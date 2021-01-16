package com.cezaryzal.languageMemo.application.translate.first;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.translate.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.translate.RandomSentenceModelByData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslateFromNativeFirstImpl extends SentenceComponentDataFiller implements First{

    private final RandomSentenceModelByData randomSentenceModelByData;

    @Autowired
    public TranslateFromNativeFirstImpl(RandomSentenceModelByData randomSentenceModelByData) {
        this.randomSentenceModelByData = randomSentenceModelByData;
    }

    @Override
    public TranslateComponentDto getFirstSentenceDto() {
        SentenceModel randomSentenceModelByTodayDate = randomSentenceModelByData.getRandomSentenceByTodayDate();

        return createSentenceDtoFromSentenceFirstTry(true, randomSentenceModelByTodayDate);
    }
}
