package com.cezaryzal.manager.service.sentence.beforeAddNew;

import com.cezaryzal.entity.InputSentence;
import com.cezaryzal.entity.Sentence;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SupplementData {

    public Sentence fillInMissingData (InputSentence inputSentence){
        return new Sentence(
                null,
                inputSentence.getLanguageEng(),
                inputSentence.getLanguagePol(),
                inputSentence.getHint(),
                0,
                LocalDate.now());
    }

}
