package com.cezaryzal.manager.response.filter;

import org.springframework.stereotype.Component;

@Component
public class FirstLetters {
    final char INCORRECT_LETTER = '_';
    String patternPhrase;

    public void setPatternPhrase(String patternPhrase) {
        this.patternPhrase = patternPhrase;
    }

    public String getPhraseWithFirstLetters(String phrase) {
        StringBuilder phraseWithFirstLetters = new StringBuilder();
        phraseWithFirstLetters.append(patternPhrase.charAt(0));

        for (int i = 1; i < phrase.length(); i++) {

            char characterToAppend = phrase.charAt(i - 1) != '_' ? patternPhrase.charAt(i) : INCORRECT_LETTER;
            phraseWithFirstLetters.append(characterToAppend);

        }
        return String.valueOf(phraseWithFirstLetters);
    }
}
