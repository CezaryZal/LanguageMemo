package com.cezaryzal.languageMemo.service.result;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
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

    @Autowired
    public UpdateSentenceByAnswer(ReplayLevelModifier replayLevelModifier, ReplayDateModifier replayDateModifier) {
        this.replayLevelModifier = replayLevelModifier;
        this.replayDateModifier = replayDateModifier;
    }

    public Sentence getUpdatedSentence(ComponentDtoInput componentDtoInput, Sentence currentlyUsedSentence){
        int modifiedReplayLevel = modifyReplayLevel(componentDtoInput.getNumberOfTries(), currentlyUsedSentence);
        currentlyUsedSentence.setReplayLevel(modifiedReplayLevel);
        currentlyUsedSentence.setReplayDate(modifyReplayDate(modifiedReplayLevel));

        return currentlyUsedSentence;
    }
    
    private int modifyReplayLevel(int numberOfTries, Sentence currentlyUsedSentence){
        return replayLevelModifier
                .changeReplayLevelByNumberOfTries(numberOfTries, currentlyUsedSentence.getReplayLevel());
    }
    
    private LocalDate modifyReplayDate(int replayLevel){
        return replayDateModifier.changeReplayDateByReplayLevel(replayLevel);
        
    }
}
