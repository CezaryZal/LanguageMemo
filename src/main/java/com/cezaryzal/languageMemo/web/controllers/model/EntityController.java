package com.cezaryzal.languageMemo.web.controllers.model;

import com.cezaryzal.languageMemo.repository.entity.MemoItem;

import java.util.List;
import java.util.Optional;

public interface EntityController {

    Optional<MemoItem> findById(Long id);
    List<MemoItem> findAll();
    MemoItem addNewMemoItem(MemoItem memoItem);
    MemoItem updateMemoItem(MemoItem memoItem);
    void deleteMemoItemById(Long id);

    List<MemoItem> getByReplayDateLessThanEqual(String date);
    Optional<MemoItem> getRandomMemoItemByReplayDateLessThanEqual(String date);
    List<MemoItem> getListMemoItemByLowerReplayLevel(int number);
    List<MemoItem> getMemoItemByCorrectAnswer(String correctAnswer);
    public List<MemoItem> getMemoItemListByCluesContainingInsideString(String inside);
    public List<MemoItem> getMemoItemListByAnswerContainingInsideString(String inside);
}
