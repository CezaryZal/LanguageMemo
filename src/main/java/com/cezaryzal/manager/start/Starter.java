package com.cezaryzal.manager.start;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.serviceSentence.RandomSentence;
import org.springframework.stereotype.Service;

@Service
public class Starter {

    private RandomSentence randomSentence;

    public Starter(RandomSentence randomSentence) {
        this.randomSentence = randomSentence;
    }

    public SentenceDTO getFirstSentenceDto() {
        Sentence randomSentenceByTodayDate = randomSentence.getRandomSentenceByTodayDate();

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
