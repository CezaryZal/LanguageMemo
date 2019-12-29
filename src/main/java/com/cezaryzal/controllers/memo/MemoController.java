package com.cezaryzal.controllers.memo;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.AddSentence;
import com.cezaryzal.entity.SentenceDTO;

public interface MemoController {

    SentenceDTO getFirstSentence ();

    SentenceDTO resultByInputAnswer (Answer answer);

    String addNewSentence (AddSentence addSentence);

}
