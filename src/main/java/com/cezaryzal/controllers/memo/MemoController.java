package com.cezaryzal.controllers.memo;

import com.cezaryzal.entity.InputAnswer;
import com.cezaryzal.entity.SentenceToAdd;
import com.cezaryzal.entity.SentenceDTO;

import java.util.Map;
import java.util.Optional;

public interface MemoController {

    SentenceDTO getFirstSentence ();

    SentenceDTO resultByInputAnswer (InputAnswer inputAnswer);

    String addNewSentence (SentenceToAdd sentenceToAdd);

    Map<String, String> getMapWithMostDifficultSentence();

    Optional<Integer> getCounter();

}
