package com.cezaryzal.languageMemo.web.controllers.translate;

import com.cezaryzal.languageMemo.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fromNative")
public class FromNativeTranslateControllerImp implements MemoController {

    private final TranslateService translateService;

    @Autowired
    public FromNativeTranslateControllerImp(TranslateService translateService) {
        this.translateService = translateService;
    }

    @GetMapping("/first")
    @Override
    public TranslateComponentDto getFirstSentence() {
        return translateService
                .handleFromNativeTranslateService()
                .getFirstSentenceDto();
    }

    @PostMapping ("/result")
    @Override
    public TranslateComponentDto resultByInputAnswer(@RequestBody TranslateComponentInput translateComponentInput) {
        return translateService
                .handleFromNativeTranslateService()
                .getResultByInputAnswer(translateComponentInput);
    }

    @GetMapping("/difficult")
    @Override
    public Map<String, String> getMapWithMostDifficultSentence() {
        return translateService
                .handleFromNativeTranslateService()
                .getMapWithMostDifficultSentence();
    }

    @GetMapping("/counter-daily-sentences")
    @Override
    public Optional<Integer> getCounter() {
        return translateService
                .handleFromNativeTranslateService()
                .getCounterReplayDateLessThanEqual();
    }
}
