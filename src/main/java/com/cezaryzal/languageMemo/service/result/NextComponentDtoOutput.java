package com.cezaryzal.languageMemo.service.result;

import com.cezaryzal.languageMemo.service.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.service.random.RandomSentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextComponentDtoOutput extends SentenceComponentDataFiller {

    private final RandomSentence randomSentence;

    @Autowired
    public NextComponentDtoOutput(RandomSentence randomSentence) {
        this.randomSentence = randomSentence;
    }

    public ComponentDtoOutput getNextComponentDtoOutput(boolean isCorrectAnswer) {
        return createComponentDtoFirstTry(
                isCorrectAnswer,
                randomSentence.getRandomSentenceByTodayDate());
    }
}
