package com.cezaryzal.languageMemo.web.controllers.model;

import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sentence")
public class EntityControllerImpl implements EntityController {
    private final RepositoryMemoItemService repositoryMemoItemService;

    @Autowired
    public EntityControllerImpl(RepositoryMemoItemService repositoryMemoItemService) {
        this.repositoryMemoItemService = repositoryMemoItemService;
    }

    @GetMapping("/{id}")
    @Override
    public Optional<MemoItem> findById(@PathVariable Long id) {
        return repositoryMemoItemService.findById(id);
    }
    @Override
    public List<MemoItem> findAll() {
        return repositoryMemoItemService.findAll();
    }
    @PostMapping
    @Override
    public MemoItem addNewMemoItem(@RequestBody MemoItem memoItem) {
        return repositoryMemoItemService.addNewMemoItem(memoItem);
    }
    @PutMapping
    @Override
    public MemoItem updateMemoItem(@RequestBody MemoItem memoItem) {
        return repositoryMemoItemService.updateMemoItem(memoItem);
    }
    @DeleteMapping("/{id}")
    @Override
    public void deleteMemoItemById(@PathVariable Long id) {
        repositoryMemoItemService.deleteMemoItemById(id);
    }

    //YYYY-MM-DD; {date}<replay_date
    @GetMapping("/dates/{date}")
    @Override
    public List<MemoItem> getByReplayDateLessThanEqual(@PathVariable String date) {
        return repositoryMemoItemService.getByReplayDateLessThanEqual(LocalDate.parse(date));
    }
    @GetMapping("/random/{date}")
    @Override
    public Optional<MemoItem> getRandomMemoItemByReplayDateLessThanEqual(@PathVariable String date) {
        return repositoryMemoItemService.getRandomByReplayDateLessThanEqual(date);
    }
    @GetMapping("/limit/{number}")
    @Override
    public List<MemoItem> getListMemoItemByLowerReplayLevel(@PathVariable int number) {
        return repositoryMemoItemService.getListMemoItemByLowerReplayLevel(number);
    }
    @GetMapping("/answer/{correctAnswer}")
    @Override
    public List<MemoItem> getMemoItemByCorrectAnswer(String correctAnswer) {
        return repositoryMemoItemService.getMemoItemByCorrectAnswer(correctAnswer);
    }

    @GetMapping("/part-word/clues/{phrase}")
    @Override
    public List<MemoItem> getMemoItemListByCluesContainingInsideString(@PathVariable String inside){
        return repositoryMemoItemService.getMemoItemListByCluesContainingInsideString(inside);
    }

    @GetMapping("/part-word/correct_answer/{phrase}")
    @Override
    public List<MemoItem> getMemoItemListByAnswerContainingInsideString(@PathVariable String inside) {
        return repositoryMemoItemService.getMemoItemListByAnswerContainingInsideString(inside);
    }


}
