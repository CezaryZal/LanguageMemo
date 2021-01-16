package com.cezaryzal.languageMemo.application.translate;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;

public abstract class SentenceComponentDataFiller {

     protected TranslateComponentDto createSentenceDtoFromSentenceFirstTry(boolean isCorrectAnswer, SentenceModel sentenceModel) {
        return TranslateComponentDto.builder()
                .sentenceId(sentenceModel.getId())
                .headerToTranslate(sentenceModel.getLanguagePol())
                .progressThroughLastTries("Pierwsza próba")
                .isCorrectAnswer(isCorrectAnswer)
                .numberOfTries(0)
                .hint(sentenceModel.getHintFromNative())
                .build();
    }
}
