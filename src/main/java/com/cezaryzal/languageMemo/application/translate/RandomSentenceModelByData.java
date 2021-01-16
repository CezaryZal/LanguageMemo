package com.cezaryzal.languageMemo.application.translate;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.reposervice.SentenceRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RandomSentenceModelByData {

    private final SentenceRepoService sentenceRepoService;

    @Autowired
    public RandomSentenceModelByData(SentenceRepoService sentenceRepoService) {
        this.sentenceRepoService = sentenceRepoService;
    }


    public SentenceModel getRandomSentenceByTodayDate() {
        Optional<SentenceModel> nextRandomSentenceByTodayDate = sentenceRepoService.
                findRandomFirstByReplayDateLessThanEqual(LocalDate.now());

        return nextRandomSentenceByTodayDate
                .orElseThrow(() -> new RuntimeException("Brak kolajnych sformułowań do powtórzenia")
        );
    }

}
