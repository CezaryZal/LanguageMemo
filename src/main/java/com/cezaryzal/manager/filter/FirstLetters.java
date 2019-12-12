package com.cezaryzal.manager.filter;

import org.springframework.stereotype.Component;

@Component
public class FirstLetters {
    final char INCORRECT_LETTER = '_';

    public String getPhraseWithFirstLetters(String phrase){
        StringBuilder phraseWithFirstLetters = new StringBuilder();

        for (int i = 1; i < phrase.length(); i++){
            if (phrase.charAt(i-1) != '_'){
                phraseWithFirstLetters.append(phrase.charAt(i));
            }
            phraseWithFirstLetters.append(INCORRECT_LETTER);
        }
        return String.valueOf(phraseWithFirstLetters);
    }
}
