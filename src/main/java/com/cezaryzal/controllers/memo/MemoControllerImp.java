package com.cezaryzal.controllers.memo;

import com.cezaryzal.entity.InputAnswer;
import com.cezaryzal.entity.SentenceToAdd;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
}
