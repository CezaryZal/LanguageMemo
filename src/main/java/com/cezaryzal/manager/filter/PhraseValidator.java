package com.cezaryzal.manager.filter;

public class PhraseValidator {
    StringBuilder resultPhrase;

    String progressPhrase;
    String splitterPhrase;
    String patternPhrase;

    public PhraseValidator(String progressPhrase, String patternPhrase) {
        this.progressPhrase = progressPhrase;
        this.patternPhrase = patternPhrase;
    }

    public String validInputPhraseByLetterComparatorAndPhraseSplitter(String splitterPhrase) {
        this.splitterPhrase = splitterPhrase;
        resultPhrase = new StringBuilder();
        for (int i = 0; i < patternPhrase.length(); i++) {
            compareAndInsertLetterToProgressSentence(i);
        }
        return String.valueOf(resultPhrase);
    }

    public void compareAndInsertLetterToProgressSentence(int numberOfLetterCompared){
        if (splitterPhrase.charAt(numberOfLetterCompared) == patternPhrase.charAt(numberOfLetterCompared)) {
            resultPhrase.append(splitterPhrase.charAt(numberOfLetterCompared));
        } else if (progressPhrase.charAt(numberOfLetterCompared) == patternPhrase.charAt(numberOfLetterCompared)){
            resultPhrase.append(progressPhrase.charAt(numberOfLetterCompared));
        } else {
            resultPhrase.append("_");
        }
    }
}
