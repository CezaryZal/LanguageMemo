package com.cezaryzal.languageMemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ComponentDtoOutput {

    private Long sentenceId;
    private String headerToTranslate;
    private String progressThroughLastTries;
    private boolean isCorrectAnswer;
    private int numberOfTries;
    private String hint;


}
