package com.cezaryzal.languageMemo.application.translate.add;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.reposervice.SentenceRepoService;

//TODO finish validation module
public class ValidatorNewSentence {

    private SentenceRepoService sentenceRepoService;

    public ValidatorNewSentence(SentenceRepoService sentenceRepoService) {
        this.sentenceRepoService = sentenceRepoService;
    }

    public String validInputSentenceBeforeAdd (SentenceModel inputSentenceModel){
        return "";
    }
}
