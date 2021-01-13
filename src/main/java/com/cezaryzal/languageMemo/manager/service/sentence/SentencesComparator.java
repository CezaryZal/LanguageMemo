package com.cezaryzal.languageMemo.manager.service.sentence;

import com.cezaryzal.languageMemo.application.entity.InputAnswer;
import com.cezaryzal.languageMemo.application.entity.Sentence;
import org.springframework.stereotype.Service;

@Service
public class SentencesComparator {

    public boolean comparingInputPhrasesWithPattern(InputAnswer inputAnswer, Sentence patternSentence){
        return inputAnswer.getPhrase().equalsIgnoreCase(patternSentence.getLanguageEng());
    }
}
