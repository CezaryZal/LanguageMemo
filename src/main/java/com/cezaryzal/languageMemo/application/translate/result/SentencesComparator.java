package com.cezaryzal.languageMemo.application.translate.result;

import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.application.model.SentenceModel;
import org.springframework.stereotype.Service;

@Service
public class SentencesComparator {

    public boolean comparingInputPhrasesWithPattern(String type,
                                                    TranslateComponentInput translateComponentInput,
                                                    SentenceModel patternSentenceModel){
        if (type.equals("toNative")){
            return comparingToNative(translateComponentInput, patternSentenceModel);
        } else if (type.equals("fromNative")) {
            return comparingFromNative(translateComponentInput, patternSentenceModel);
        } else throw new RuntimeException("unrecognized type");
    }

    private boolean comparingFromNative(
            TranslateComponentInput translateComponentInput,
            SentenceModel patternSentenceModel){
        return translateComponentInput.getPhrase().equalsIgnoreCase(patternSentenceModel.getLanguageEng());
    }

    private boolean comparingToNative(
            TranslateComponentInput translateComponentInput,
            SentenceModel patternSentenceModel){
        return translateComponentInput.getPhrase().equalsIgnoreCase(patternSentenceModel.getLanguagePol());
    }

}
