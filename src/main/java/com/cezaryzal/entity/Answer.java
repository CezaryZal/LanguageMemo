package com.cezaryzal.entity;

import lombok.Data;

@Data
public class Answer {

    private Long sentenceId;
    private String phrase;
    private int numberOfTries;
}
