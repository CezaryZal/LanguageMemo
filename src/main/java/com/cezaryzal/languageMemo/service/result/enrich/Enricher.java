package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;

public interface Enricher {

    void enrichProgressPhrase(final CurrentPlayedSentenceComponent currentlyPlayedCase);
}
