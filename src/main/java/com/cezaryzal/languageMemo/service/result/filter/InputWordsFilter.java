package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InputWordsFilter implements InputFilter{

    //TODO validacja czy frazy poniżej 3 liter zostają zamieniane na puste blanki
    @Override
    public void catchCorrectPieceToProgressPhrase(final CurrentPlayedMemoItem currentlyPlayedCase,
                                                  String inputPhrases) {
        StringBuilder creatingProgressPhrase = new StringBuilder();

        Arrays.stream(inputPhrases.split("[ _\\-]"))
                .forEach(word -> {
                    if (word.length() > 2) {
                        String progressPhrase = currentlyPlayedCase.getProgressPhrase();
                        if (currentlyPlayedCase.getUsedSentence().getCorrectAnswer().contains(word)) {
                            int index = currentlyPlayedCase
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
                    }});
        String createdProgressPhrase = creatingProgressPhrase.toString();

        if (!createdProgressPhrase.isBlank())
        currentlyPlayedCase.setProgressPhrase(createdProgressPhrase);
    }
}
