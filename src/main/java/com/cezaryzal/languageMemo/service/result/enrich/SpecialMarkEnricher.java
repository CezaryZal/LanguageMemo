package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.service.result.filter.ReplacementBlankCharacters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialMarkEnricher implements Enricher{
    private final ReplacementBlankCharacters replacementBlankCharacters;

    @Autowired
    public SpecialMarkEnricher(ReplacementBlankCharacters replacementBlankCharacters) {
        this.replacementBlankCharacters = replacementBlankCharacters;
    }

    @Override
    public void enrichProgressPhrase(final CurrentPlayedMemoItem currentlyPlayedCase) {
        StringBuilder creatingProgressPhrase = new StringBuilder();
        String correctAnswer = currentlyPlayedCase
                .getUsedMemoItem()
                .getCorrectAnswer();

        String phrasesWithoutLetter =
                replacementBlankCharacters.replaceLetterOnEmptyChar(correctAnswer);

        for (int i = 0; i < correctAnswer.length(); i++) {
            if (phrasesWithoutLetter.charAt(i) != '_')
                creatingProgressPhrase.append(phrasesWithoutLetter
                        .charAt(i));
            else
                creatingProgressPhrase
                        .append(currentlyPlayedCase
                                .getProgressPhrase()
                                .charAt(i));
        }
        currentlyPlayedCase.setProgressPhrase(creatingProgressPhrase.toString());
    }
}
