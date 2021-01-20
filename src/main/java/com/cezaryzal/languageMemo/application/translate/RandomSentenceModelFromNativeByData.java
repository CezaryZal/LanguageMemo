package com.cezaryzal.languageMemo.application.translate;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.reposervice.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RandomSentenceModelFromNativeByData {

    private final RepoService repoService;

    @Autowired
    public RandomSentenceModelFromNativeByData(@Qualifier("fromNativeRepoServiceImp") RepoService repoService) {
        this.repoService = repoService;
    }


    public SentenceModel getRandomSentenceByTodayDate() {
        Optional<SentenceModel> nextRandomSentenceByTodayDate = repoService.
                findRandomFirstByReplayDateLessThanEqual(LocalDate.now());

        return nextRandomSentenceByTodayDate
                .orElseThrow(() -> new RuntimeException("Brak kolajnych sformułowań do powtórzenia")
        );
    }

}
