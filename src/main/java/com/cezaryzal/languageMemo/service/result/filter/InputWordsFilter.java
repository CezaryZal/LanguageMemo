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


        Arrays.stream(inputPhrases.split("[ _\\-]"))
                .forEach(word -> {
                    if (word.length() > 2) {
                        StringBuilder creatingProgressPhrase = new StringBuilder();

                        String progressPhrase = currentlyPlayedCase.getProgressPhrase();
                        if (currentlyPlayedCase.getUsedMemoItem().getCorrectAnswer().contains(word)) {
                            int index = currentlyPlayedCase
                                    .getUsedMemoItem()
                                    .getCorrectAnswer()
                                    .indexOf(word);

                            if (index == 0){
                                creatingProgressPhrase
                                        .append(word);
                            } else {
                                creatingProgressPhrase
                                        .append(progressPhrase, 0, index)
                                        .append(word);
                            }
                            creatingProgressPhrase.append(progressPhrase.substring(index+word.length()));

                            if (!creatingProgressPhrase.toString().isBlank())
                                currentlyPlayedCase.setProgressPhrase(creatingProgressPhrase.toString());
                        }
                    }});
    }
}
