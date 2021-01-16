package com.cezaryzal.languageMemo.application.translate.result.modifier;

import com.cezaryzal.languageMemo.config.ApiConstants;
import org.springframework.stereotype.Service;

@Service
public class ReplayLevelModifier {

    public int changeReplayLevelByNumberOfTries(int numberOfTries, int replayLevel){
        int updateReplayLevel = replayLevel - numberOfTries + ApiConstants.NUMBER_THAT_SCALES_REPLAY_LEVEL;

        if (updateReplayLevel <= ApiConstants.MIN_REPLAY_LEVEL_VALUE){
            return ApiConstants.MIN_REPLAY_LEVEL_VALUE;
        } else if (updateReplayLevel >= ApiConstants.MAX_REPLAY_LEVEL_VALUE){
            return ApiConstants.MAX_REPLAY_LEVEL_VALUE;
        }
        return updateReplayLevel;
    }

}
