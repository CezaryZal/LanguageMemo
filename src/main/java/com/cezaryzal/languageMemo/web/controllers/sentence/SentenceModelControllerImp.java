package com.cezaryzal.languageMemo.web.controllers.sentence;

import com.cezaryzal.languageMemo.model.SentenceModel;
import com.cezaryzal.languageMemo.repository.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sentence")
public class SentenceModelControllerImp implements SentenceModelController {

    private final RepoService repoService;

    @Autowired
    public SentenceModelControllerImp(@Qualifier("fromNativeRepoServiceImp") RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping("/{id}")
    @Override
    public Optional<SentenceModel> findById(@PathVariable Long id) {
        return repoService.findById(id);
    }

    @GetMapping("/dates/{date}")
    @Override
    public List<SentenceModel> findAllByReplayDateLessThanEqual(@PathVariable String date) {
        return repoService.findAllByReplayDateLessThanEqual(date);
    }

    @GetMapping("/limit/{number}")
    @Override
    public List<SentenceModel> getListSentenceByLowestReplayLevel(@PathVariable int number) {
        return repoService.getListSentenceByLowestReplayLevel(number);
    }

    @GetMapping("/random/{date}")
    @Override
    public Optional<SentenceModel> findRandomFirstByReplayDateLessThanEqual(@PathVariable String date){
        return repoService.findRandomFirstByReplayDateLessThanEqual(date);
    }

    @GetMapping
    @Override
    public List<SentenceModel> findAll() {
        return repoService.findAll();
    }

    @PostMapping
    @Override
    public SentenceModel addNewSentence(@RequestBody SentenceModel sentenceModel) {
        return repoService.addNewSentence(sentenceModel);
    }

    @PutMapping
    @Override
    public SentenceModel updateSentence(@RequestBody SentenceModel sentenceModel) {
        return repoService.updateSentence(sentenceModel);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteSentenceById(@PathVariable Long id) {
        repoService.deleteSentenceById(id);
    }
}
