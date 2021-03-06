package com.cezaryzal.manager.result;

import com.cezaryzal.entity.InputAnswer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.manager.result.modifier.ReplayDateModifier;
import com.cezaryzal.manager.result.modifier.ReplayLevelModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UpdateSentenceByAnswer {

    private ReplayLevelModifier replayLevelModifier;
    private ReplayDateModifier replayDateModifier;

    @Autowired
    public UpdateSentenceByAnswer(ReplayLevelModifier replayLevelModifier, ReplayDateModifier replayDateModifier) {
        this.replayLevelModifier = replayLevelModifier;
        this.replayDateModifier = replayDateModifier;
    }

    public Sentence getUpdatedSentence(InputAnswer inputAnswer, Sentence currentlyUsedSentence){
        int modifiedReplayLevel = modifyReplayLevel(inputAnswer.getNumberOfTries(), currentlyUsedSentence);
        currentlyUsedSentence.setReplayLevel(modifiedReplayLevel);
        currentlyUsedSentence.setReplayDate(modifyReplayDate(modifiedReplayLevel));

        return currentlyUsedSentence;
    }
    
    private int modifyReplayLevel(int numberOfTries, Sentence currentlyUsedSentence){
        return replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, currentlyUsedSentence.getReplayLevel());
    }
    
    private LocalDate modifyReplayDate(int replayLevel){
        return replayDateModifier.changeReplayDateByReplayLevel(replayLevel);
        
    }
}
