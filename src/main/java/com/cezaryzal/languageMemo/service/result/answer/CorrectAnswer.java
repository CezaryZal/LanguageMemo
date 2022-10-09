package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import com.cezaryzal.languageMemo.service.result.service.NextMemoItemDtoOutput;
import org.springframework.stereotype.Service;

@Service
public class CorrectAnswer implements ServiceAnswer{

    private final RepositorySentenceService repositorySentenceService;
    private final UpdateSentenceByAnswer updateSentenceByAnswer;
    private final NextMemoItemDtoOutput nextMemoItemDtoOutput;

    public CorrectAnswer(RepositorySentenceService repositorySentenceService,
                         UpdateSentenceByAnswer updateSentenceByAnswer,
                         NextMemoItemDtoOutput nextMemoItemDtoOutput) {
        this.repositorySentenceService = repositorySentenceService;
        this.updateSentenceByAnswer = updateSentenceByAnswer;
        this.nextMemoItemDtoOutput = nextMemoItemDtoOutput;
    }

    @Override
    public MemoItemDtoOutput serviceByMemoItemInput(MemoItemDtoInput memoItemDtoInput) {
        repositorySentenceService.updateSentence(
                updateSentenceByAnswer.getUpdatedReplayDataSentence(memoItemDtoInput));

        return nextMemoItemDtoOutput.getNextMemoItemDtoOutput(true);
    }


}
