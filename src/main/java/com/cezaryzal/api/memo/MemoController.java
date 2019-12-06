package com.cezaryzal.api.memo;

import com.cezaryzal.entity.SentenceDTO;

public interface MemoController {

    SentenceDTO getFirstSentence ();

    SentenceDTO resultByInputSentenceDTO (SentenceDTO sentenceDTO);

}
