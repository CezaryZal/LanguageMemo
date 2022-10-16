package com.cezaryzal.languageMemo.service.search;

import com.cezaryzal.languageMemo.model.MemoItemNavigator;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FinderBySimilarSpellings {
    private final RepositoryMemoItemService repositoryMemoItemService;

    @Autowired
    public FinderBySimilarSpellings(RepositoryMemoItemService repositoryMemoItemService) {
        this.repositoryMemoItemService = repositoryMemoItemService;
    }

    //potem z Set<> spradzić
    public Set<MemoItem> findMemoItemListOfSimilarSpellingsByClues(String memoItemInput, MemoItemNavigator navigator){
        Set<MemoItem> matchingMemoItemListByInput = new HashSet<>();

        Arrays.stream(memoItemInput.split("[ _\\-]"))
                .map(this::parseWordBasedOnLength)
                .forEach(word -> {
                    matchingMemoItemListByInput
                            .addAll(getMemoItemListByCluesContainingInsideString(word, navigator));
                });
        return matchingMemoItemListByInput;
    }

    public String parseWordBasedOnLength(String string){
        int wordLength = string.length();

        if (wordLength<3)
            return string;
        if (wordLength<5)
            return string.substring(0, wordLength-1);
        if (wordLength<6)
            return string.substring(1, wordLength-1);
        if (wordLength<10)
            return string.substring(1, wordLength-2);
        else
            return string.substring(1, wordLength-3);
    }

    private List<MemoItem> getMemoItemListByCluesContainingInsideString(String word, MemoItemNavigator navigator){
        switch (navigator){
            case CLUES:
                return repositoryMemoItemService.getMemoItemListByCluesContainingInsideString(word);
            case CORRECT_ANSWER:
                return repositoryMemoItemService.getMemoItemListByAnswerContainingInsideString(word);
        }
        return null;
    }
}
