package com.cezaryzal.languageMemo.translate.components;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TranslateComponentDto {

    private Long sentenceId;
    private String headerToTranslate;
    private String progressThroughLastTries;
    private boolean isCorrectAnswer;
    private int numberOfTries;
    private String hint;


}
