package com.cezaryzal.languageMemo.service.random;

import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RandomMemoItemImpl implements RandomMemoItem {

    private final RepositoryMemoItemService memoItemService;

    @Autowired
    public RandomMemoItemImpl(RepositoryMemoItemService memoItemService) {
        this.memoItemService = memoItemService;
    }

    @Override
    public MemoItem getRandomMemoItemByTodayDate() {
        Optional<MemoItem> randomMemoItemByTodayDate = memoItemService
                .getRandomByReplayDateLessThanEqual(LocalDate.now());
        return randomMemoItemByTodayDate.orElse(new MemoItem(
                0L,
                "Wszystkie sformułowania na dziś zostały odgadnięte",
                "Dobra robora!",
                "Zrób sobię przerwę")
        );
    }
}
