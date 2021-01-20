package com.cezaryzal.languageMemo.application.translate.result;

import com.cezaryzal.languageMemo.application.translate.RandomSentenceModelFromNativeByData;
import com.cezaryzal.languageMemo.application.translate.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextTranslateComponentDto extends SentenceComponentDataFiller {

    private final RandomSentenceModelFromNativeByData randomSentenceModelFromNativeByData;

    @Autowired
    public NextTranslateComponentDto(RandomSentenceModelFromNativeByData randomSentenceModelFromNativeByData) {
        this.randomSentenceModelFromNativeByData = randomSentenceModelFromNativeByData;
    }

    public TranslateComponentDto getNextSentenceDto(boolean isCorrectAnswer) {
        return createSentenceDtoFromSentenceFirstTry(isCorrectAnswer, randomSentenceModelFromNativeByData.getRandomSentenceByTodayDate());
    }

}
