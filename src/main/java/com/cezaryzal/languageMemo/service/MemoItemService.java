package com.cezaryzal.languageMemo.service;

import com.cezaryzal.languageMemo.model.*;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import com.cezaryzal.languageMemo.service.create.MemoItemCreator;
import com.cezaryzal.languageMemo.service.create.ValidatorNewCreateMemoItem;
import com.cezaryzal.languageMemo.service.difficult.Difficult;
import com.cezaryzal.languageMemo.service.first.StartMemoItemDtoOutput;
import com.cezaryzal.languageMemo.service.result.service.ResultService;
import com.cezaryzal.languageMemo.service.search.SimilarSpellingsSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class MemoItemService {
    private final RepositoryMemoItemService repositoryMemoItemService;
    private final MemoItemCreator memoItemCreator;
    private final StartMemoItemDtoOutput startMemoItemDtoOutput;
    private final ResultService resultService;
    private final Difficult difficultMemoItem;
    private final SimilarSpellingsSearcher similarSpellingsSearcher;
    private final ValidatorNewCreateMemoItem validatorNewMemoItem;

    @Autowired
    public MemoItemService(RepositoryMemoItemService repositoryMemoItemService,
                           MemoItemCreator memoItemCreator,
                           StartMemoItemDtoOutput startMemoItemDtoOutput,
                           ResultService resultService,
                           Difficult difficultMemoItem,
                           SimilarSpellingsSearcher similarSpellingsSearcher,
                           ValidatorNewCreateMemoItem validatorNewMemoItem) {
        this.repositoryMemoItemService = repositoryMemoItemService;
        this.memoItemCreator = memoItemCreator;
        this.startMemoItemDtoOutput = startMemoItemDtoOutput;
        this.resultService = resultService;
        this.difficultMemoItem = difficultMemoItem;
        this.similarSpellingsSearcher = similarSpellingsSearcher;
        this.validatorNewMemoItem = validatorNewMemoItem;
    }

    public String addNewMemoItem(ModelToCreateMemoItem modelToCreateMemoItem) {
        if (validatorNewMemoItem.isValidObjectToCreateNewMemoItem(modelToCreateMemoItem)) {
            repositoryMemoItemService.addNewMemoItem(
                    memoItemCreator.createMemoItem(modelToCreateMemoItem));
            return "New MemoItem was appended to repository";
        } else
            return "This MemoItem has already been added to repository";
    }

    public MemoItemDtoOutput getStartMemoItemDtoOutput() {
         return startMemoItemDtoOutput.getMemoItemDtoOutputWithStartParams();
    }

    public MemoItemDtoOutput getResultByInputAnswer(MemoItemDtoInput memoItemDtoInput) {
        if (memoItemDtoInput.getMemoItemId() == null)
            return getStartMemoItemDtoOutput();
        return resultService.resultByInputAnswer(memoItemDtoInput);
    }

    public Map<String, String> getMapWithMostDifficultMemoItem() {
        return difficultMemoItem.getMapDifficultMemoItem();
    }

    public Optional<Integer> getCounterReplayDateLessThanEqual() {
        return repositoryMemoItemService.getCounterReplayDateLessThanEqual(LocalDate.now());
    }

    public Set<MemoItem> searchMemoItemListOfSimilarSpellingsByClues(String memoItemInput) {
        return similarSpellingsSearcher.findMemoItemListOfSimilarSpellingsByClues(
                memoItemInput,
                MemoItemNavigator.CLUES);
    }

    public Set<MemoItem> searchMemoItemListOfSimilarSpellingsByAnswer(String memoItemInput) {
        return similarSpellingsSearcher.findMemoItemListOfSimilarSpellingsByClues(
                memoItemInput,
                MemoItemNavigator.CORRECT_ANSWER);
    }
}
