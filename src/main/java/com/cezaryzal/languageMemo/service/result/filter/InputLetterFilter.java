package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputLetterFilter implements InputFilter{
    StringBuilder creatingProgressPhrase;

    private final ServiceResultConfig serviceResultConfig;

    @Autowired
    public InputLetterFilter(ServiceResultConfig serviceResultConfig) {
        this.serviceResultConfig = serviceResultConfig;
    }

    @Override
    public void catchCorrectPieceToProgressPhrase(final CurrentPlayedMemoItem currentlyPlayedCase, String inputPhrases) {
        creatingProgressPhrase = new StringBuilder();
        for (int i = 0;
             i < currentlyPlayedCase
                .getUsedSentence()
                .getCorrectAnswer()
                .length();
             i++) {
            compareAndInsertLetterToProgressSentence(currentlyPlayedCase, i, inputPhrases);
        }
        currentlyPlayedCase.setProgressPhrase(creatingProgressPhrase.toString());
    }
    private void compareAndInsertLetterToProgressSentence(CurrentPlayedMemoItem currentlyPlayedCase,
                                                          int indexOfLetterCompared,
                                                          String inputPhrases){
        String correctAnswer = currentlyPlayedCase
                                        .getUsedSentence()
                                        .getCorrectAnswer();
        String progressPhrase = currentlyPlayedCase
                                        .getProgressPhrase();

        if (inputPhrases.length() > indexOfLetterCompared &&
                inputPhrases.charAt(indexOfLetterCompared) == correctAnswer.charAt(indexOfLetterCompared)) {
            creatingProgressPhrase.append(inputPhrases.charAt(indexOfLetterCompared));
        } else if (progressPhrase.charAt(indexOfLetterCompared) == correctAnswer.charAt(indexOfLetterCompared)){
            creatingProgressPhrase.append(progressPhrase.charAt(indexOfLetterCompared));
        } else {
            creatingProgressPhrase.append(serviceResultConfig.getIncorrectLetter());
        }
    }
}
