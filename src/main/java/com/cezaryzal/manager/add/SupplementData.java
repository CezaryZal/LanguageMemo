package com.cezaryzal.manager.add;

import com.cezaryzal.entity.AddSentence;
import com.cezaryzal.entity.Sentence;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SupplementData {

    public Sentence fillInMissingData (AddSentence addSentence){
        return new Sentence(
                null,
                addSentence.getLanguageEng(),
                addSentence.getLanguagePol(),
                addSentence.getHint(),
                0,
                LocalDate.now());
    }

}
