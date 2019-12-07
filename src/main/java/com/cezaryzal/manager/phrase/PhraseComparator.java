package com.cezaryzal.manager.phrase;

import org.springframework.stereotype.Component;

@Component
public class PhraseComparator {

    private String inputPhrase;
    private String correctPhrase;

    public void setInputPhrase(String inputPhrase) {
        this.inputPhrase = inputPhrase;
    }

    public void setCorrectPhrase(String correctPhrase) {
        this.correctPhrase = correctPhrase;
    }

    public boolean comparingPhrases(){
        return inputPhrase.equalsIgnoreCase(correctPhrase);
    }
}
