package com.cezaryzal.languageMemo.repository.service;

import com.cezaryzal.languageMemo.repository.entity.MemoItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RepositoryMemoItemService {

    Optional<MemoItem> findById(Long id);
    List<MemoItem> findAll();
    MemoItem addNewMemoItem(MemoItem memoItem);
    MemoItem updateMemoItem(MemoItem memoItem);
    void deleteMemoItemById(Long id);

    List<MemoItem> getByReplayDateLessThanEqual(LocalDate localDate);
    Optional<MemoItem> getRandomByReplayDateLessThanEqual(Object localDate);
    List<MemoItem> getListMemoItemByLowerReplayLevel(int limitReplayLevel);
    List<MemoItem> getMemoItemByCorrectAnswer(String correctAnswer);
    Optional<Integer> getCounterReplayDateLessThanEqual(LocalDate localDate);
    List<MemoItem> getMemoItemListByCluesContainingInsideString(String pattern);
    List<MemoItem> getMemoItemListByAnswerContainingInsideString(String pattern);
    MemoItem getMemoItemListByAnswerAndCluesContainingInsideString(String answerPattern, String cluesPattern);

}
