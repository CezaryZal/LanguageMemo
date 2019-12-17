package com.cezaryzal.manager.serviceSentence;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import org.springframework.stereotype.Component;

@Component
public class NextSentenceDto {

    private Sentence nextRandomSentenceByTodayDate;

    private RandomSentence randomSentence;

    public NextSentenceDto(RandomSentence randomSentence) {
        this.randomSentence = randomSentence;
    }

    public SentenceDTO getNextSentenceDto(boolean isCorrectAnswer) {
        nextRandomSentenceByTodayDate = randomSentence.getRandomSentenceByTodayDate();

        return createSentenceDtoFromSentence(isCorrectAnswer);
    }

    private SentenceDTO createSentenceDtoFromSentence(boolean isCorrectAnswer) {
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
