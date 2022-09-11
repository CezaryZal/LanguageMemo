package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;

import java.util.Arrays;

public class InputWordsFilter {
    private final CurrentPlayedSentenceComponent currentPlayedSentence;

    public InputWordsFilter(CurrentPlayedSentenceComponent currentPlayedSentence) {
        this.currentPlayedSentence = currentPlayedSentence;
    }


    //TODO validacja czy frazy poniżej 3 liter zostają zamieniane na puste blanki
    public void catchCorrectWordToProgressPhrase(String[] splittedInputPhrases, String progressPhrase){
        StringBuilder creatingProgressPhrase = new StringBuilder();

        Arrays.stream(splittedInputPhrases)
                .forEach(word -> {
                    if (currentPlayedSentence.getUsedSentence().getCorrectAnswer().contains(word)) {
                        int index = currentPlayedSentence
                                .getUsedSentence()
                                .getCorrectAnswer()
                                .indexOf(word);

                        if (index != 0)
                            creatingProgressPhrase
                                    .append(progressPhrase, 0, index);

                        creatingProgressPhrase
                                .append(word)
                                .append(progressPhrase.substring(index + word.length()));
                    }
                });
        currentPlayedSentence.setProgressPhrase(creatingProgressPhrase.toString());
    }
}
