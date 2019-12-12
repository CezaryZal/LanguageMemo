package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;

public class ResultComparator {

    private Answer inputAnswer;
    private Sentence patternSentence;

    public ResultComparator(Answer inputAnswer, Sentence patternSentence) {
        this.inputAnswer = inputAnswer;
        this.patternSentence = patternSentence;
    }

    public boolean comparingInputPhrasesWithPattern(){
        return inputAnswer.getPhrase().equalsIgnoreCase(patternSentence.getLanguageEng());
    }
}
