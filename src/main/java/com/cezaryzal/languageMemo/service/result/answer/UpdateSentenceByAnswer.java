package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.result.modifier.ReplayDateModifier;
import com.cezaryzal.languageMemo.service.result.modifier.ReplayLevelModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UpdateSentenceByAnswer {

    private final ReplayLevelModifier replayLevelModifier;
    private final ReplayDateModifier replayDateModifier;
    private final CurrentPlayedMemoItem currentlyPlayedCase;

    @Autowired
    public UpdateSentenceByAnswer(ReplayLevelModifier replayLevelModifier, ReplayDateModifier replayDateModifier, CurrentPlayedMemoItem currentlyPlayedCase) {
        this.replayLevelModifier = replayLevelModifier;
        this.replayDateModifier = replayDateModifier;
        this.currentlyPlayedCase = currentlyPlayedCase;
    }

    public Sentence getUpdatedReplayDataSentence(MemoItemDtoInput memoItemDtoInput){
        Sentence currentlyUsedSentence = currentlyPlayedCase.getUsedSentence();
        int modifiedReplayLevel = modifyReplayLevel(memoItemDtoInput.getGuess(), currentlyUsedSentence);

        currentlyUsedSentence.setReplayLevel(modifiedReplayLevel);
        currentlyUsedSentence.setReplayDate(modifyReplayDate(modifiedReplayLevel));

        return currentlyUsedSentence;
    }
    
    private int modifyReplayLevel(int guess, Sentence currentlyUsedSentence){
        return replayLevelModifier
                .changeReplayLevelByNumberOfTries(guess, currentlyUsedSentence.getReplayLevel());
    }
    
    private LocalDate modifyReplayDate(int replayLevel){
        return replayDateModifier.changeReplayDateByReplayLevel(replayLevel);
        
    }
}
