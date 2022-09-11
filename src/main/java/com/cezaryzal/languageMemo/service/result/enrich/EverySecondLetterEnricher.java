package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import org.springframework.stereotype.Service;

@Service
public class EverySecondLetterEnricher implements Enricher {
    StringBuilder creatingProgressPhrase;

    @Override
    public void enrichProgressPhrase(CurrentPlayedSentenceComponent currentlyPlayedCase) {
        creatingProgressPhrase = new StringBuilder();
        String correctAnswer = currentlyPlayedCase
                .getUsedSentence()
                .getCorrectAnswer();

        for (int i = 0; i < correctAnswer.length(); i++) {
            if (i % 2 == 1){
                creatingProgressPhrase.append(correctAnswer
                                                    .charAt(i));
            } else {
                creatingProgressPhrase.append(currentlyPlayedCase
                                                    .getProgressPhrase()
                                                    .charAt(i));
            }
        }
        currentlyPlayedCase.setProgressPhrase(creatingProgressPhrase.toString());
    }

}
