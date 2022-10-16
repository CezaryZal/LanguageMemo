package com.cezaryzal.languageMemo.service;

import com.cezaryzal.languageMemo.model.*;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import com.cezaryzal.languageMemo.service.create.MemoItemCreator;
import com.cezaryzal.languageMemo.service.difficult.Difficult;
import com.cezaryzal.languageMemo.service.first.StartMemoItemDtoOutput;
import com.cezaryzal.languageMemo.service.result.service.ResultService;
import com.cezaryzal.languageMemo.service.search.FinderBySimilarSpellings;
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
    private final FinderBySimilarSpellings finderBySimilarSpellings;

    @Autowired
    public MemoItemService(RepositoryMemoItemService repositoryMemoItemService,
                           MemoItemCreator memoItemCreator,
                           StartMemoItemDtoOutput startMemoItemDtoOutput,
                           ResultService resultService,
                           Difficult difficultMemoItem,
                           FinderBySimilarSpellings finderBySimilarSpellings) {
        this.repositoryMemoItemService = repositoryMemoItemService;
        this.memoItemCreator = memoItemCreator;
        this.startMemoItemDtoOutput = startMemoItemDtoOutput;
        this.resultService = resultService;
        this.difficultMemoItem = difficultMemoItem;
        this.finderBySimilarSpellings = finderBySimilarSpellings;
    }

    public String addNewMemoItem(ModelToCreateMemoItem modelToCreateMemoItem) {
        MemoItem similarMemoItem = getSimilarMemoItemToNewOne(
                modelToCreateMemoItem.getCorrectAnswer(),
                modelToCreateMemoItem.getClues());
        if (similarMemoItem == null || !isNotBlankMemoItem(modelToCreateMemoItem)) {
            repositoryMemoItemService.addNewMemoItem(
                    memoItemCreator.createMemoItem(modelToCreateMemoItem));
            return "New MemoItem was appended to repository.";
        } else
            return "This MemoItem has already been added to repository. MemoItem: " + similarMemoItem.toString();
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
        return finderBySimilarSpellings.findMemoItemListOfSimilarSpellingsByClues(
                memoItemInput,
                MemoItemNavigator.CLUES);
    }

    public Set<MemoItem> searchMemoItemListOfSimilarSpellingsByAnswer(String memoItemInput) {
        return finderBySimilarSpellings.findMemoItemListOfSimilarSpellingsByClues(
                memoItemInput,
                MemoItemNavigator.CORRECT_ANSWER);
    }

    private MemoItem getSimilarMemoItemToNewOne(String answerPattern, String cluesPattern) {
        return repositoryMemoItemService.getMemoItemListByAnswerAndCluesContainingInsideString(
                finderBySimilarSpellings.parseWordBasedOnLength(answerPattern),
                finderBySimilarSpellings.parseWordBasedOnLength(cluesPattern));
    }

//    TODO Fine pattern to validation method
    private boolean isNotBlankMemoItem(ModelToCreateMemoItem modelToCreateMemoItem){
        if (modelToCreateMemoItem.getCorrectAnswer().isBlank() ||
                modelToCreateMemoItem.getCorrectAnswer().isEmpty() ||
                modelToCreateMemoItem.getCorrectAnswer() == null){
            return false;
        } else if (modelToCreateMemoItem.getClues().isBlank() ||
                modelToCreateMemoItem.getClues().isEmpty() ||
                modelToCreateMemoItem.getClues() == null){
            return false;
        } else if (modelToCreateMemoItem.getHint().isBlank() ||
                modelToCreateMemoItem.getHint().isEmpty() ||
                modelToCreateMemoItem.getHint() == null){
            return false;
        } else return !modelToCreateMemoItem.getExampleOfUse().isBlank() &&
                !modelToCreateMemoItem.getExampleOfUse().isEmpty() &&
                modelToCreateMemoItem.getExampleOfUse() != null;
    }
}
