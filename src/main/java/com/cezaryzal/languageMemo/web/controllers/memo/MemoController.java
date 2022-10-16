package com.cezaryzal.languageMemo.web.controllers.memo;

import com.cezaryzal.languageMemo.model.ModelToCreateMemoItem;
import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface MemoController {

    MemoItemDtoOutput getFirstMemoItem();
    MemoItemDtoOutput resultByInputAnswer (MemoItemDtoInput memoItemDtoInput);
    Map<String, String> getMapWithMostDifficultMemoItem();
    Optional<Integer> getCounter();
    String addNewMemoItem(ModelToCreateMemoItem modelToCreateMemoItem);
    Set<MemoItem> searchMemoItemListOfSimilarSpellingsByClues(String word);
    Set<MemoItem> searchMemoItemListOfSimilarSpellingsByCorrectAnswer(String word);

}
