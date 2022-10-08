package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.service.random.RandomSentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NextMemoItemDtoOutput extends SentenceComponentDataFiller {

    private final RandomSentence randomSentence;
    private final CurrentPlayedMemoItem currentPlayedMemoItem;

    @Autowired
    public NextMemoItemDtoOutput(RandomSentence randomSentence,
                                 CurrentPlayedMemoItem currentPlayedMemoItem) {
        this.randomSentence = randomSentence;
        this.currentPlayedMemoItem = currentPlayedMemoItem;
    }

    public MemoItemDtoOutput getNextMemoItemDtoOutput(boolean isCorrectAnswer) {
        Sentence randomSentenceByTodayDate = randomSentence.getRandomSentenceByTodayDate();
        currentPlayedMemoItem.saveSentenceInCacheBeforeNextOne();
        currentPlayedMemoItem.initialProgressPhrase(randomSentenceByTodayDate);

        return createMemoItemDtoFirstTry(
                isCorrectAnswer,
                currentPlayedMemoItem.getLastSentence(),
                randomSentenceByTodayDate);
    }
}
