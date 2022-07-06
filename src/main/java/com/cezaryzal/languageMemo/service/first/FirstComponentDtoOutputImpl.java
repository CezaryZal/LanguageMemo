package com.cezaryzal.languageMemo.service.first;

import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.service.random.RandomSentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstComponentDtoOutputImpl extends SentenceComponentDataFiller implements FirstComponentDtoOutput {

    private final RandomSentence randomSentence;

    @Autowired
    public FirstComponentDtoOutputImpl(RandomSentence randomSentence) {
        this.randomSentence = randomSentence;
    }



    @Override
    public ComponentDtoOutput getFirstComponentDtoOutput() {
        Sentence randomSentenceByTodayDate = randomSentence.getRandomSentenceByTodayDate();

        return createComponentDtoFirstTry(true, randomSentenceByTodayDate);
    }
}
