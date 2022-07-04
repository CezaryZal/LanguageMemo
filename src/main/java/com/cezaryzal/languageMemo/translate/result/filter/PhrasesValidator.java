package com.cezaryzal.languageMemo.translate.result.filter;

import com.cezaryzal.languageMemo.config.ApiConstants;

public class PhrasesValidator {
    StringBuilder resultPhrase;

    String progressPhraseByInput;
    String patternPhrase;

    public PhrasesValidator(String progressPhraseByInput, String patternPhrase) {
        this.progressPhraseByInput = progressPhraseByInput;
        this.patternPhrase = patternPhrase;
    }

    public String validProgressPhraseByPatternAndFilter(String modifiedPhrase) {
        resultPhrase = new StringBuilder();
        for (int i = 0; i < patternPhrase.length(); i++) {
            compareAndInsertLetterToProgressSentence(i, modifiedPhrase);
        }
        return String.valueOf(resultPhrase);
    }

    private void compareAndInsertLetterToProgressSentence(int numberOfLetterCompared, String modifiedPhrase){
        if (modifiedPhrase.charAt(numberOfLetterCompared) == patternPhrase.charAt(numberOfLetterCompared)) {
            resultPhrase.append(modifiedPhrase.charAt(numberOfLetterCompared));
        } else if (progressPhraseByInput.charAt(numberOfLetterCompared) == patternPhrase.charAt(numberOfLetterCompared)){
            resultPhrase.append(progressPhraseByInput.charAt(numberOfLetterCompared));
        } else {
            resultPhrase.append(ApiConstants.INCORRECT_LETTER);
        }
    }
}
