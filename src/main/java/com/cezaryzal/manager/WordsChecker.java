package com.cezaryzal.manager;

public class WordsChecker {

    private String correctWord = "home";

    public boolean checking (String inputWord){
        return inputWord.equals(correctWord);
    }
}
