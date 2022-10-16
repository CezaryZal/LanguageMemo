package com.cezaryzal.languageMemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemoItemDtoOutput {

    private Long memoItemId;
    private String headerToTranslate;
    private String progressThroughLastTries;
    private String lastMemoItem;
    private boolean isCorrectAnswer;
    private int guess;
    private String hint;


}
