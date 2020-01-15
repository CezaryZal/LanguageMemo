package com.cezaryzal.entity;

import lombok.Data;

@Data
public class InputAnswer {

    private Long sentenceId;
    private String phrase;
    private int numberOfTries;
}
