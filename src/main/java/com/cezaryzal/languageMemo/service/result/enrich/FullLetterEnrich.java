package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import org.springframework.stereotype.Service;

@Service
public class FullLetterEnrich implements Enricher{

    @Override
    public void enrichProgressPhrase(CurrentPlayedSentenceComponent currentlyPlayedCase) {
        currentlyPlayedCase
                .setProgressPhrase(
                        currentlyPlayedCase
                                .getUsedSentence()
                                .getCorrectAnswer()
                );
    }
}
