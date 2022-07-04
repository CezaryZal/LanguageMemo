package com.cezaryzal.languageMemo.translate.add;

import com.cezaryzal.languageMemo.model.SentenceModel;
import com.cezaryzal.languageMemo.repository.service.RepoService;

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
