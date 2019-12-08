package com.cezaryzal.api.memo;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.SentenceDTO;

public interface MemoController {

    SentenceDTO getFirstSentence ();

    SentenceDTO resultByInputAnswer (Answer answer);

}
