package com.cezaryzal.languageMemo.web.controllers.memo;

import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.application.model.AppendSentence;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TranslateMemoControllerImp implements MemoController {

    private final ApiService apiService;

    @Autowired
    public TranslateMemoControllerImp(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/first")
    @Override
    public TranslateComponentDto getFirstSentence() {
        return apiService.getFirstSentenceDto();
    }

    @PostMapping ("/result")
    @Override
    public TranslateComponentDto resultByInputAnswer(@RequestBody TranslateComponentInput translateComponentInput) {
        return apiService.getResultByInputAnswer(translateComponentInput);
    }

    @PostMapping ("/add")
    @Override
    public String addNewSentence(@RequestBody AppendSentence appendSentence) {
        return apiService.addNewSentenceThroughInputSentence(appendSentence);
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
