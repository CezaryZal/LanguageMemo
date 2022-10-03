package com.cezaryzal.languageMemo.service;

import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;

public abstract class SentenceComponentDataFiller {

     protected MemoItemDtoOutput createComponentDtoFirstTry(boolean isCorrectAnswer,
                                                            String lastSentence,
                                                            Sentence sentence) {
        return MemoItemDtoOutput.builder()
                .sentenceId(sentence.getId())
                .headerToTranslate(sentence.getClues())
                .progressThroughLastTries("Pierwsza próba")
                .lastSentence(lastSentence)
                .isCorrectAnswer(isCorrectAnswer)
                .numberOfTries(0)
                .hint(sentence.getHint())
                .build();
    }
}
