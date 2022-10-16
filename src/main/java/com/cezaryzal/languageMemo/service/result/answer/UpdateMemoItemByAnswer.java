package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.service.result.modifier.ReplayDateModifier;
import com.cezaryzal.languageMemo.service.result.modifier.ReplayLevelModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UpdateMemoItemByAnswer {

    private final ReplayLevelModifier replayLevelModifier;
    private final ReplayDateModifier replayDateModifier;
    private final CurrentPlayedMemoItem currentlyPlayedCase;

    @Autowired
    public UpdateMemoItemByAnswer(ReplayLevelModifier replayLevelModifier, ReplayDateModifier replayDateModifier, CurrentPlayedMemoItem currentlyPlayedCase) {
        this.replayLevelModifier = replayLevelModifier;
        this.replayDateModifier = replayDateModifier;
        this.currentlyPlayedCase = currentlyPlayedCase;
    }

    public MemoItem getUpdatedReplayDataMemoItem(MemoItemDtoInput memoItemDtoInput){
        MemoItem currentlyUsedMemoItem = currentlyPlayedCase.getUsedMemoItem();
        int modifiedReplayLevel = modifyReplayLevel(memoItemDtoInput.getGuess(), currentlyUsedMemoItem);

        currentlyUsedMemoItem.setReplayLevel(modifiedReplayLevel);
        currentlyUsedMemoItem.setReplayDate(modifyReplayDate(modifiedReplayLevel));

        return currentlyUsedMemoItem;
    }
    
    private int modifyReplayLevel(int guess, MemoItem currentlyUsedMemoItem){
        return replayLevelModifier
                .changeReplayLevelByNumberOfTries(guess, currentlyUsedMemoItem.getReplayLevel());
    }
    
    private LocalDate modifyReplayDate(int replayLevel){
        return replayDateModifier.changeReplayDateByReplayLevel(replayLevel);
        
    }
}
