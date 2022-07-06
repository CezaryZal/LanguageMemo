package com.cezaryzal.languageMemo.service.random;

import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RandomSentenceImpl implements RandomSentence{

    private final RepositorySentenceService sentenceService;

    @Autowired
    public RandomSentenceImpl(RepositorySentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @Override
    public Sentence getRandomSentenceByTodayDate() {
        Optional<Sentence> randomSentenceByTodayDate = sentenceService
                .getRandomByReplayDateLessThanEqual(LocalDate.now());
        return randomSentenceByTodayDate.orElseThrow(
                ()-> new RuntimeException("Brak kolajnych sformułowań do powtórzenia"));
    }
}
