package com.cezaryzal.languageMemo.service.create;

import com.cezaryzal.languageMemo.model.ModelToCreateMemoItem;
import com.cezaryzal.languageMemo.repository.SentenceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

//TODO finish validation module
public class ValidatorNewMemoItem {

    private SentenceJpaRepository sentenceJpaRepository;

    @Autowired
    public ValidatorNewMemoItem(SentenceJpaRepository sentenceJpaRepository) {
        this.sentenceJpaRepository = sentenceJpaRepository;
    }

    public String validInputMemoItemBeforeAdd(ModelToCreateMemoItem modelToCreateMemoItem){
        return "";
    }
}
