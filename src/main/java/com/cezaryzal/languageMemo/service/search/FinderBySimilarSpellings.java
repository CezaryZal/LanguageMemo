package com.cezaryzal.languageMemo.service.search;

import org.springframework.stereotype.Service;

@Service
public class FinderBySimilarSpellings {

    public String parseWordBasedOnLength(String string){
        int wordLength = string.length();

        if (wordLength<5)
            return string.substring(0, wordLength-1);
        if (wordLength<6)
            return string.substring(1, wordLength-1);
        if (wordLength<10)
            return string.substring(1, wordLength-2);
        else
            return string.substring(1, wordLength-3);
    }
}
