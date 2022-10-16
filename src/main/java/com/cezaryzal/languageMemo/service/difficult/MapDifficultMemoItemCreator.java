package com.cezaryzal.languageMemo.service.difficult;

import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
abstract class MapDifficultMemoItemCreator {

    protected Map<String, String> modifyListToGetMapDifficultMemoItem(List<MemoItem> mostDifficultMemoItem){
        Map<String, String> mapDifficultMemoItem = new HashMap<>();
        mostDifficultMemoItem.forEach(memoItem -> mapDifficultMemoItem
                .put(memoItem.getClues(), memoItem.getCorrectAnswer()));

        return mapDifficultMemoItem;
    }
}
