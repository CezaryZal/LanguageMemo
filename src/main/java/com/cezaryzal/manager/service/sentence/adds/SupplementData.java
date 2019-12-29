package com.cezaryzal.manager.service.sentence.adds;

import com.cezaryzal.entity.InputSentence;
import com.cezaryzal.entity.Sentence;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
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
