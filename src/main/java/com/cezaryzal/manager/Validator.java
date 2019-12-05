package com.cezaryzal.manager;

public class Validator {
    final char INCORRECT_LETTER = '_';
    StringBuilder progressSentence = new StringBuilder();
    String inputSentence;
    String correctSentence;

    public Validator(String inputSentence, String correctSentence) {
        this.inputSentence = inputSentence;
        this.correctSentence = correctSentence;
    }

    public String shareSentenceIntoWords (){
        return "";
    }

    public void filterSentences() {
        for (int i = 0; i < correctSentence.length(); i++) {
            insertLetterToProgressSentence(i);
        }
    }

    public void insertLetterToProgressSentence(int numberLetterOfSentenceWhichCompare){
        try {
            char letterToInsert = compareInputLettersWithExpected(
                    inputSentence.toLowerCase().charAt(numberLetterOfSentenceWhichCompare),
                    correctSentence.toLowerCase().charAt(numberLetterOfSentenceWhichCompare));
            progressSentence.append(letterToInsert);
        } catch (StringIndexOutOfBoundsException e) {
            progressSentence.append(INCORRECT_LETTER);
        }
    }

    //przenieść do oddzielnej klasy z 2 polami char?
    public char compareInputLettersWithExpected(char actualLetter, char expectedLetter){
        if (actualLetter == expectedLetter) {
            return expectedLetter;
        } else
            return INCORRECT_LETTER;
    }

    public String getProgressSentence (){
        return String.valueOf(progressSentence);
    }

}
