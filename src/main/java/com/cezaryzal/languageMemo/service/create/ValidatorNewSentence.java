package com.cezaryzal.languageMemo.service.create;

import com.cezaryzal.languageMemo.model.AppendSentence;
import com.cezaryzal.languageMemo.repository.SentenceRepository;
import org.springframework.beans.factory.annotation.Autowired;

//TODO finish validation module
public class ValidatorNewSentence {

    private SentenceRepository sentenceRepository;

    @Autowired
    public ValidatorNewSentence(SentenceRepository sentenceRepository) {
        this.sentenceRepository = sentenceRepository;
    }

    public String validInputSentenceBeforeAdd (AppendSentence appendSentence){
        return "";
    }
}
