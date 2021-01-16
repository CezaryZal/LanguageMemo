package com.cezaryzal.languageMemo.application.translate.result;

import com.cezaryzal.languageMemo.application.translate.RandomSentenceModelByData;
import com.cezaryzal.languageMemo.application.translate.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextTranslateComponentDto extends SentenceComponentDataFiller {

    private final RandomSentenceModelByData randomSentenceModelByData;

    @Autowired
    public NextTranslateComponentDto(RandomSentenceModelByData randomSentenceModelByData) {
        this.randomSentenceModelByData = randomSentenceModelByData;
    }

    public TranslateComponentDto getNextSentenceDto(boolean isCorrectAnswer) {
        return createSentenceDtoFromSentenceFirstTry(isCorrectAnswer, randomSentenceModelByData.getRandomSentenceByTodayDate());
    }

}
