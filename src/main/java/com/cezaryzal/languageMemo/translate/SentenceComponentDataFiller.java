package com.cezaryzal.languageMemo.translate;

import com.cezaryzal.languageMemo.model.SentenceModel;
import com.cezaryzal.languageMemo.translate.components.TranslateComponentDto;

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

    protected TranslateComponentDto createSentenceDtoToSentenceFirstTry(boolean isCorrectAnswer, SentenceModel sentenceModel) {
        return TranslateComponentDto.builder()
                .sentenceId(sentenceModel.getId())
                .headerToTranslate(sentenceModel.getLanguageEng())
                .progressThroughLastTries("Pierwsza próba")
                .isCorrectAnswer(isCorrectAnswer)
                .numberOfTries(0)
                .hint(sentenceModel.getHintToNative())
                .build();
    }
}
