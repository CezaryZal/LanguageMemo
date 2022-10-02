package com.cezaryzal.languageMemo.service.result.modifier;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplayLevelModifier {

    private final ServiceResultConfig serviceResultConfig;

    @Autowired
    public ReplayLevelModifier(ServiceResultConfig serviceResultConfig) {
        this.serviceResultConfig = serviceResultConfig;
    }

    public int  changeReplayLevelByNumberOfTries(int numberOfTries, int replayLevel){
        int updateReplayLevel = replayLevel - numberOfTries + serviceResultConfig.getScalesReplayLevelNumber();

        if (updateReplayLevel <= serviceResultConfig.getMinReplayLevelValue()){
            return serviceResultConfig.getMinReplayLevelValue();
        } else if (updateReplayLevel >= serviceResultConfig.getMaxReplayLevelValue()){
            return serviceResultConfig.getMaxReplayLevelValue();
        }
        return updateReplayLevel;
    }

}
