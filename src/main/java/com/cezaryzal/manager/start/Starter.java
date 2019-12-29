package com.cezaryzal.manager.start;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.service.sentence.RandomSentenceFromDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Starter {

    private RandomSentenceFromDb randomSentenceFromDb;

    @Autowired
    public Starter(RandomSentenceFromDb randomSentenceFromDb) {
        this.randomSentenceFromDb = randomSentenceFromDb;
    }

    public SentenceDTO getFirstSentenceDto() {
        Sentence randomSentenceByTodayDate = randomSentenceFromDb.getRandomSentenceByTodayDate();

        return createSentenceDtoFromSentence(randomSentenceByTodayDate);
    }

    private SentenceDTO createSentenceDtoFromSentence(Sentence sentence) {
        return new SentenceDTO(
                sentence.getId(),
                sentence.getLanguagePol(),
                "Pierwsza próba",
                true,
                0,
                sentence.getHint()
        );
    }
}
