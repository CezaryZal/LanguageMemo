package com.cezaryzal.languageMemo.service.difficult;

import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShortMemoItemDifficultImpl extends MapDifficultMemoItemCreator implements Difficult{

    private final RepositoryMemoItemService repositoryMemoItemService;

    @Autowired
    public ShortMemoItemDifficultImpl(RepositoryMemoItemService repositoryMemoItemService) {
        this.repositoryMemoItemService = repositoryMemoItemService;
    }

    @Override
    public Map<String, String> getMapDifficultMemoItem(){
        List<MemoItem> mostDifficultMemoItem =
                repositoryMemoItemService.getListMemoItemByLowerReplayLevel(3);
        return modifyListToGetMapDifficultMemoItem(mostDifficultMemoItem);
    }
}
