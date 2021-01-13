package com.cezaryzal.languageMemo.application.service.add;

import com.cezaryzal.languageMemo.application.entity.Sentence;
import com.cezaryzal.languageMemo.application.entity.SentenceToAdd;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SupplementData {

    public Sentence fillInMissingData (SentenceToAdd sentenceToAdd){
        return new Sentence(
                null,
                sentenceToAdd.getLanguageEng(),
                sentenceToAdd.getLanguagePol(),
                sentenceToAdd.getHintFromNative(),
                0,
                LocalDate.now());
    }

}
