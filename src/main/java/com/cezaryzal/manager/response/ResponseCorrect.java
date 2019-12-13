package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.sun.istack.FinalArrayList;
import org.springframework.stereotype.Component;

@Component
public class ResponseCorrect {
    final int NUMBER_THAT_SCALES_REPLAY_LEVEL = 1;
    final int MIN_REPLAY_LEVEL_VALUE = 0;
    final int MAX_REPLAY_LEVEL_VALUE = 5;

    private Sentence currentlyUseSentence;

    public void setCurrentlyUseSentence(Sentence currentlyUseSentence) {
        this.currentlyUseSentence = currentlyUseSentence;
    }

    public Sentence createUpdatedSentence(Answer inputAnswer) {
        int updateReplayLevel = changeReplayLevelByNumberOfTries(inputAnswer);
        return new Sentence(
                inputAnswer.getSentenceId(),
                currentlyUseSentence.getLanguageEng(),
                currentlyUseSentence.getLanguagePol(),
                currentlyUseSentence.getHint(),
                updateReplayLevel,
                currentlyUseSentence.getDateNextUpdate()
        );
    }

    private int changeReplayLevelByNumberOfTries(Answer inputAnswer){
        int updateReplayLevel = currentlyUseSentence.getReplayLevel() - inputAnswer.getNumberOfTries() + NUMBER_THAT_SCALES_REPLAY_LEVEL;

        if (updateReplayLevel < MIN_REPLAY_LEVEL_VALUE){
            return MIN_REPLAY_LEVEL_VALUE;
        } else if (updateReplayLevel > MAX_REPLAY_LEVEL_VALUE){
            return MAX_REPLAY_LEVEL_VALUE;
        }
        return updateReplayLevel;
    }

}
