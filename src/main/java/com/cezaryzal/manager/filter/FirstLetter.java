package com.cezaryzal.manager.filter;

import org.springframework.stereotype.Component;

@Component
public class FirstLetter {
    final char INCORRECT_LETTER = '_';

    public String getPhraseWithFirstLetter(String phrase) {
        StringBuilder phraseWithFirstLetter = new StringBuilder();
        phraseWithFirstLetter.append(phrase.charAt(0));
        for (int i = 1; i < phrase.length(); i++){
            phraseWithFirstLetter.append(INCORRECT_LETTER);
        }
        return String.valueOf(phraseWithFirstLetter);
    }
}
