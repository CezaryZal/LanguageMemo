package com.cezaryzal.languageMemo.web.controllers.translate;

import com.cezaryzal.languageMemo.application.TranslateService;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fromNative")
public class ToNativeTranslateController implements MemoController{

    private final TranslateService translateService;

    @Autowired
    public ToNativeTranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }


    @GetMapping("/first")
    @Override
    public TranslateComponentDto getFirstSentence() {
        return translateService
                .handleToNativeTranslateService()
                .getFirstSentenceDto();
    }

    @PostMapping("/result")
    @Override
    public TranslateComponentDto resultByInputAnswer(@RequestBody TranslateComponentInput translateComponentInput) {
        return translateService
                .handleToNativeTranslateService()
                .getResultByInputAnswer(translateComponentInput);
    }

    @GetMapping("/difficult")
    @Override
    public Map<String, String> getMapWithMostDifficultSentence() {
        return translateService
                .handleToNativeTranslateService()
                .getMapWithMostDifficultSentence();
    }

    @GetMapping("/counter-daily-sentences")
    @Override
    public Optional<Integer> getCounter() {
        return translateService
                .handleToNativeTranslateService()
                .getCounterReplayDateLessThanEqual();
    }
}
