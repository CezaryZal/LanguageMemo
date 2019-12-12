package com.cezaryzal.manager.filter;

import org.springframework.stereotype.Component;

@Component
public class EverySecondLetter {
    final char INCORRECT_LETTER = '_';

    public String getPhraseWithEverySecondLetter(String phrase){
        StringBuilder phraseWithEverySecondLetter = new StringBuilder();

        for (int i = 0; i < phrase.length(); i++){
            if (i%2 != 0){
                phraseWithEverySecondLetter.append(phrase.charAt(i));
            } else {
                phraseWithEverySecondLetter.append(INCORRECT_LETTER);
            }
        }
        return String.valueOf(phraseWithEverySecondLetter);
    }

}
