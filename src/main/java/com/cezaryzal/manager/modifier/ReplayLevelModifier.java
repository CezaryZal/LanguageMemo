package com.cezaryzal.manager.modifier;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import org.springframework.stereotype.Component;

@Component
public class ReplayLevelModifier {
    final int NUMBER_THAT_SCALES_REPLAY_LEVEL = 1;
    final int MIN_REPLAY_LEVEL_VALUE = 0;
    final int MAX_REPLAY_LEVEL_VALUE = 5;

    private Sentence currentlyUsedSentence;

    public void setCurrentlyUsedSentence(Sentence currentlyUsedSentence) {
        this.currentlyUsedSentence = currentlyUsedSentence;
    }

    public int changeReplayLevelByNumberOfTries(Answer inputAnswer){
        int updateReplayLevel = currentlyUsedSentence.getReplayLevel() - inputAnswer.getNumberOfTries() + NUMBER_THAT_SCALES_REPLAY_LEVEL;

        if (updateReplayLevel < MIN_REPLAY_LEVEL_VALUE){
            return MIN_REPLAY_LEVEL_VALUE;
        } else if (updateReplayLevel > MAX_REPLAY_LEVEL_VALUE){
            return MAX_REPLAY_LEVEL_VALUE;
        }
        return updateReplayLevel;
    }

}
