package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InputWordsFilter implements InputFilter {

    //TODO Move to extended file
    public static final List<String> THIRD_LETTER_WORDS_USED =
            Arrays.asList("for", "all", "the", "too", "she", "you", "was", "are", "out", "can", "now", "him", "aim");
    public static final List<String> USED_WORD_LESS_THAN_THREE_LETTERS = Arrays.asList("in", "In", "on", "On", "of",
            "if", "If", "by","do", "Do", "an", "at", "to", "it", "It", "he", "He", "we", "We", "is", "Is", "be",
            "am", "Am", "go", "up", "no", "No", "I", "a", "A");

    private CurrentPlayedMemoItem currentlyPlayedCase;

    @Override
    public void catchCorrectPieceToProgressPhrase(final CurrentPlayedMemoItem currentlyPlayedCase,
                                                  String inputPhrases) {
        this.currentlyPlayedCase = currentlyPlayedCase;
        String correctAnswer = currentlyPlayedCase.getUsedMemoItem().getCorrectAnswer();

        Arrays.stream(inputPhrases.split("[ '_\\-]"))
                .forEach(word -> {
                    if (USED_WORD_LESS_THAN_THREE_LETTERS.contains(word) && correctAnswer.contains(word)){
                        fillProgressPhraseByInputWord(word);
                    }
                    else if (word.length() > 3 || THIRD_LETTER_WORDS_USED.contains(word.toLowerCase())) {
                        String correctWord = getMatchingWord(word, correctAnswer);

                        if (correctWord != null) {
                            fillProgressPhraseByInputWord(correctWord);
                        }
                    }
                });
    }

    private String getMatchingWord(String inputWord, String correctAnswer) {
        String wordWithoutFirstLetter = inputWord.substring(1);
        if (correctAnswer.contains(wordWithoutFirstLetter)) {
            int indexStartWord = correctAnswer.indexOf(wordWithoutFirstLetter) - 1;

            return correctAnswer.substring(indexStartWord, indexStartWord + inputWord.length());
        }
        return null;
    }

    private void fillProgressPhraseByInputWord(String word){
        StringBuilder creatingProgressPhrase = new StringBuilder();
        String progressPhrase = currentlyPlayedCase.getProgressPhrase();

        int index = currentlyPlayedCase.getUsedMemoItem()
                .getCorrectAnswer()
                .indexOf(word);

        if (index == 0) {
            creatingProgressPhrase.append(word);
        } else {
            creatingProgressPhrase
                    .append(progressPhrase, 0, index)
                    .append(word);
        }
        creatingProgressPhrase.append(progressPhrase.substring(index + word.length()));

        if (!creatingProgressPhrase.toString().isBlank())
            currentlyPlayedCase.setProgressPhrase(creatingProgressPhrase.toString());
    }
}
