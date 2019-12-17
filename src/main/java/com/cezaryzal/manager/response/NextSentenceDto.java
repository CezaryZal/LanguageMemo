package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.repository.SentenceRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class NextSentenceDto {

    private Sentence nextRandomSentenceByTodayDate;

    private SentenceRepo sentenceRepo;

    public NextSentenceDto(SentenceRepo sentenceRepo) {
        this.sentenceRepo = sentenceRepo;
    }

    public SentenceDTO getNextSentenceDto(boolean isCorrectAnswer) {
        nextRandomSentenceByTodayDate = getNextRandomSentenceByTodayDate();

        return changeSentenceToDto(isCorrectAnswer);
    }

    private Sentence getNextRandomSentenceByTodayDate() {
        Optional<Sentence> nextRandomSentenceByTodayDate = sentenceRepo
                .findRandomFirstByReplayDateLessThanEqual(LocalDate.now());

        return nextRandomSentenceByTodayDate.orElseThrow(
                () -> new RuntimeException("Brak kolajnych sformułowań do powtórzenia")
        );
    }

    private SentenceDTO changeSentenceToDto(boolean isCorrectAnswer) {
        return new SentenceDTO(
                nextRandomSentenceByTodayDate.getId(),
                nextRandomSentenceByTodayDate.getLanguagePol(),
                "Pierwsza próba",
                isCorrectAnswer,
                0,
                nextRandomSentenceByTodayDate.getHint()
        );
    }


}
