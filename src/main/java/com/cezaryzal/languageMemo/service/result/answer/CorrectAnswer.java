package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import com.cezaryzal.languageMemo.service.result.service.NextMemoItemDtoOutput;
import org.springframework.stereotype.Service;

@Service
public class CorrectAnswer implements ServiceAnswer{

    private final RepositoryMemoItemService repositoryMemoItemService;
    private final UpdateMemoItemByAnswer updateMemoItemByAnswer;
    private final NextMemoItemDtoOutput nextMemoItemDtoOutput;

    public CorrectAnswer(RepositoryMemoItemService repositoryMemoItemService,
                         UpdateMemoItemByAnswer updateMemoItemByAnswer,
                         NextMemoItemDtoOutput nextMemoItemDtoOutput) {
        this.repositoryMemoItemService = repositoryMemoItemService;
        this.updateMemoItemByAnswer = updateMemoItemByAnswer;
        this.nextMemoItemDtoOutput = nextMemoItemDtoOutput;
    }

    @Override
    public MemoItemDtoOutput serviceByMemoItemInput(MemoItemDtoInput memoItemDtoInput) {
        repositoryMemoItemService.updateMemoItem(
                updateMemoItemByAnswer.getUpdatedReplayDataMemoItem(memoItemDtoInput));

        return nextMemoItemDtoOutput.getNextMemoItemDtoOutput(true);
    }


}
