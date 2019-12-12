package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.filter.*;
import org.springframework.stereotype.Component;

@Component
public class ResponseValidator {

    private Sentence currentlyUseSentence;
    private Answer inputAnswer;

    private PhraseDivider phraseDivider;
    private FirstLetter firstLetter;
    private FirstLetters firstLetters;
    private EverySecondLetter everySecondLetter;

    public ResponseValidator(PhraseDivider phraseDivider, FirstLetter firstLetter,
                             FirstLetters firstLetters, EverySecondLetter everySecondLetter) {
        this.phraseDivider = phraseDivider;
        this.firstLetter = firstLetter;
        this.firstLetters = firstLetters;
        this.everySecondLetter = everySecondLetter;
    }

    public void setCurrentlyUseSentence(Sentence sentence) {
        this.currentlyUseSentence = sentence;
    }

    public void setInputAnswer(Answer inputAnswer) {
        this.inputAnswer = inputAnswer;
    }

    public SentenceDTO inputValidationBaseOnNumberOfTries() {
        String patternPhrase = currentlyUseSentence.getLanguageEng();
        InputLettersComparator inputLettersComparator = new InputLettersComparator(inputAnswer.getPhrase(), patternPhrase);
        String progressPhraseByInput = inputLettersComparator.createProgressThroughLastTries();
        switch (inputAnswer.getNumberOfTries()) {
            case 1:
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, sharePhraseIntoWords());
                return createSentenceDTOByValidator(progressPhraseByInput);
            case 2:
                String phraseWithFirstLetter = firstLetter.getPhraseWithFirstLetter(patternPhrase);
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, sharePhraseIntoWords());
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithFirstLetter);
                return createSentenceDTOByValidator(progressPhraseByInput);
            case 3:
                firstLetters.setPatternPhrase(patternPhrase);
                String phraseWithFirstLetters = firstLetters.getPhraseWithFirstLetters(sharePhraseIntoWords());
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, sharePhraseIntoWords());
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithFirstLetters);
                return createSentenceDTOByValidator(progressPhraseByInput);
            case 4:
                firstLetters.setPatternPhrase(patternPhrase);
                String phraseWithFirstLetters4 = firstLetters.getPhraseWithFirstLetters(sharePhraseIntoWords());
                String phraseWithEverySecondLetter = everySecondLetter.getPhraseWithEverySecondLetter(patternPhrase);
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, sharePhraseIntoWords());
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithFirstLetters4);
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithEverySecondLetter);
                return createSentenceDTOByValidator(progressPhraseByInput);
            case 5:
//                wyświetlenie całości

        }
        return createSentenceDTOByValidator(progressPhraseByInput);
    }

    private SentenceDTO createSentenceDTOByValidator(String progressPhrase) {
        int numberOfTries = inputAnswer.getNumberOfTries() + 1;
        return new SentenceDTO(
                inputAnswer.getSentenceId(),
                currentlyUseSentence.getLanguagePol(),
                progressPhrase,
                false,
                numberOfTries,
                currentlyUseSentence.getHint());
    }

    private String sharePhraseIntoWords() {
        return phraseDivider.sharePhraseIntoWords(currentlyUseSentence.getLanguageEng());
    }

    private String validProgressPhraseByPatternAndFilter(String progressPhraseByInput, String modifiedPhrase) {
        PhrasesValidator phrasesValidator = new PhrasesValidator(progressPhraseByInput, currentlyUseSentence.getLanguageEng());
        phrasesValidator.setModifiedPhrase(modifiedPhrase);
        return phrasesValidator.validProgressPhraseByPatternAndFilter();
    }

}
