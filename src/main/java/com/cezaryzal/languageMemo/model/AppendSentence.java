package com.cezaryzal.languageMemo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppendSentence {

    private String clues;
    private String correctAnswer;
    private String hint;
    private String exampleOfUse;
    private String partOfSpeechClues;
    private String similarWord;

}
