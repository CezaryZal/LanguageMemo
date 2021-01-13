package com.cezaryzal.languageMemo.manager.service.sentence;

import com.cezaryzal.languageMemo.application.entity.Sentence;
import com.cezaryzal.languageMemo.application.entity.SentenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextSentenceDto {

    private final RandomSentenceFromDb randomSentenceFromDb;

    @Autowired
    public NextSentenceDto(RandomSentenceFromDb randomSentenceFromDb) {
        this.randomSentenceFromDb = randomSentenceFromDb;
    }

    public SentenceDTO getNextSentenceDto(boolean isCorrectAnswer) {
        return createSentenceDtoFromSentence(isCorrectAnswer, randomSentenceFromDb.getRandomSentenceByTodayDate());
    }

    private SentenceDTO createSentenceDtoFromSentence(boolean isCorrectAnswer, Sentence nextRandomSentenceByTodayDate) {
         return SentenceDTO.builder()
                 .sentenceId(nextRandomSentenceByTodayDate.getId())
                 .headerToTranslate(nextRandomSentenceByTodayDate.getLanguagePol())
                 .progressThroughLastTries("Pierwsza próba")
                 .isCorrectAnswer(isCorrectAnswer)
                 .numberOfTries(0)
                 .hint(nextRandomSentenceByTodayDate.getHint())
                 .build();
    }
}
