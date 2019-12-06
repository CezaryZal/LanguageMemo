package com.cezaryzal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SentenceDTO {

    private Long sentenceId;
    private String displayHeaderToTranslate;
    private String displayResultFromLastTries;
    private int numberOfTries;
    private String hint;


}
