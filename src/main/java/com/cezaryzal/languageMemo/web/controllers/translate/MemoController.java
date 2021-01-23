package com.cezaryzal.languageMemo.web.controllers.translate;

import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.application.model.AppendSentence;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;

import java.util.Map;
import java.util.Optional;

public interface MemoController {

    TranslateComponentDto getFirstSentence ();

    TranslateComponentDto resultByInputAnswer (TranslateComponentInput translateComponentInput);

    Map<String, String> getMapWithMostDifficultSentence();

    Optional<Integer> getCounter();

}
