package com.cezaryzal.manager.add;

import com.cezaryzal.entity.SentenceToAdd;
import com.cezaryzal.entity.Sentence;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SupplementData {

    public Sentence fillInMissingData (SentenceToAdd sentenceToAdd){
        return new Sentence(
                null,
                sentenceToAdd.getLanguageEng(),
                sentenceToAdd.getLanguagePol(),
                sentenceToAdd.getHint(),
                0,
                LocalDate.now());
    }

}
