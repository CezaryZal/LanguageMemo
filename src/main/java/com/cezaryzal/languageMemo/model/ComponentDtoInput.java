package com.cezaryzal.languageMemo.model;

import lombok.Data;

@Data
public class ComponentDtoInput {

    private Long sentenceId;
    private String phrase;
    private int numberOfTries;
    private String type;
}
