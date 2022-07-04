package com.cezaryzal.languageMemo.translate.first;

import com.cezaryzal.languageMemo.model.SentenceModel;
import com.cezaryzal.languageMemo.translate.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.translate.random.RandomSentenceModel;
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

        return createSentenceDtoToSentenceFirstTry(true, randomSentence);
    }
}
