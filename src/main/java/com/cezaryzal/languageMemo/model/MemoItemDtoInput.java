package com.cezaryzal.languageMemo.model;

import lombok.Data;

@Data
public class MemoItemDtoInput {

    private Long memoItemId;
    private String phrase;
    private int guessCounter;
    private String type;
}
