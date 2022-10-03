package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;

public interface Enricher {

    void enrichProgressPhrase(final CurrentPlayedMemoItem currentlyPlayedCase);
}
