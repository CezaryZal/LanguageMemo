package com.cezaryzal.languageMemo.application.translate.first;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.translate.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.translate.random.RandomSentenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FromNativeFirstImpl extends SentenceComponentDataFiller implements First{

    private final RandomSentenceModel randomSentenceModel;

    @Autowired
    public FromNativeFirstImpl(@Qualifier("fromNativeRandomSentenceModel") RandomSentenceModel randomSentenceModel) {
        this.randomSentenceModel = randomSentenceModel;
    }

    @Override
    public TranslateComponentDto getFirstSentenceDto() {
        SentenceModel randomSentenceModelByTodayDate = randomSentenceModel.getRandomSentenceByTodayDate();

        return createSentenceDtoFromSentenceFirstTry(true, randomSentenceModelByTodayDate);
    }
}
