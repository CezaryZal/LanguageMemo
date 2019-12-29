package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.response.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncorrectAnswer {

    private PhraseDivider phraseDivider;
    private FirstLetter firstLetter;
    private FirstLetters firstLetters;
    private EverySecondLetter everySecondLetter;

    @Autowired
    public IncorrectAnswer(PhraseDivider phraseDivider, FirstLetter firstLetter,
                           FirstLetters firstLetters, EverySecondLetter everySecondLetter) {
        this.phraseDivider = phraseDivider;
        this.firstLetter = firstLetter;
        this.firstLetters = firstLetters;
        this.everySecondLetter = everySecondLetter;
    }

    public SentenceDTO inputValidationBasedOnNumberOfTries(Answer inputAnswer, Sentence currentlyUsedSentence) {

        String patternPhrase = currentlyUsedSentence.getLanguageEng();
        InputLettersComparator inputLettersComparator = new InputLettersComparator(inputAnswer.getPhrase(), patternPhrase);
        String progressPhraseByInput = inputLettersComparator.createProgressThroughLastTries();
        int numberOfTries = inputAnswer.getNumberOfTries();

        if (numberOfTries == 0){
            return createSentenceDTOByValidator(progressPhraseByInput, inputAnswer, currentlyUsedSentence);
        }
        String sharePhraseIntoWords = sharePhraseIntoWords(currentlyUsedSentence);
        progressPhraseByInput =
                validProgressPhraseByPatternAndFilter(progressPhraseByInput, sharePhraseIntoWords, currentlyUsedSentence);

        if (numberOfTries == 1) {
            return createSentenceDTOByValidator(progressPhraseByInput, inputAnswer, currentlyUsedSentence);
        }
        String phraseWithFirstLetter = firstLetter.getPhraseWithFirstLetter(patternPhrase);
        progressPhraseByInput =
                validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithFirstLetter, currentlyUsedSentence);

        if (numberOfTries == 2){
            return createSentenceDTOByValidator(progressPhraseByInput, inputAnswer, currentlyUsedSentence);
        }
        String phraseWithFirstLetters =
                firstLetters.getPhraseWithFirstLetters(sharePhraseIntoWords(currentlyUsedSentence), patternPhrase);
        progressPhraseByInput =
                validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithFirstLetters, currentlyUsedSentence);

        if (numberOfTries == 3){
            return createSentenceDTOByValidator(progressPhraseByInput, inputAnswer, currentlyUsedSentence);
        }
        String phraseWithEverySecondLetter = everySecondLetter.getPhraseWithEverySecondLetter(patternPhrase);
        progressPhraseByInput =
                validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithEverySecondLetter, currentlyUsedSentence);

        if (numberOfTries == 4){
            return createSentenceDTOByValidator(progressPhraseByInput, inputAnswer, currentlyUsedSentence);
        }
        progressPhraseByInput = currentlyUsedSentence.getLanguageEng();

        return createSentenceDTOByValidator(progressPhraseByInput, inputAnswer, currentlyUsedSentence);
    }

    private String sharePhraseIntoWords(Sentence currentlyUsedSentence) {
        return phraseDivider.sharePhraseIntoWords(currentlyUsedSentence.getLanguageEng());
    }

    private String validProgressPhraseByPatternAndFilter(String progressPhraseByInput,
                                                         String modifiedPhrase,
                                                         Sentence currentlyUsedSentence) {
        PhrasesValidator phrasesValidator = new PhrasesValidator(progressPhraseByInput, currentlyUsedSentence.getLanguageEng());

        return phrasesValidator.validProgressPhraseByPatternAndFilter(modifiedPhrase);
    }

    private SentenceDTO createSentenceDTOByValidator(String progressPhrase, Answer inputAnswer, Sentence currentlyUsedSentence) {

        int numberOfTries = inputAnswer.getNumberOfTries() + 1;
        return new SentenceDTO(
                inputAnswer.getSentenceId(),
                currentlyUsedSentence.getLanguagePol(),
                progressPhrase,
                false,
                numberOfTries,
                currentlyUsedSentence.getHint());
    }

}
