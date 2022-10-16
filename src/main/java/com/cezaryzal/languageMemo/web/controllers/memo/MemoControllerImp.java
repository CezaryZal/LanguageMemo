package com.cezaryzal.languageMemo.web.controllers.memo;

import com.cezaryzal.languageMemo.model.ModelToCreateMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.service.MemoItemService;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api")
public class MemoControllerImp implements MemoController{
    private final MemoItemService memoItemService;

    @Autowired
    public MemoControllerImp(MemoItemService memoItemService) {
        this.memoItemService = memoItemService;
    }

    @PostMapping("/add")
    public String addNewMemoItem(@RequestBody ModelToCreateMemoItem modelToCreateMemoItem){
        return memoItemService.addNewMemoItem(modelToCreateMemoItem);
    }
    @GetMapping("/first")
    @Override
    public MemoItemDtoOutput getFirstMemoItem() {
        return memoItemService.getStartMemoItemDtoOutput();
    }

    @PostMapping("/result")
    @Override
    public MemoItemDtoOutput resultByInputAnswer(@RequestBody MemoItemDtoInput memoItemDtoInput) {
        MemoItemDtoOutput resultByInputAnswer = memoItemService.getResultByInputAnswer(memoItemDtoInput);
        log.debug(
                "\n<------Memo controller: " + memoItemDtoInput
        );
        return resultByInputAnswer;
    }

    @GetMapping("/difficult")
    @Override
    public Map<String, String> getMapWithMostDifficultMemoItem() {
        return memoItemService.getMapWithMostDifficultMemoItem();
    }

    @GetMapping("/counter_daily_memo_item")
    @Override
    public Optional<Integer> getCounter() {
        return memoItemService.getCounterReplayDateLessThanEqual();
    }

    @GetMapping("/search/clues/{memoItemInput}")
    @Override
    public Set<MemoItem> searchMemoItemListOfSimilarSpellingsByClues(@PathVariable String memoItemInput) {
        return memoItemService.searchMemoItemListOfSimilarSpellingsByClues(memoItemInput);
    }

    @GetMapping("/search/correct_answer/{memoItemInput}")
    @Override
    public Set<MemoItem> searchMemoItemListOfSimilarSpellingsByCorrectAnswer(@PathVariable String memoItemInput) {
        return memoItemService.searchMemoItemListOfSimilarSpellingsByAnswer(memoItemInput);
    }
}
