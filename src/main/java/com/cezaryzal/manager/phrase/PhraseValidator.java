package com.cezaryzal.manager.phrase;

import org.springframework.stereotype.Component;

@Component
public class PhraseValidator {
    final char INCORRECT_LETTER = '_';
    StringBuilder progressSentence;
    String inputPhrase;
    String patternPhrase;

    public void setInputPhrase(String inputPhrase) {
        this.inputPhrase = inputPhrase;
    }

    public void setPatternPhrase(String patternPhrase) {
        this.patternPhrase = patternPhrase;
    }

    public String createProgressThroughLastTries(){
        progressSentence = new StringBuilder();
        filterSentences();
        return getProgressSentence();
    }

//    private String shareSentenceIntoWords (){
//        return "";
//    }

    private void filterSentences() {
        for (int i = 0; i < patternPhrase.length(); i++) {
            insertLetterToProgressSentence(i);
        }
    }

    private void insertLetterToProgressSentence(int numberLetterOfSentenceWhichCompare){
        try {
            char letterToInsert = compareInputLettersWithExpected(
                    inputPhrase.toLowerCase().charAt(numberLetterOfSentenceWhichCompare),
                    patternPhrase.toLowerCase().charAt(numberLetterOfSentenceWhichCompare));
            progressSentence.append(letterToInsert);
        } catch (StringIndexOutOfBoundsException e) {
            progressSentence.append(INCORRECT_LETTER);
        }
    }

    //przenieść do oddzielnej klasy z 2 polami char?
    private char compareInputLettersWithExpected(char actualLetter, char expectedLetter){
        if (actualLetter == expectedLetter) {
            return expectedLetter;
        } else
            return INCORRECT_LETTER;
    }

    private String getProgressSentence (){
        return String.valueOf(progressSentence);
    }

}
