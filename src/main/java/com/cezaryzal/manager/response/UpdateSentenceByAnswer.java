package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.manager.modifier.ReplayDateModifier;
import com.cezaryzal.manager.modifier.ReplayLevelModifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UpdateSentenceByAnswer {

    private Sentence currentlyUsedSentence;

    private ReplayLevelModifier replayLevelModifier;
    private ReplayDateModifier replayDateModifier;

    public UpdateSentenceByAnswer(ReplayLevelModifier replayLevelModifier, ReplayDateModifier replayDateModifier) {
        this.replayLevelModifier = replayLevelModifier;
        this.replayDateModifier = replayDateModifier;
    }

    public void setCurrentlyUsedSentence(Sentence currentlyUsedSentence) {
        this.currentlyUsedSentence = currentlyUsedSentence;
    }

    public Sentence getUpdatedSentence(Answer inputAnswer){
        int modifiedReplayLevel = modifyReplayLevel(inputAnswer.getNumberOfTries());
        currentlyUsedSentence.setReplayLevel(modifiedReplayLevel);
        currentlyUsedSentence.setReplayDate(modifyReplayDate(modifiedReplayLevel));

        return currentlyUsedSentence;
    }
    
    private int modifyReplayLevel(int numberOfTries){
        replayLevelModifier.setReplayLevel(currentlyUsedSentence.getReplayLevel());
        return replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries);
    }
    
    private LocalDate modifyReplayDate(int replayLevel){
        return replayDateModifier.changeReplayDateByReplayLevel(replayLevel);
        
    }
}
