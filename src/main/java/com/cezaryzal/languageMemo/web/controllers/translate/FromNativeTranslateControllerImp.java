package com.cezaryzal.languageMemo.web.controllers.translate;

import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.application.model.AppendSentence;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FromNativeTranslateControllerImp implements MemoController {

    private final TranslateService translateService;

    @Autowired
    public FromNativeTranslateControllerImp(TranslateService translateService) {
        this.translateService = translateService;
    }

    @GetMapping("/first")
    @Override
    public TranslateComponentDto getFirstSentence() {
        return translateService.getFirstSentenceDto();
    }

    @PostMapping ("/result")
    @Override
    public TranslateComponentDto resultByInputAnswer(@RequestBody TranslateComponentInput translateComponentInput) {
        return translateService.getResultByInputAnswer(translateComponentInput);
    }

    @PostMapping ("/add")
    @Override
    public String addNewSentence(@RequestBody AppendSentence appendSentence) {
        return translateService.addNewSentenceThroughInputSentence(appendSentence);
    }

    @GetMapping("/difficult")
    @Override
    public Map<String, String> getMapWithMostDifficultSentence() {
        return translateService.getMapWithMostDifficultSentence();
    }

    @GetMapping("/counter-daily-sentences")
    @Override
    public Optional<Integer> getCounter() {
        return translateService.getCounterReplayDateLessThanEqual();
    }
}
