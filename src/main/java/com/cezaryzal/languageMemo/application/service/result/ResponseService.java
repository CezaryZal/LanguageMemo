package com.cezaryzal.languageMemo.application.service.result;

import com.cezaryzal.languageMemo.application.entity.InputAnswer;
import com.cezaryzal.languageMemo.application.entity.Sentence;
import com.cezaryzal.languageMemo.application.entity.SentenceDTO;
import com.cezaryzal.languageMemo.application.service.serviceTmp.repository.SentenceService;
import com.cezaryzal.languageMemo.application.service.serviceTmp.sentence.NextSentenceDto;
import com.cezaryzal.languageMemo.application.service.serviceTmp.sentence.SentencesComparator;
import com.cezaryzal.languageMemo.config.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ResponseService {

    private final SentencesComparator sentencesComparator;
    private final IncorrectAnswer incorrectAnswer;
    private final SentenceService sentenceService;
    private final UpdateSentenceByAnswer updateSentenceByAnswer;
    private final NextSentenceDto nextSentenceDto;

    @Autowired
    public ResponseService(SentencesComparator sentencesComparator, IncorrectAnswer incorrectAnswer,
                           SentenceService sentenceService, UpdateSentenceByAnswer updateSentenceByAnswer, NextSentenceDto nextSentenceDto) {
        this.sentencesComparator = sentencesComparator;
        this.incorrectAnswer = incorrectAnswer;
        this.sentenceService = sentenceService;
        this.updateSentenceByAnswer = updateSentenceByAnswer;
        this.nextSentenceDto = nextSentenceDto;
    }

    public SentenceDTO resultByInputAnswer(InputAnswer inputAnswer) {
        Sentence currentlyUsedSentence = getCurrentlyUsedSentenceFromDBById(inputAnswer.getSentenceId());
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(inputAnswer, currentlyUsedSentence);

        return answerIsCorrect ?
                handleCorrectAnswer(inputAnswer, currentlyUsedSentence) : handleIncorrectAnswer(inputAnswer, currentlyUsedSentence);
    }

    //Zrobić oddzielną klasę na sprawdzenie poprawności przesłanego Answer; tutaj nie będzie mozliwości umieszczenia nulla
    private Sentence getCurrentlyUsedSentenceFromDBById(Long id) {
        Optional<Sentence> searchSentence = sentenceService.findById(id);
        return searchSentence
                .orElseThrow(() -> new EntityNotFoundException("Szukany record na podstawie id nie istnieje"));
    }

    private boolean checkingCorrectnessOfPhraseTranslation(InputAnswer inputAnswer, Sentence currentlyUsedSentence) {
        return sentencesComparator.comparingInputPhrasesWithPattern(inputAnswer, currentlyUsedSentence);
    }

    private SentenceDTO handleCorrectAnswer(InputAnswer inputAnswer, Sentence currentlyUsedSentence) {
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(inputAnswer, currentlyUsedSentence));

        return getNextSentenceDtoToShow(true);
    }

    private SentenceDTO handleIncorrectAnswer(InputAnswer inputAnswer, Sentence currentlyUsedSentence) {

        if (inputAnswer.getNumberOfTries() <= ApiConstants.MAX_REPLAY_LEVEL_VALUE){
            return incorrectAnswer.inputValidationBasedOnNumberOfTries(inputAnswer, currentlyUsedSentence);
        }
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(inputAnswer, currentlyUsedSentence));

        return getNextSentenceDtoToShow(false);
    }

    private void updateCurrentlyUsedSentence(Sentence updateSentence) {
        sentenceService.updateSentence(updateSentence);
    }

    private SentenceDTO getNextSentenceDtoToShow(boolean isCorrectAnswer) {
        return nextSentenceDto.getNextSentenceDto(isCorrectAnswer);
    }


//    public String getPhraseInEnglishFromSentenceById (Long id){
//
//        Optional<Sentence> searchSentence = sentenceService.findById(id);
//
//        return searchSentence
//                .map(Sentence::getLanguageEng)
//                .orElseThrow(() -> new RuntimeException("Brak frazy, nie istnieje record o podanym id"));
//    }

}
