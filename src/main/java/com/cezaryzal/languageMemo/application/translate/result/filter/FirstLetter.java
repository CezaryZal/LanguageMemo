package com.cezaryzal.languageMemo.application.translate.result.filter;

import com.cezaryzal.languageMemo.config.ApiConstants;
import org.springframework.stereotype.Service;

@Service
public class FirstLetter {

    public String getPhraseWithFirstLetter(String phrase) {
        StringBuilder phraseWithFirstLetter = new StringBuilder();
        phraseWithFirstLetter.append(phrase.charAt(0));
        for (int i = 1; i < phrase.length(); i++){
            phraseWithFirstLetter.append(ApiConstants.INCORRECT_LETTER);
        }
        return String.valueOf(phraseWithFirstLetter);
    }
}
