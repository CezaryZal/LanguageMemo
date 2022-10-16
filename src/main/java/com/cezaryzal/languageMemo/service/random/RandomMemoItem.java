package com.cezaryzal.languageMemo.service.random;

import com.cezaryzal.languageMemo.repository.entity.MemoItem;

public interface RandomMemoItem {

    MemoItem getRandomMemoItemByTodayDate();
}
