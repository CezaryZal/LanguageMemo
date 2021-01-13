package com.cezaryzal.languageMemo.application.service.serviceTmp.sentence;

import com.cezaryzal.languageMemo.application.entity.Sentence;
import com.cezaryzal.languageMemo.application.entity.SentenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextSentenceDto {

    private RandomSentenceFromDb randomSentenceFromDb;

    @Autowired
    public NextSentenceDto(RandomSentenceFromDb randomSentenceFromDb) {
        this.randomSentenceFromDb = randomSentenceFromDb;
    }

    public SentenceDTO getNextSentenceDto(boolean isCorrectAnswer) {
        return createSentenceDtoFromSentence(isCorrectAnswer, randomSentenceFromDb.getRandomSentenceByTodayDate());
    }

    private SentenceDTO createSentenceDtoFromSentence(boolean isCorrectAnswer, Sentence nextRandomSentenceByTodayDate) {
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
