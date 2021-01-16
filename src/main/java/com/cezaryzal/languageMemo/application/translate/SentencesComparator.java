package com.cezaryzal.languageMemo.application.translate;

import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.application.model.SentenceModel;
import org.springframework.stereotype.Service;

@Service
public class SentencesComparator {

    public boolean comparingInputPhrasesWithPattern(
            TranslateComponentInput translateComponentInput,
            SentenceModel patternSentenceModel){
        return translateComponentInput.getPhrase().equalsIgnoreCase(patternSentenceModel.getLanguageEng());
    }
}
