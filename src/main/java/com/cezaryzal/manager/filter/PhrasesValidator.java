package com.cezaryzal.manager.filter;

public class PhrasesValidator {
    final char INCORRECT_LETTER = '_';
    StringBuilder resultPhrase;
    String modifiedPhrase;

    String progressPhraseByInput;
    String patternPhrase;

    public PhrasesValidator(String progressPhraseByInput, String patternPhrase) {
        this.progressPhraseByInput = progressPhraseByInput;
        this.patternPhrase = patternPhrase;
    }

    public void setModifiedPhrase(String modifiedPhrase) {
        this.modifiedPhrase = modifiedPhrase;
    }

    public String validProgressPhraseByPatternAndFilter() {
        resultPhrase = new StringBuilder();
        for (int i = 0; i < patternPhrase.length(); i++) {
            compareAndInsertLetterToProgressSentence(i);
        }
        return String.valueOf(resultPhrase);
    }

    public void compareAndInsertLetterToProgressSentence(int numberOfLetterCompared){
        if (modifiedPhrase.charAt(numberOfLetterCompared) == patternPhrase.charAt(numberOfLetterCompared)) {
            resultPhrase.append(modifiedPhrase.charAt(numberOfLetterCompared));
        } else if (progressPhraseByInput.charAt(numberOfLetterCompared) == patternPhrase.charAt(numberOfLetterCompared)){
            resultPhrase.append(progressPhraseByInput.charAt(numberOfLetterCompared));
        } else {
            resultPhrase.append(INCORRECT_LETTER);
        }
    }
}
