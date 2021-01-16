package com.cezaryzal.languageMemo.web.controllers.sentence;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.reposervice.SentenceRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sentence")
public class SentenceModelControllerImp implements SentenceModelController {

    private final SentenceRepoService sentenceRepoService;

    @Autowired
    public SentenceModelControllerImp(SentenceRepoService sentenceRepoService) {
        this.sentenceRepoService = sentenceRepoService;
    }

    @GetMapping("/{id}")
    @Override
    public Optional<SentenceModel> findById(@PathVariable Long id) {
        return sentenceRepoService.findById(id);
    }

    @GetMapping("/dates/{date}")
    @Override
    public List<SentenceModel> findAllByReplayDateLessThanEqual(@PathVariable String date) {
        return sentenceRepoService.findAllByReplayDateLessThanEqual(date);
    }

    @GetMapping("/limit/{number}")
    @Override
    public List<SentenceModel> getListSentenceByLowestReplayLevel(@PathVariable int number) {
        return sentenceRepoService.getListSentenceByLowestReplayLevel(number);
    }

    @GetMapping("/random/{date}")
    @Override
    public Optional<SentenceModel> findRandomFirstByReplayDateLessThanEqual(@PathVariable String date){
        return sentenceRepoService.findRandomFirstByReplayDateLessThanEqual(date);
    }

    @GetMapping
    @Override
    public List<SentenceModel> findAll() {
        return sentenceRepoService.findAll();
    }

    @PostMapping
    @Override
    public SentenceModel addNewSentence(@RequestBody SentenceModel sentenceModel) {
        return sentenceRepoService.addNewSentence(sentenceModel);
    }

    @PutMapping
    @Override
    public SentenceModel updateSentence(@RequestBody SentenceModel sentenceModel) {
        return sentenceRepoService.updateSentence(sentenceModel);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteSentenceById(@PathVariable Long id) {
        sentenceRepoService.deleteSentenceById(id);
    }
}
