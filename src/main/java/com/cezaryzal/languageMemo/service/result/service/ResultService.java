package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import com.cezaryzal.languageMemo.service.result.answer.CorrectAnswer;
import com.cezaryzal.languageMemo.service.result.answer.IncorrectAnswer;
import com.cezaryzal.languageMemo.service.result.answer.UpdateSentenceByAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService extends CheckingSentences{

    private final IncorrectAnswer incorrectAnswer;
    private final CorrectAnswer correctAnswer;
    private final RepositorySentenceService repositorySentenceService;
    private final CurrentPlayedSentenceComponent currentlyPlayedCase;

    @Autowired
    public ResultService(IncorrectAnswer incorrectAnswer,
                         CorrectAnswer correctAnswer,
                         RepositorySentenceService repositorySentenceService,
                         CurrentPlayedSentenceComponent currentlyPlayedCase) {
        this.incorrectAnswer = incorrectAnswer;
        this.correctAnswer = correctAnswer;
        this.repositorySentenceService = repositorySentenceService;
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
                correctAnswer.serviceByInputComponent(componentDtoInput) :
                incorrectAnswer.serviceByInputComponent(componentDtoInput);
    }
}
