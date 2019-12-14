package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.manager.modifier.ReplayLevelModifier;
import org.springframework.stereotype.Component;

@Component
public class CorrectAnswer {

    private Sentence currentlyUsedSentence;

    private ReplayLevelModifier replayLevelModifier;

    public CorrectAnswer(ReplayLevelModifier replayLevelModifier) {
        this.replayLevelModifier = replayLevelModifier;
    }

    public void setCurrentlyUsedSentence(Sentence currentlyUsedSentence) {
        this.currentlyUsedSentence = currentlyUsedSentence;
    }

    public Sentence getUpdatedSentence(Answer inputAnswer){
        replayLevelModifier.setCurrentlyUsedSentence(currentlyUsedSentence);
        int updateReplayLevel = replayLevelModifier.changeReplayLevelByNumberOfTries(inputAnswer);
        currentlyUsedSentence.setReplayLevel(updateReplayLevel);

        return createUpdatedSentence(inputAnswer);
    }




    private Sentence createUpdatedSentence(Answer inputAnswer) {
        return new Sentence(
                inputAnswer.getSentenceId(),
                currentlyUsedSentence.getLanguageEng(),
                currentlyUsedSentence.getLanguagePol(),
                currentlyUsedSentence.getHint(),
                currentlyUsedSentence.getReplayLevel(),
                currentlyUsedSentence.getReplayDate()
        );
    }
}
