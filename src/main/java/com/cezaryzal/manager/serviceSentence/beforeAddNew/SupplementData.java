package com.cezaryzal.manager.serviceSentence.beforeAddNew;

import com.cezaryzal.entity.InputSentence;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.manager.serviceRepo.SentenceService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SupplementData {

    private SentenceService sentenceService;

    public SupplementData(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    public Sentence fillInMissingData (InputSentence inputSentence){
        return new Sentence(
                null,
                inputSentence.getLanguageEng(),
                inputSentence.getLanguagePol(),
                inputSentence.getHint(),
                0,
                LocalDate.now());
    }

    public Sentence addNewSentenceThroughInputSentence (InputSentence inputSentence){
        return sentenceService.addNewSentence(fillInMissingData(inputSentence));
    }

}
