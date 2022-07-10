package com.cezaryzal.languageMemo.service.create;

import com.cezaryzal.languageMemo.model.AppendSentence;
import com.cezaryzal.languageMemo.repository.SentenceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

//TODO finish validation module
public class ValidatorNewSentence {

    private SentenceJpaRepository sentenceJpaRepository;

    @Autowired
    public ValidatorNewSentence(SentenceJpaRepository sentenceJpaRepository) {
        this.sentenceJpaRepository = sentenceJpaRepository;
    }

    public String validInputSentenceBeforeAdd (AppendSentence appendSentence){
        return "";
    }
}
