package com.cezaryzal.languageMemo.service.first;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.service.random.RandomSentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstComponentDtoOutputImpl extends SentenceComponentDataFiller implements FirstComponentDtoOutput {

    private final RandomSentence randomSentence;
    private final CurrentPlayedSentenceComponent componentData;

    @Autowired
    public FirstComponentDtoOutputImpl(RandomSentence randomSentence,
                                       CurrentPlayedSentenceComponent componentData) {
        this.randomSentence = randomSentence;
        this.componentData = componentData;
    }


    @Override
    public MemoItemDtoOutput getFirstComponentDtoOutput() {
        Sentence randomSentenceByTodayDate = randomSentence.getRandomSentenceByTodayDate();
        componentData.initialProgressPhrase(randomSentenceByTodayDate);

        return createComponentDtoFirstTry(true, "First try", randomSentenceByTodayDate);
    }
}
