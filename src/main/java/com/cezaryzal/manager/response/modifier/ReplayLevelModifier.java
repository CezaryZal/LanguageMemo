package com.cezaryzal.manager.response.modifier;

import org.springframework.stereotype.Component;

@Component
public class ReplayLevelModifier {
    final int NUMBER_THAT_SCALES_REPLAY_LEVEL = 1;
    final int MIN_REPLAY_LEVEL_VALUE = 0;
    final int MAX_REPLAY_LEVEL_VALUE = 5;

    private int replayLevel;

    public void setReplayLevel(int replayLevel) {
        this.replayLevel = replayLevel;
    }

    public int changeReplayLevelByNumberOfTries(int numberOfTries){
        int updateReplayLevel = replayLevel - numberOfTries + NUMBER_THAT_SCALES_REPLAY_LEVEL;

        if (updateReplayLevel <= MIN_REPLAY_LEVEL_VALUE){
            return MIN_REPLAY_LEVEL_VALUE;
        } else if (updateReplayLevel >= MAX_REPLAY_LEVEL_VALUE){
            return MAX_REPLAY_LEVEL_VALUE;
        }
        return updateReplayLevel;
    }

}
