package com.cezaryzal.languageMemo.application.translate.add;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.reposervice.RepoService;

//TODO finish validation module
public class ValidatorNewSentence {

    private RepoService repoService;

    public ValidatorNewSentence(RepoService repoService) {
        this.repoService = repoService;
    }

    public String validInputSentenceBeforeAdd (SentenceModel inputSentenceModel){
        return "";
    }
}
