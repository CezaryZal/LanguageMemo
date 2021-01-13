package com.cezaryzal.languageMemo.application.controllers.memo;

import com.cezaryzal.languageMemo.application.entity.InputAnswer;
import com.cezaryzal.languageMemo.application.entity.SentenceDTO;
import com.cezaryzal.languageMemo.application.entity.SentenceToAdd;

import java.util.Map;
import java.util.Optional;

public interface MemoController {

    SentenceDTO getFirstSentence ();

    SentenceDTO resultByInputAnswer (InputAnswer inputAnswer);

    String addNewSentence (SentenceToAdd sentenceToAdd);

    Map<String, String> getMapWithMostDifficultSentence();

    Optional<Integer> getCounter();

}
