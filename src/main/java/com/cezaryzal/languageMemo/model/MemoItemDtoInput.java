package com.cezaryzal.languageMemo.model;

import lombok.Data;

@Data
public class MemoItemDtoInput {

    private Long sentenceId;
    private String phrase;
    private int guess;
    private String type;
}
