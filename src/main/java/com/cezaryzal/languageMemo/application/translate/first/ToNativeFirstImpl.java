package com.cezaryzal.languageMemo.application.translate.first;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.translate.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.translate.random.RandomSentenceModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ToNativeFirstImpl extends SentenceComponentDataFiller implements First {

    private final RandomSentenceModel randomSentenceModel;

    public ToNativeFirstImpl(@Qualifier("toNativeRandomSentenceModel") RandomSentenceModel randomSentenceModel) {
        this.randomSentenceModel = randomSentenceModel;
    }

    @Override
    public TranslateComponentDto getFirstSentenceDto() {
        SentenceModel randomSentence = randomSentenceModel.getRandomSentenceByTodayDate();

        return createSentenceDtoFromSentenceFirstTry(true, randomSentence);
    }
}
