package com.cezaryzal.languageMemo.service;

import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;

public abstract class SentenceComponentDataFiller {

     protected ComponentDtoOutput createComponentDtoFirstTry(boolean isCorrectAnswer, Sentence sentence) {
        return ComponentDtoOutput.builder()
                .sentenceId(sentence.getId())
                .headerToTranslate(sentence.getClues())
                .progressThroughLastTries("Pierwsza próba")
                .isCorrectAnswer(isCorrectAnswer)
                .numberOfTries(0)
                .hint(sentence.getHint())
                .build();
    }
}
