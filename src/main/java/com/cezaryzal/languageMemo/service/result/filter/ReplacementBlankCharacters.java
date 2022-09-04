package com.cezaryzal.languageMemo.service.result.filter;

import org.springframework.stereotype.Service;

@Service
public class ReplacementBlankCharacters {

    public String replaceLetterOnEmptyChar(String phrase) {
        return phrase.replaceAll("[a-zA-Z]", "_");
    }

    public String replaceAllSentenceOnEmptyChars(String phrase){
        return phrase.replaceAll("[^.]", "_");

    }
}
