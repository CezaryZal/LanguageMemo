package com.cezaryzal.controllers.memo;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.InputSentence;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;

public interface MemoController {

    SentenceDTO getFirstSentence ();

    SentenceDTO resultByInputAnswer (Answer answer);

    Sentence addNewSentence (InputSentence inputSentence);

}
