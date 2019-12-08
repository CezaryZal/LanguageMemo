package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import org.springframework.stereotype.Component;

@Component
public class ResultComparator {

    private Answer inputAnswer;
    private Sentence patternSentence;

    public void setInputAnswer(Answer inputAnswer) {
        this.inputAnswer = inputAnswer;
    }

    public void setPatternSentence(Sentence patternSentence) {
        this.patternSentence = patternSentence;
    }

    public boolean comparingInputPhrasesWithPattern(){
        return inputAnswer.getInputPhrase().equalsIgnoreCase(patternSentence.getLanguageEng());
    }
}
