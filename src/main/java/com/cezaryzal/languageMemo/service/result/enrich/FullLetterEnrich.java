package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import org.springframework.stereotype.Service;

@Service
public class FullLetterEnrich implements Enricher{

    @Override
    public void enrichProgressPhrase(CurrentPlayedMemoItem currentlyPlayedCase) {
        currentlyPlayedCase
                .setProgressPhrase(
                        currentlyPlayedCase
                                .getUsedMemoItem()
                                .getCorrectAnswer()
                );
    }
}
