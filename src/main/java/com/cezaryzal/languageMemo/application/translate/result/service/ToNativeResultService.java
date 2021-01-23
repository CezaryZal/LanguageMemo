package com.cezaryzal.languageMemo.application.translate.result.service;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.reposervice.RepoService;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.application.translate.result.IncorrectAnswer;
import com.cezaryzal.languageMemo.application.translate.result.NextTranslateComponentDto;
import com.cezaryzal.languageMemo.application.translate.result.SentencesComparator;
import com.cezaryzal.languageMemo.application.translate.result.UpdateSentenceByAnswer;
import com.cezaryzal.languageMemo.config.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ToNativeResultService extends CheckingSentences{

    private final IncorrectAnswer incorrectAnswer;
    private final RepoService repoService;
    private final UpdateSentenceByAnswer updateSentenceByAnswer;
    private final NextTranslateComponentDto nextTranslateComponentDto;

    @Autowired
    public ToNativeResultService(SentencesComparator sentencesComparator,
                                 IncorrectAnswer incorrectAnswer,
                                 @Qualifier("toNativeRepoServiceImp") RepoService repoService,
                                 UpdateSentenceByAnswer updateSentenceByAnswer,
                                 NextTranslateComponentDto nextTranslateComponentDto) {
        super(sentencesComparator);
        this.incorrectAnswer = incorrectAnswer;
        this.repoService = repoService;
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
        Optional<SentenceModel> searchSentence = repoService.findById(id);
        return searchSentence
                .orElseThrow(() -> new EntityNotFoundException("Szukany record na podstawie id nie istnieje"));
    }

    private TranslateComponentDto handleCorrectAnswer(TranslateComponentInput translateComponentInput,
                                                      SentenceModel currentlyUsedSentenceModel) {
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(
                translateComponentInput,
                currentlyUsedSentenceModel));

        return getNextSentenceDtoToShow(true);
    }

    private TranslateComponentDto handleIncorrectAnswer(TranslateComponentInput translateComponentInput,
                                                        SentenceModel currentlyUsedSentenceModel) {

        if (translateComponentInput.getNumberOfTries() <= ApiConstants.MAX_REPLAY_LEVEL_VALUE){
            return incorrectAnswer.inputValidationBasedOnNumberOfTries(
                    translateComponentInput,
                    currentlyUsedSentenceModel);
        } else {
            updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(translateComponentInput,
                                                                                currentlyUsedSentenceModel));

            return getNextSentenceDtoToShow(false);
        }

    }

    private void updateCurrentlyUsedSentence(SentenceModel updateSentenceModel) {
        repoService.updateSentence(updateSentenceModel);
    }

    private TranslateComponentDto getNextSentenceDtoToShow(boolean isCorrectAnswer) {
        return nextTranslateComponentDto.getNextSentenceDtoToNative(isCorrectAnswer);
    }
}
