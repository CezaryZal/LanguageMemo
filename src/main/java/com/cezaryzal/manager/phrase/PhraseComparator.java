package com.cezaryzal.manager.phrase;

import org.springframework.stereotype.Component;

@Component
public class PhraseComparator {

    public boolean comparingPhrases(String inputPhrase, String correctPhrase){
        return inputPhrase.equalsIgnoreCase(correctPhrase);
    }
}
