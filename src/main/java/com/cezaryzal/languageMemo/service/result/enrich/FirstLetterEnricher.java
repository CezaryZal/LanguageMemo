package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import org.springframework.stereotype.Service;

@Service
public class FirstLetterEnricher implements Enricher {

    @Override
    public void enrichProgressPhrase(final CurrentPlayedSentenceComponent currentlyPlayedCase) {
        String correctAnswer = currentlyPlayedCase
                .getUsedSentence()
                .getCorrectAnswer();

        StringBuilder replacedProgressPhrase =
                new StringBuilder(currentlyPlayedCase.getProgressPhrase());

        replacedProgressPhrase.setCharAt(0, correctAnswer.charAt(0));

        currentlyPlayedCase.setProgressPhrase(String.valueOf(replacedProgressPhrase));
    }
}
