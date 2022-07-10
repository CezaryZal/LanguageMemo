package com.cezaryzal.languageMemo.web.controllers.memo;

import com.cezaryzal.languageMemo.model.AppendSentence;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.SentenceService;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MemoControllerImp implements MemoController{
    private final SentenceService sentenceService;

    @Autowired
    public MemoControllerImp(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @PostMapping("/add")
    public String addNewSentence(@RequestBody AppendSentence appendSentence){
        return sentenceService.addNewSentenceThroughAppendedSentence(appendSentence);
    }
    @GetMapping("/first")
    @Override
    public ComponentDtoOutput getFirstSentence() {
        return sentenceService.getFirstComponentDtoOutput();
    }
    @PostMapping("/result")
    @Override
    public ComponentDtoOutput resultByInputAnswer(@RequestBody ComponentDtoInput componentDtoInput) {
        return sentenceService.getResultByInputAnswer(componentDtoInput);
    }
    @GetMapping("/difficult")
    @Override
    public Map<String, String> getMapWithMostDifficultSentence() {
        return sentenceService.getMapWithMostDifficultSentence();
    }
    @GetMapping("/counter-daily-sentence")
    @Override
    public Optional<Integer> getCounter() {
        return sentenceService.getCounterReplayDateLessThanEqual();
    }
    @GetMapping("/search/clues/{word}")
    @Override
    public List<Sentence> searchSentenceListOfSimilarSpellingsByClues(@PathVariable String word) {
        return sentenceService.searchSentenceListOfSimilarSpellingsByClues(word);
    }
    @GetMapping("/search/correct-answer/{word}")
    @Override
    public List<Sentence> searchSentenceListOfSimilarSpellingsByCorrectAnswer(@PathVariable String word) {
        return sentenceService.searchSentenceListOfSimilarSpellingsByAnswer(word);
    }
}
