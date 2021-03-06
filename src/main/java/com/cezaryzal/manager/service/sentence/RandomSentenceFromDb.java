package com.cezaryzal.manager.service.sentence;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.repository.SentenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RandomSentenceFromDb {

    private SentenceRepo sentenceRepo;

    @Autowired
    public RandomSentenceFromDb(SentenceRepo sentenceRepo) {
        this.sentenceRepo = sentenceRepo;
    }

    public Sentence getRandomSentenceByTodayDate() {
        Optional<Sentence> nextRandomSentenceByTodayDate = sentenceRepo
                .findRandomFirstByReplayDateLessThanEqual(LocalDate.now());

        return nextRandomSentenceByTodayDate
                .orElseThrow(() -> new RuntimeException("Brak kolajnych sformułowań do powtórzenia")
        );
    }

}
