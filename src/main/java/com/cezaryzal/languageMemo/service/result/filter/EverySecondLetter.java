package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.config.ApiConstants;
import org.springframework.stereotype.Service;

@Service
public class EverySecondLetter {

    public String getPhraseWithEverySecondLetter(String phrase) {
        StringBuilder phraseWithEverySecondLetter = new StringBuilder();

        for (int i = 0; i < phrase.length(); i++) {

            char characterToAppend = i % 2 != 0 ? phrase.charAt(i) : ApiConstants.INCORRECT_LETTER;
            phraseWithEverySecondLetter.append(characterToAppend);
        }
        return String.valueOf(phraseWithEverySecondLetter);
    }
}
