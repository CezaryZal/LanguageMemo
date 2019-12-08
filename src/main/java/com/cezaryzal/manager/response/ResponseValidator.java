package com.cezaryzal.manager.response;

import com.cezaryzal.manager.serviceRepo.SentenceService;
import org.springframework.stereotype.Component;

@Component
public class ResponseValidator {

    private SentenceService sentenceService;
    private ResultComparator resultComparator;

    public ResponseValidator(SentenceService sentenceService, ResultComparator resultComparator) {
        this.sentenceService = sentenceService;
        this.resultComparator = resultComparator;
    }



}
