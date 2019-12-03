package com.cezaryzal.manager;

public class SentenceChecker {

    public boolean checking (String inputSentence, String correctSentence){
        return inputSentence.equalsIgnoreCase(correctSentence);
    }
}
