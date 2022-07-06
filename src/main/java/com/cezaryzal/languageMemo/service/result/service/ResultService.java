package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import com.cezaryzal.languageMemo.service.result.incorrect.IncorrectAnswer;
import com.cezaryzal.languageMemo.service.result.NextComponentDtoOutput;
import com.cezaryzal.languageMemo.service.result.SentencesComparator;
import com.cezaryzal.languageMemo.service.result.UpdateSentenceByAnswer;
import com.cezaryzal.languageMemo.config.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ResultService extends CheckingSentences{

    private final IncorrectAnswer incorrectAnswer;
    private final RepositorySentenceService repositorySentenceService;
    private final UpdateSentenceByAnswer updateSentenceByAnswer;
    private final NextComponentDtoOutput nextComponentDtoOutput;

    @Autowired
    public ResultService(SentencesComparator sentencesComparator,
                         IncorrectAnswer incorrectAnswer,
                         RepositorySentenceService repositorySentenceService,
                         UpdateSentenceByAnswer updateSentenceByAnswer,
                         NextComponentDtoOutput nextComponentDtoOutput) {
        super(sentencesComparator);
        this.incorrectAnswer = incorrectAnswer;
        this.repositorySentenceService = repositorySentenceService;
        this.updateSentenceByAnswer = updateSentenceByAnswer;
        this.nextComponentDtoOutput = nextComponentDtoOutput;
    }


    public ComponentDtoOutput resultByInputAnswer(ComponentDtoInput componentDtoInput) {
        Sentence currentlyUsedSentence =
                getCurrentlyUsedSentenceFromDBById(componentDtoInput.getSentenceId());
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(componentDtoInput,
                currentlyUsedSentence);

        return answerIsCorrect ?
                handleCorrectAnswer(componentDtoInput, currentlyUsedSentence) :
                handleIncorrectAnswer(componentDtoInput, currentlyUsedSentence);
    }

    //Zrobić oddzielną klasę na sprawdzenie poprawności przesłanego Answer; tutaj nie będzie mozliwości umieszczenia nulla
    private Sentence getCurrentlyUsedSentenceFromDBById(Long id) {
        Optional<Sentence> searchSentence = repositorySentenceService.findById(id);
        return searchSentence
                .orElseThrow(() -> new EntityNotFoundException("Szukany record na podstawie id nie istnieje"));
    }

    private ComponentDtoOutput handleCorrectAnswer(ComponentDtoInput componentDtoInput,
                                                   Sentence currentlyUsedSentence) {
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(componentDtoInput,
                currentlyUsedSentence));

        return getNextSentenceDtoToShow(true);
    }

    private ComponentDtoOutput handleIncorrectAnswer(ComponentDtoInput componentDtoInput,
                                                     Sentence currentlyUsedSentence) {

        if (componentDtoInput.getNumberOfTries() <= ApiConstants.MAX_REPLAY_LEVEL_VALUE){
            return incorrectAnswer.inputValidationBasedOnNumberOfTries(
                    componentDtoInput,
                    currentlyUsedSentence);
        } else {
            updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(componentDtoInput,
                    currentlyUsedSentence));

            return getNextSentenceDtoToShow(false);
        }
    }

    private void updateCurrentlyUsedSentence(Sentence updateSentence) {
        repositorySentenceService.updateSentence(updateSentence);
    }

    private ComponentDtoOutput getNextSentenceDtoToShow(boolean isCorrectAnswer) {
        return nextComponentDtoOutput.getNextComponentDtoOutput(isCorrectAnswer);
    }

}
