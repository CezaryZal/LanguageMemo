package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.service.MemoItemComponentDataFiller;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.service.random.RandomMemoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextMemoItemDtoOutput extends MemoItemComponentDataFiller {

    private final RandomMemoItem randomMemoItem;
    private final CurrentPlayedMemoItem currentPlayedMemoItem;

    @Autowired
    public NextMemoItemDtoOutput(RandomMemoItem randomMemoItem,
                                 CurrentPlayedMemoItem currentPlayedMemoItem) {
        this.randomMemoItem = randomMemoItem;
        this.currentPlayedMemoItem = currentPlayedMemoItem;
    }

    public MemoItemDtoOutput getNextMemoItemDtoOutput(boolean isCorrectAnswer) {
        MemoItem randomMemoItemByTodayDate = randomMemoItem.getRandomMemoItemByTodayDate();
        currentPlayedMemoItem.saveMemoItemInCacheBeforeNextOne();
        currentPlayedMemoItem.initialProgressPhrase(randomMemoItemByTodayDate);

        return createMemoItemDtoFirstTry(
                isCorrectAnswer,
                currentPlayedMemoItem.getLastMemoItem(),
                randomMemoItemByTodayDate);
    }
}
