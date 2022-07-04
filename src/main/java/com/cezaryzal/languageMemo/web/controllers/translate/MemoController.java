package com.cezaryzal.languageMemo.web.controllers.translate;

import com.cezaryzal.languageMemo.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.translate.components.TranslateComponentDto;

import java.util.Map;
import java.util.Optional;

public interface MemoController {

    TranslateComponentDto getFirstSentence ();

    TranslateComponentDto resultByInputAnswer (TranslateComponentInput translateComponentInput);

    Map<String, String> getMapWithMostDifficultSentence();

    Optional<Integer> getCounter();

}
