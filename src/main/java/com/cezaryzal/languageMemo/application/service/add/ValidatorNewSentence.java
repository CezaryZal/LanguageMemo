package com.cezaryzal.languageMemo.application.service.add;

import com.cezaryzal.languageMemo.application.entity.Sentence;
import com.cezaryzal.languageMemo.application.service.serviceTmp.repository.SentenceService;

public class ValidatorNewSentence {

    private SentenceService sentenceService;

    public ValidatorNewSentence(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    public String validInputSentenceBeforeAdd (Sentence inputSentence){
        return "";
    }
}
