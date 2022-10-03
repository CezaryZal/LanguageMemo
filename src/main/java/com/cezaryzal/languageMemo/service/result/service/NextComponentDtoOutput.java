package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.service.random.RandomSentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextComponentDtoOutput extends SentenceComponentDataFiller {

    private final RandomSentence randomSentence;
    private final CurrentPlayedSentenceComponent componentData;

    @Autowired
    public NextComponentDtoOutput(RandomSentence randomSentence,
                                  CurrentPlayedSentenceComponent componentData) {
        this.randomSentence = randomSentence;
        this.componentData = componentData;
    }

    public MemoItemDtoOutput getNextComponentDtoOutput(boolean isCorrectAnswer) {
        String lastSentence = componentData.getUsedSentence().getClues() + "-" +
        componentData.getUsedSentence().getCorrectAnswer();

        Sentence randomSentenceByTodayDate = randomSentence.getRandomSentenceByTodayDate();
        componentData.initialProgressPhrase(randomSentenceByTodayDate);

        return createComponentDtoFirstTry(
                isCorrectAnswer,
                lastSentence,
                randomSentenceByTodayDate);
    }
}
