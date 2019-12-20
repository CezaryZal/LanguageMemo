package com.cezaryzal.manager.response.filter;

import org.springframework.stereotype.Component;

@Component
public class EverySecondLetter {
    final char INCORRECT_LETTER = '_';

    public String getPhraseWithEverySecondLetter(String phrase) {
        StringBuilder phraseWithEverySecondLetter = new StringBuilder();

        for (int i = 0; i < phrase.length(); i++) {

            char characterToAppend = i % 2 != 0 ? phrase.charAt(i) : INCORRECT_LETTER;
            phraseWithEverySecondLetter.append(characterToAppend);
        }
        return String.valueOf(phraseWithEverySecondLetter);
    }

}
