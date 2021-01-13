package com.cezaryzal.languageMemo.controllers.memo;

import com.cezaryzal.languageMemo.application.entity.InputAnswer;
import com.cezaryzal.languageMemo.application.entity.SentenceToAdd;
import com.cezaryzal.languageMemo.application.entity.SentenceDTO;
import com.cezaryzal.languageMemo.manager.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MemoControllerImp implements MemoController {

    private ApiService apiService;

    @Autowired
    public MemoControllerImp(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/first")
    @Override
    public SentenceDTO getFirstSentence() {
        return apiService.getFirstSentenceDto();
    }

    @PostMapping ("/result")
    @Override
    public SentenceDTO resultByInputAnswer(@RequestBody InputAnswer inputAnswer) {
        return apiService.getResultByInputAnswer(inputAnswer);
    }

    @PostMapping ("/add")
    @Override
    public String addNewSentence(@RequestBody SentenceToAdd sentenceToAdd) {
        return apiService.addNewSentenceThroughInputSentence(sentenceToAdd);
    }

    @GetMapping("/difficult")
    @Override
    public Map<String, String> getMapWithMostDifficultSentence() {
        return apiService.getMapWithMostDifficultSentence();
    }

    @GetMapping("/counter-daily-sentences")
    @Override
    public Optional<Integer> getCounter() {
        return apiService.getCounterReplayDateLessThanEqual();
    }
}
