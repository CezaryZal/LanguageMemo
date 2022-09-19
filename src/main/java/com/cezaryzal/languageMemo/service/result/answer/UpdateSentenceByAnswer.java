package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
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
    private final CurrentPlayedSentenceComponent currentlyPlayedCase;

    @Autowired
    public UpdateSentenceByAnswer(ReplayLevelModifier replayLevelModifier, ReplayDateModifier replayDateModifier, CurrentPlayedSentenceComponent currentlyPlayedCase) {
        this.replayLevelModifier = replayLevelModifier;
        this.replayDateModifier = replayDateModifier;
        this.currentlyPlayedCase = currentlyPlayedCase;
    }

    public Sentence getUpdatedReplayDataSentence(ComponentDtoInput componentDtoInput){
        Sentence currentlyUsedSentence = currentlyPlayedCase.getUsedSentence();
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
