package com.cezaryzal.manager.service.sentence;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import org.springframework.stereotype.Service;

@Service
public class SentencesComparator {

    public boolean comparingInputPhrasesWithPattern(Answer inputAnswer, Sentence patternSentence){
        return inputAnswer.getPhrase().equalsIgnoreCase(patternSentence.getLanguageEng());
    }
}
