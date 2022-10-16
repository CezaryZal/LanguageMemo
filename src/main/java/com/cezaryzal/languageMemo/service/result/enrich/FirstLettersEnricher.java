package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import org.springframework.stereotype.Service;

@Service
public class FirstLettersEnricher implements Enricher {

    @Override
    public void enrichProgressPhrase(final CurrentPlayedMemoItem currentlyPlayedCase) {
        String correctAnswer = currentlyPlayedCase
                .getUsedMemoItem()
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
