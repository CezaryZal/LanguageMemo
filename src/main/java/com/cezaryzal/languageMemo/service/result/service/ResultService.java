package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
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
    private final CurrentPlayedSentenceComponent currentlyPlayedCase;

    @Autowired
    public ResultService(IncorrectAnswer incorrectAnswer,
                         RepositorySentenceService repositorySentenceService,
                         UpdateSentenceByAnswer updateSentenceByAnswer,
                         NextComponentDtoOutput nextComponentDtoOutput,
                         CurrentPlayedSentenceComponent currentlyPlayedCase) {
        this.incorrectAnswer = incorrectAnswer;
        this.repositorySentenceService = repositorySentenceService;
        this.updateSentenceByAnswer = updateSentenceByAnswer;
        this.nextComponentDtoOutput = nextComponentDtoOutput;
        this.currentlyPlayedCase = currentlyPlayedCase;
    }


    public ComponentDtoOutput resultByInputAnswer(ComponentDtoInput componentDtoInput) {

        if (componentDtoInput.getNumberOfTries() == 0){
            Sentence currentlyUsedSentence = repositorySentenceService
                    .findById(componentDtoInput.getSentenceId())
                    .orElseThrow();

            currentlyPlayedCase.initialProgressPhrase(currentlyUsedSentence);
        }
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(
                                                                componentDtoInput,
                                                                currentlyPlayedCase.getUsedSentence());

        return answerIsCorrect ?
                handleCorrectAnswer(componentDtoInput) :
                incorrectAnswer.validationByOnNumberOfTries(componentDtoInput);
    }

    private ComponentDtoOutput handleCorrectAnswer(ComponentDtoInput componentDtoInput) {
        repositorySentenceService.updateSentence(updateSentenceByAnswer
                                                        .getUpdatedReplayDataSentence(componentDtoInput));

        return nextComponentDtoOutput.getNextComponentDtoOutput(true);
    }
}
