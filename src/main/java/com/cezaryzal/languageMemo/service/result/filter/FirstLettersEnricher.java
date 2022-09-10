package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import com.cezaryzal.languageMemo.service.result.enrich.Enricher;
import org.springframework.stereotype.Service;

@Service
public class FirstLettersEnricher implements Enricher {

    @Override
    public void enrichProgressPhrase(CurrentPlayedSentenceComponent currentlyPlayedCase) {
        String correctAnswer = currentlyPlayedCase
                .getUsedSentence()
                .getCorrectAnswer();

        currentlyPlayedCase
                .getSplittedCorrectAnswer()
                .forEach(word -> {
                    int indexOfLitterInCorrectAnswer = correctAnswer.indexOf(word);

                    StringBuilder replacedProgressPhrase =
                            new StringBuilder(currentlyPlayedCase.getProgressPhrase());

                    replacedProgressPhrase.setCharAt(
                            indexOfLitterInCorrectAnswer,
                            correctAnswer.charAt(indexOfLitterInCorrectAnswer));

                    currentlyPlayedCase.setProgressPhrase(String.valueOf(replacedProgressPhrase));
                });

    }
}
