package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;

public interface InputFilter {

    void catchCorrectPieceToProgressPhrase(final CurrentPlayedSentenceComponent currentlyPlayedCase,
                                           String inputPhrases);
}
