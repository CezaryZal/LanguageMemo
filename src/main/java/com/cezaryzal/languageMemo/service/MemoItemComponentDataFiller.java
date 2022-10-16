package com.cezaryzal.languageMemo.service;

import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;

public abstract class MemoItemComponentDataFiller {

     protected MemoItemDtoOutput createMemoItemDtoFirstTry(boolean isCorrectAnswer,
                                                           String lastMemoItem,
                                                           MemoItem memoItem) {
        return MemoItemDtoOutput.builder()
                .memoItemId(memoItem.getId())
                .headerToTranslate(memoItem.getClues())
                .progressThroughLastTries("Pierwsza próba")
                .lastMemoItem(lastMemoItem)
                .isCorrectAnswer(isCorrectAnswer)
                .guess(0)
                .hint(memoItem.getHint())
                .build();
    }

    protected MemoItemDtoOutput createMemoItemDtoLastTryPerDay(MemoItem endingDummyMemoItem){
         return MemoItemDtoOutput.builder()
                 .memoItemId(endingDummyMemoItem.getId())
                 .headerToTranslate(endingDummyMemoItem.getClues())
                 .progressThroughLastTries(endingDummyMemoItem.getCorrectAnswer())
                 .lastMemoItem("------")
                 .isCorrectAnswer(true)
                 .guess(0)
                 .hint(endingDummyMemoItem.getHint())
                 .build();
    }
}
