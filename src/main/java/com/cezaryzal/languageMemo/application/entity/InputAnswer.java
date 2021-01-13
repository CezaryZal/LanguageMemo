package com.cezaryzal.languageMemo.application.entity;

import lombok.Data;

@Data
public class InputAnswer {

    private Long sentenceId;
    private String phrase;
    private int numberOfTries;
}
