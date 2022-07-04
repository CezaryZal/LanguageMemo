package com.cezaryzal.languageMemo.translate.components;

import lombok.Data;

@Data
public class TranslateComponentInput {

    private Long sentenceId;
    private String phrase;
    private int numberOfTries;
    private String type;
}
