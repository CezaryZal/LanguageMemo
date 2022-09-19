package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import com.cezaryzal.languageMemo.service.result.answer.IncorrectAnswer;
import com.cezaryzal.languageMemo.service.result.answer.UpdateSentenceByAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService extends CheckingSentences{

    private final IncorrectAnswer incorrectAnswer;
    private final RepositorySentenceService repositorySentenceService;
    private final UpdateSentenceByAnswer updateSentenceByAnswer;
    private final NextComponentDtoOutput nextComponentDtoOutput;

    @Autowired
    public ResultService(IncorrectAnswer incorrectAnswer,
                         RepositorySentenceService repositorySentenceService,
                         UpdateSentenceByAnswer updateSentenceByAnswer,
                         NextComponentDtoOutput nextComponentDtoOutput) {
        this.incorrectAnswer = incorrectAnswer;
        this.repositorySentenceService = repositorySentenceService;
        this.updateSentenceByAnswer = updateSentenceByAnswer;
        this.nextComponentDtoOutput = nextComponentDtoOutput;
    }


    public ComponentDtoOutput resultByInputAnswer(ComponentDtoInput componentDtoInput) {
        Sentence currentlyUsedSentence = repositorySentenceService
                .findById(componentDtoInput.getSentenceId())
                .orElseThrow();
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(componentDtoInput,
                currentlyUsedSentence);

        return answerIsCorrect ?
                handleCorrectAnswer(componentDtoInput) :
                incorrectAnswer.validationByOnNumberOfTries(componentDtoInput, currentlyUsedSentence);
    }

    private ComponentDtoOutput handleCorrectAnswer(ComponentDtoInput componentDtoInput) {
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedReplayDataSentence(componentDtoInput));

        return getNextSentenceDtoToShow(true);
    }

    // option, force to always write the correct answer
//    private ComponentDtoOutput handleIncorrectAnswer(ComponentDtoInput componentDtoInput,
//                                                     Sentence currentlyUsedSentence) {
//
//        if (componentDtoInput.getNumberOfTries() <= ApiConstants.MAX_REPLAY_LEVEL_VALUE){
//            return incorrectAnswer.inputValidationBasedOnNumberOfTries(
//                    componentDtoInput,
//                    currentlyUsedSentence);
//        } else {
//            updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedReplayDataSentence(componentDtoInput,
//                    currentlyUsedSentence));
//
//            return getNextSentenceDtoToShow(false);
//        }
//    }

    private void updateCurrentlyUsedSentence(Sentence updatedSentence) {
        repositorySentenceService.updateSentence(updatedSentence);
    }

    private ComponentDtoOutput getNextSentenceDtoToShow(boolean isCorrectAnswer) {
        return nextComponentDtoOutput.getNextComponentDtoOutput(isCorrectAnswer);
    }

}
