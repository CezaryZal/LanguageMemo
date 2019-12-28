package com.cezaryzal.manager.service.sentence;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;

public class SentencesComparator {

    private Answer inputAnswer;
    private Sentence patternSentence;

    public SentencesComparator(Answer inputAnswer, Sentence patternSentence) {
        this.inputAnswer = inputAnswer;
        this.patternSentence = patternSentence;
    }

    public boolean comparingInputPhrasesWithPattern(){
        return inputAnswer.getPhrase().equalsIgnoreCase(patternSentence.getLanguageEng());
    }
}
