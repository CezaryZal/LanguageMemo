package com.cezaryzal.manager.result;

import com.cezaryzal.config.ApiConstants;
import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.service.repository.SentenceService;
import com.cezaryzal.manager.service.sentence.NextSentenceDto;
import com.cezaryzal.manager.service.sentence.SentencesComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ResponseService {

    private SentencesComparator sentencesComparator;
    private IncorrectAnswer incorrectAnswer;
    private SentenceService sentenceService;
    private UpdateSentenceByAnswer updateSentenceByAnswer;
    private NextSentenceDto nextSentenceDto;

    @Autowired
    public ResponseService(SentencesComparator sentencesComparator, IncorrectAnswer incorrectAnswer,
                           SentenceService sentenceService, UpdateSentenceByAnswer updateSentenceByAnswer, NextSentenceDto nextSentenceDto) {
        this.sentencesComparator = sentencesComparator;
        this.incorrectAnswer = incorrectAnswer;
        this.sentenceService = sentenceService;
        this.updateSentenceByAnswer = updateSentenceByAnswer;
        this.nextSentenceDto = nextSentenceDto;
    }

    public SentenceDTO resultByInputAnswer(Answer inputAnswer) {
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

    private boolean checkingCorrectnessOfPhraseTranslation(Answer inputAnswer, Sentence currentlyUsedSentence) {
        return sentencesComparator.comparingInputPhrasesWithPattern(inputAnswer, currentlyUsedSentence);
    }

    private SentenceDTO handleCorrectAnswer(Answer inputAnswer, Sentence currentlyUsedSentence) {
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(inputAnswer, currentlyUsedSentence));

        return getNextSentenceDtoToShow(true);
    }

    private SentenceDTO handleIncorrectAnswer(Answer inputAnswer, Sentence currentlyUsedSentence) {

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
