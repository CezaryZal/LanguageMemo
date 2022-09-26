package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import com.cezaryzal.languageMemo.service.result.service.NextComponentDtoOutput;
import org.springframework.stereotype.Service;

@Service
public class CorrectAnswer implements ServiceAnswer{

    private final RepositorySentenceService repositorySentenceService;
    private final UpdateSentenceByAnswer updateSentenceByAnswer;
    private final NextComponentDtoOutput nextComponentDtoOutput;

    public CorrectAnswer(RepositorySentenceService repositorySentenceService,
                         UpdateSentenceByAnswer updateSentenceByAnswer,
                         NextComponentDtoOutput nextComponentDtoOutput) {
        this.repositorySentenceService = repositorySentenceService;
        this.updateSentenceByAnswer = updateSentenceByAnswer;
        this.nextComponentDtoOutput = nextComponentDtoOutput;
    }

    @Override
    public ComponentDtoOutput serviceByInputComponent(ComponentDtoInput componentDtoInput) {
        repositorySentenceService.updateSentence(updateSentenceByAnswer
                .getUpdatedReplayDataSentence(componentDtoInput));

        return nextComponentDtoOutput.getNextComponentDtoOutput(true);
    }


}
