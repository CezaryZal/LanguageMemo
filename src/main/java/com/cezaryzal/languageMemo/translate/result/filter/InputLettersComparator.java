package com.cezaryzal.languageMemo.translate.result.filter;

import com.cezaryzal.languageMemo.config.ApiConstants;

public class InputLettersComparator {
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
            progressSentence.append(ApiConstants.INCORRECT_LETTER);
        }
    }

    private char compareInputLettersWithExpected(char actualLetter, char expectedLetter) {
        return actualLetter == expectedLetter ? expectedLetter : ApiConstants.INCORRECT_LETTER;
    }

    private String getProgressSentence() {
        return String.valueOf(progressSentence);
    }
}
