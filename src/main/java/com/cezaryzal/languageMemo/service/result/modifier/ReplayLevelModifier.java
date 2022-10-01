package com.cezaryzal.languageMemo.service.result.modifier;

import com.cezaryzal.languageMemo.config.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplayLevelModifier {

    private final ApiConstants apiConstants;

    @Autowired
    public ReplayLevelModifier(ApiConstants apiConstants) {
        this.apiConstants = apiConstants;
    }

    public int  changeReplayLevelByNumberOfTries(int numberOfTries, int replayLevel){
        int updateReplayLevel = replayLevel - numberOfTries + apiConstants.getScalesReplayLevelNumber();

        if (updateReplayLevel <= apiConstants.getMinReplayLevelValue()){
            return apiConstants.getMinReplayLevelValue();
        } else if (updateReplayLevel >= apiConstants.getMaxReplayLevelValue()){
            return apiConstants.getMaxReplayLevelValue();
        }
        return updateReplayLevel;
    }

}
