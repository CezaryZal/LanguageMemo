package com.cezaryzal.languageMemo.web.controllers.memo;

import com.cezaryzal.languageMemo.config.MainConfig;
import com.cezaryzal.languageMemo.model.AppendSentence;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.SentenceService;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.model.ComponentDtoInput;
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
        ComponentDtoOutput resultByInputAnswer = sentenceService.getResultByInputAnswer(componentDtoInput);
        log.debug(
                "\n<------Memo controller: " + componentDtoInput
        );
        return resultByInputAnswer;
    }

    @GetMapping("/difficult")
    @Override
    public Map<String, String> getMapWithMostDifficultSentence() {
        return sentenceService.getMapWithMostDifficultSentence();
    }

    @GetMapping("/counter_daily_sentence")
    @Override
    public Optional<Integer> getCounter() {
        return sentenceService.getCounterReplayDateLessThanEqual();
    }

    @GetMapping("/search/clues/{sentenceInput}")
    @Override
    public Set<Sentence> searchSentenceListOfSimilarSpellingsByClues(@PathVariable String sentenceInput) {
        return sentenceService.searchSentenceListOfSimilarSpellingsByClues(sentenceInput);
    }

    @GetMapping("/search/correct_answer/{sentenceInput}")
    @Override
    public Set<Sentence> searchSentenceListOfSimilarSpellingsByCorrectAnswer(@PathVariable String sentenceInput) {
        return sentenceService.searchSentenceListOfSimilarSpellingsByAnswer(sentenceInput);
    }
}
