package com.cezaryzal.languageMemo.application.translate.result;

import com.cezaryzal.languageMemo.application.translate.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.translate.random.RandomSentenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NextTranslateComponentDto extends SentenceComponentDataFiller {

    private final RandomSentenceModel fromNativeRandomSentenceModel;
    private final RandomSentenceModel toNativeRandomSentenceModel;

    @Autowired
    public NextTranslateComponentDto(
            @Qualifier("fromNativeRandomSentenceModel") RandomSentenceModel fromNativeRandomSentenceModel,
            @Qualifier("toNativeRandomSentenceModel") RandomSentenceModel toNativeRandomSentenceModel) {
        this.fromNativeRandomSentenceModel = fromNativeRandomSentenceModel;
        this.toNativeRandomSentenceModel = toNativeRandomSentenceModel;
    }

    public TranslateComponentDto getNextSentenceDtoFromNative(boolean isCorrectAnswer) {
        return createSentenceDtoFromSentenceFirstTry(
                isCorrectAnswer,
                fromNativeRandomSentenceModel.getRandomSentenceByTodayDate());
    }

    public TranslateComponentDto getNextSentenceDtoToNative(boolean isCorrectAnswer) {
        return createSentenceDtoFromSentenceFirstTry(
                isCorrectAnswer,
                toNativeRandomSentenceModel.getRandomSentenceByTodayDate());
    }

}
