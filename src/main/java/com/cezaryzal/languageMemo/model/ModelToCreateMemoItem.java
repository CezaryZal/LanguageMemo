package com.cezaryzal.languageMemo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelToCreateMemoItem {

    private String clues;
    private String correctAnswer;
    private String hint;
    private String exampleOfUse;
    private String partOfSpeechClues;
    private String similarWord;

}
