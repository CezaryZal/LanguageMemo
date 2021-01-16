package com.cezaryzal.languageMemo.application.translate.result;

import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.reposervice.SentenceRepoService;
import com.cezaryzal.languageMemo.application.translate.SentencesComparator;
import com.cezaryzal.languageMemo.config.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ResponseService {

    private final SentencesComparator sentencesComparator;
    private final IncorrectAnswer incorrectAnswer;
    private final SentenceRepoService sentenceRepoService;
    private final UpdateSentenceByAnswer updateSentenceByAnswer;
    private final NextTranslateComponentDto nextTranslateComponentDto;

    @Autowired
    public ResponseService(SentencesComparator sentencesComparator,
                           IncorrectAnswer incorrectAnswer,
                           SentenceRepoService sentenceRepoService,
                           UpdateSentenceByAnswer updateSentenceByAnswer,
                           NextTranslateComponentDto nextTranslateComponentDto) {
        this.sentencesComparator = sentencesComparator;
        this.incorrectAnswer = incorrectAnswer;
        this.sentenceRepoService = sentenceRepoService;
        this.updateSentenceByAnswer = updateSentenceByAnswer;
        this.nextTranslateComponentDto = nextTranslateComponentDto;
    }

    public TranslateComponentDto resultByInputAnswer(TranslateComponentInput translateComponentInput) {
        SentenceModel currentlyUsedSentenceModel =
                getCurrentlyUsedSentenceFromDBById(translateComponentInput.getSentenceId());
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(translateComponentInput,
                                                                        currentlyUsedSentenceModel);

        return answerIsCorrect ?
                handleCorrectAnswer(translateComponentInput, currentlyUsedSentenceModel) :
                handleIncorrectAnswer(translateComponentInput, currentlyUsedSentenceModel);
    }

    //Zrobić oddzielną klasę na sprawdzenie poprawności przesłanego Answer; tutaj nie będzie mozliwości umieszczenia nulla
    private SentenceModel getCurrentlyUsedSentenceFromDBById(Long id) {
        Optional<SentenceModel> searchSentence = sentenceRepoService.findById(id);
        return searchSentence
                .orElseThrow(() -> new EntityNotFoundException("Szukany record na podstawie id nie istnieje"));
    }

    private boolean checkingCorrectnessOfPhraseTranslation(TranslateComponentInput translateComponentInput,
                                                           SentenceModel currentlyUsedSentenceModel) {
        return sentencesComparator.comparingInputPhrasesWithPattern(translateComponentInput,
                                                                    currentlyUsedSentenceModel);
    }

    private TranslateComponentDto handleCorrectAnswer(TranslateComponentInput translateComponentInput,
                                                      SentenceModel currentlyUsedSentenceModel) {
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(translateComponentInput,
                                                                            currentlyUsedSentenceModel));

        return getNextSentenceDtoToShow(true);
    }

    private TranslateComponentDto handleIncorrectAnswer(TranslateComponentInput translateComponentInput,
                                                        SentenceModel currentlyUsedSentenceModel) {

        if (translateComponentInput.getNumberOfTries() <= ApiConstants.MAX_REPLAY_LEVEL_VALUE){
            return incorrectAnswer.inputValidationBasedOnNumberOfTries(translateComponentInput,
                                                                    currentlyUsedSentenceModel);
        }
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(translateComponentInput,
                                                                            currentlyUsedSentenceModel));

        return getNextSentenceDtoToShow(false);
    }

    private void updateCurrentlyUsedSentence(SentenceModel updateSentenceModel) {
        sentenceRepoService.updateSentence(updateSentenceModel);
    }

    private TranslateComponentDto getNextSentenceDtoToShow(boolean isCorrectAnswer) {
        return nextTranslateComponentDto.getNextSentenceDto(isCorrectAnswer);
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
