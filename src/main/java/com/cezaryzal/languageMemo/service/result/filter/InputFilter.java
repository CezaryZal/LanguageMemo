package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;

public interface InputFilter {

    void catchCorrectPieceToProgressPhrase(final CurrentPlayedMemoItem currentlyPlayedCase,
                                           String inputPhrases);
}
