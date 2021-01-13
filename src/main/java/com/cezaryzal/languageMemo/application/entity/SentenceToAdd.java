package com.cezaryzal.languageMemo.application.entity;

import lombok.Data;

@Data
public class SentenceToAdd {

    private String languageEng;
    private String languagePol;
    private String hintFromNative;
    private String hintToNative;

}
