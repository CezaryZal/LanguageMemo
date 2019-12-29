package com.cezaryzal.manager.response.filter;

import org.springframework.stereotype.Service;

@Service
public class PhraseDivider {


    public String sharePhraseIntoWords(String phrase) {
        return phrase.replaceAll("[a-zA-Z]", "_");
    }
}
