package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.serviceRepo.SentenceService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NextSentenceDto {

    private Sentence nextRandomSentenceByTodayDate;

    private SentenceService sentenceService;

    public NextSentenceDto(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    public SentenceDTO getNextSentenceDto(boolean isCorrectAnswer) {

        nextRandomSentenceByTodayDate = getNextRandomSentenceByTodayDate();

        SentenceDTO nextSentenceDTO = changeSentenceToDto(isCorrectAnswer);
        return nextSentenceDTO;
    }

    private Sentence getNextRandomSentenceByTodayDate() {
        Optional<Sentence> nextRandomSentenceByTodayDate = sentenceService
                .findRandomFirstByReplayDateLessThanEqual("2019-12-15");

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
