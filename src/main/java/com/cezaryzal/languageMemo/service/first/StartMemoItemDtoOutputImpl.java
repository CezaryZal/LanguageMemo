package com.cezaryzal.languageMemo.service.first;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.service.MemoItemComponentDataFiller;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.service.random.RandomMemoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartMemoItemDtoOutputImpl extends MemoItemComponentDataFiller implements StartMemoItemDtoOutput {

    private final RandomMemoItem randomMemoItem;
    private final CurrentPlayedMemoItem currentMemoItem;
    private final ServiceResultConfig serviceResultConfig;

    @Autowired
    public StartMemoItemDtoOutputImpl(RandomMemoItem randomMemoItem,
                                      CurrentPlayedMemoItem currentMemoItem,
                                      ServiceResultConfig serviceResultConfig) {
        this.randomMemoItem = randomMemoItem;
        this.currentMemoItem = currentMemoItem;
        this.serviceResultConfig = serviceResultConfig;
    }


    @Override
    public MemoItemDtoOutput getMemoItemDtoOutputWithStartParams() {
        MemoItem randomMemoItemByTodayDate = randomMemoItem.getRandomMemoItemByTodayDate();
        currentMemoItem.initialProgressPhrase(randomMemoItemByTodayDate);
        if (randomMemoItemByTodayDate.getId() == 0)
            return createMemoItemDtoLastTryPerDay(randomMemoItemByTodayDate);

        return createMemoItemDtoFirstTry(
                true,
                serviceResultConfig.getInitialStringOfLastMemoItem(),
                randomMemoItemByTodayDate);
    }
}
