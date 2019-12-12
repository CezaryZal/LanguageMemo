package com.cezaryzal.manager.filter;

import org.springframework.stereotype.Component;

@Component
public class PhraseSplitter {

    public String shareSentenceIntoWords(String phrase) {

        return phrase.replaceAll("[a-zA-Z]", "_");
    }



//    public String parsePhraseToShowLength(String phrase) {
//        StringBuilder modifyPhrase = new StringBuilder();
//        for (int i = 0; i<phrase.length(); i++){
//            modifyPhrase.append("_");
//        }
//        return String.valueOf(modifyPhrase);
//    }
}
