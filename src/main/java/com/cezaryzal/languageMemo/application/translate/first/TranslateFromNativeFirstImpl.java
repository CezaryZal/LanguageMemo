package com.cezaryzal.languageMemo.application.translate.first;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.translate.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.translate.RandomSentenceModelFromNativeByData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslateFromNativeFirstImpl extends SentenceComponentDataFiller implements First{

    private final RandomSentenceModelFromNativeByData randomSentenceModelFromNativeByData;

    @Autowired
    public TranslateFromNativeFirstImpl(RandomSentenceModelFromNativeByData randomSentenceModelFromNativeByData) {
        this.randomSentenceModelFromNativeByData = randomSentenceModelFromNativeByData;
    }

    @Override
    public TranslateComponentDto getFirstSentenceDto() {
        SentenceModel randomSentenceModelByTodayDate = randomSentenceModelFromNativeByData.getRandomSentenceByTodayDate();

        return createSentenceDtoFromSentenceFirstTry(true, randomSentenceModelByTodayDate);
    }
}
