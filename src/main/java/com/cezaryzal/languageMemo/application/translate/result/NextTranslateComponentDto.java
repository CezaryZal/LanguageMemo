package com.cezaryzal.languageMemo.application.translate.result;

import com.cezaryzal.languageMemo.application.translate.random.FromNativeRandomSentenceModel;
import com.cezaryzal.languageMemo.application.translate.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextTranslateComponentDto extends SentenceComponentDataFiller {

    private final FromNativeRandomSentenceModel fromNativeRandomSentenceModel;

    @Autowired
    public NextTranslateComponentDto(FromNativeRandomSentenceModel fromNativeRandomSentenceModel) {
        this.fromNativeRandomSentenceModel = fromNativeRandomSentenceModel;
    }

    public TranslateComponentDto getNextSentenceDto(boolean isCorrectAnswer) {
        return createSentenceDtoFromSentenceFirstTry(isCorrectAnswer, fromNativeRandomSentenceModel.getRandomSentenceByTodayDate());
    }

}
