package com.cezaryzal.languageMemo.service.first;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.SentenceComponentDataFiller;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.service.random.RandomSentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartMemoItemDtoOutputImpl extends SentenceComponentDataFiller implements StartMemoItemDtoOutput {

    private final RandomSentence randomSentence;
    private final CurrentPlayedMemoItem currentMemoItem;
    private final ServiceResultConfig serviceResultConfig;

    @Autowired
    public StartMemoItemDtoOutputImpl(RandomSentence randomSentence,
                                      CurrentPlayedMemoItem currentMemoItem,
                                      ServiceResultConfig serviceResultConfig) {
        this.randomSentence = randomSentence;
        this.currentMemoItem = currentMemoItem;
        this.serviceResultConfig = serviceResultConfig;
    }


    @Override
    public MemoItemDtoOutput getMemoItemDtoOutputWithStartParams() {
        Sentence randomSentenceByTodayDate = randomSentence.getRandomSentenceByTodayDate();
        currentMemoItem.initialProgressPhrase(randomSentenceByTodayDate);
        if (randomSentenceByTodayDate.getId() == 0)
            return createMemoItemDtoLastTryPerDay(randomSentenceByTodayDate);

        return createMemoItemDtoFirstTry(
                true,
                serviceResultConfig.getInitialStringOfLastSentence(),
                randomSentenceByTodayDate);
    }
}
