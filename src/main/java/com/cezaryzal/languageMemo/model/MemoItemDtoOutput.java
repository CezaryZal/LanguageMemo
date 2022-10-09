package com.cezaryzal.languageMemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemoItemDtoOutput {

    private Long sentenceId;
    private String headerToTranslate;
    private String progressThroughLastTries;
    private String lastSentence;
    private boolean isCorrectAnswer;
    private int guess;
    private String hint;


}
