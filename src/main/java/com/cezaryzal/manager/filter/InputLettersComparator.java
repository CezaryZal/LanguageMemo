package com.cezaryzal.manager.filter;

public class InputLettersComparator {
    final char INCORRECT_LETTER = '_';
    StringBuilder progressSentence;
    String inputPhrase;
    String patternPhrase;

    public InputLettersComparator(String inputPhrase, String patternPhrase) {
        this.inputPhrase = inputPhrase;
        this.patternPhrase = patternPhrase;
    }

    public String createProgressThroughLastTries(){
        progressSentence = new StringBuilder();
        filterLitersInSentence();
        return getProgressSentence();
    }

    private void filterLitersInSentence() {
        for (int i = 0; i < patternPhrase.length(); i++) {
            insertLetterToProgressSentence(i);
        }
    }
// metoda dopisująca '_' do inpustu gdy jest krótszy od wzorcowego (Ale po metodzie rozdzielającej wyrazy!!!
    private void insertLetterToProgressSentence(int numberOfLetterCompared){
        try {
            char letterToInsert = compareInputLettersWithExpected(
                    inputPhrase.toLowerCase().charAt(numberOfLetterCompared),
                    patternPhrase.toLowerCase().charAt(numberOfLetterCompared));
            progressSentence.append(letterToInsert);
        } catch (StringIndexOutOfBoundsException e) {
            progressSentence.append(INCORRECT_LETTER);
        }
    }

    //przenieść do oddzielnej klasy z 2 polami char?
    private char compareInputLettersWithExpected(char actualLetter, char expectedLetter) {
        if (actualLetter == expectedLetter) {
            return expectedLetter;
        }
        return INCORRECT_LETTER;
    }

    private String getProgressSentence() {
        return String.valueOf(progressSentence);
    }

}
