package com.cezaryzal.controllers.memo;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.AddSentence;
import com.cezaryzal.entity.SentenceDTO;

import java.util.Map;

public interface MemoController {

    SentenceDTO getFirstSentence ();

    SentenceDTO resultByInputAnswer (Answer answer);

    String addNewSentence (AddSentence addSentence);

    Map<String, String> getMapWithMostDifficultSentence();

}
