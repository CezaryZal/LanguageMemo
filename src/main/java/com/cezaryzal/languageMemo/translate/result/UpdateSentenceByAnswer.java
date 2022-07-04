package com.cezaryzal.languageMemo.translate.result;

import com.cezaryzal.languageMemo.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.model.SentenceModel;
import com.cezaryzal.languageMemo.translate.result.modifier.ReplayDateModifier;
import com.cezaryzal.languageMemo.translate.result.modifier.ReplayLevelModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UpdateSentenceByAnswer {

    private final ReplayLevelModifier replayLevelModifier;
    private final ReplayDateModifier replayDateModifier;

    @Autowired
    public UpdateSentenceByAnswer(ReplayLevelModifier replayLevelModifier, ReplayDateModifier replayDateModifier) {
        this.replayLevelModifier = replayLevelModifier;
        this.replayDateModifier = replayDateModifier;
    }

    public SentenceModel getUpdatedSentence(TranslateComponentInput translateComponentInput, SentenceModel currentlyUsedSentenceModel){
        int modifiedReplayLevel = modifyReplayLevel(translateComponentInput.getNumberOfTries(), currentlyUsedSentenceModel);
        currentlyUsedSentenceModel.setReplayLevelFromNative(modifiedReplayLevel);
        currentlyUsedSentenceModel.setReplayDateFromNative(modifyReplayDate(modifiedReplayLevel));

        return currentlyUsedSentenceModel;
    }
    
    private int modifyReplayLevel(int numberOfTries, SentenceModel currentlyUsedSentenceModel){
        return replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, currentlyUsedSentenceModel.getReplayLevelFromNative());
    }
    
    private LocalDate modifyReplayDate(int replayLevel){
        return replayDateModifier.changeReplayDateByReplayLevel(replayLevel);
        
    }
}
