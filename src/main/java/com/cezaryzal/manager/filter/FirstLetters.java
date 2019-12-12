package com.cezaryzal.manager.filter;

import org.springframework.stereotype.Component;

@Component
public class FirstLetters {
    final char INCORRECT_LETTER = '_';
    String patternPhrase;

    public void setPatternPhrase(String patternPhrase) {
        this.patternPhrase = patternPhrase;
    }

    public String getPhraseWithFirstLetters(String phrase){
        StringBuilder phraseWithFirstLetters = new StringBuilder();
        phraseWithFirstLetters.append(patternPhrase.charAt(0));

        for (int i = 1; i < phrase.length(); i++){
            if (phrase.charAt(i-1) != '_'){
                phraseWithFirstLetters.append(patternPhrase.charAt(i));
            } else {
                phraseWithFirstLetters.append(INCORRECT_LETTER);
            }
        }
        return String.valueOf(phraseWithFirstLetters);
    }
}
