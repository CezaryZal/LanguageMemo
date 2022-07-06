package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.config.ApiConstants;
import org.springframework.stereotype.Service;

@Service
public class FirstLetters {

    public String getPhraseWithFirstLetters(String phrase, String patternPhrase) {
        StringBuilder phraseWithFirstLetters = new StringBuilder();
        phraseWithFirstLetters.append(patternPhrase.charAt(0));

        for (int i = 1; i < phrase.length(); i++) {

            char characterToAppend = phrase.charAt(i - 1) != '_' ? patternPhrase.charAt(i) : ApiConstants.INCORRECT_LETTER;
            phraseWithFirstLetters.append(characterToAppend);

        }
        return String.valueOf(phraseWithFirstLetters);
    }
}