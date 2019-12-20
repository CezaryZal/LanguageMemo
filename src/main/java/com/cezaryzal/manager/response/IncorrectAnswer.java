package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.response.filter.*;
import org.springframework.stereotype.Component;

@Component
public class IncorrectAnswer {

    private Sentence currentlyUsedSentence;
    private Answer inputAnswer;

    private PhraseDivider phraseDivider;
    private FirstLetter firstLetter;
    private FirstLetters firstLetters;
    private EverySecondLetter everySecondLetter;

    public IncorrectAnswer(PhraseDivider phraseDivider, FirstLetter firstLetter,
                           FirstLetters firstLetters, EverySecondLetter everySecondLetter) {
        this.phraseDivider = phraseDivider;
        this.firstLetter = firstLetter;
        this.firstLetters = firstLetters;
        this.everySecondLetter = everySecondLetter;
    }

    public void setCurrentlyUsedSentence(Sentence sentence) {
        this.currentlyUsedSentence = sentence;
    }

    public void setInputAnswer(Answer inputAnswer) {
        this.inputAnswer = inputAnswer;
    }

    public SentenceDTO inputValidationBasedOnNumberOfTries() {

        String patternPhrase = currentlyUsedSentence.getLanguageEng();
        InputLettersComparator inputLettersComparator = new InputLettersComparator(inputAnswer.getPhrase(), patternPhrase);
        String progressPhraseByInput = inputLettersComparator.createProgressThroughLastTries();
        int numberOfTries = inputAnswer.getNumberOfTries();

        if (numberOfTries == 0){
            return createSentenceDTOByValidator(progressPhraseByInput);
        }
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, sharePhraseIntoWords());
        if (numberOfTries == 1) {
            return createSentenceDTOByValidator(progressPhraseByInput);
        }
        String phraseWithFirstLetter = firstLetter.getPhraseWithFirstLetter(patternPhrase);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithFirstLetter);
        if (numberOfTries == 2){
            return createSentenceDTOByValidator(progressPhraseByInput);
        }
        firstLetters.setPatternPhrase(patternPhrase);
        String phraseWithFirstLetters = firstLetters.getPhraseWithFirstLetters(sharePhraseIntoWords());
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithFirstLetters);
        if (numberOfTries == 3){
            return createSentenceDTOByValidator(progressPhraseByInput);
        }
        String phraseWithEverySecondLetter = everySecondLetter.getPhraseWithEverySecondLetter(patternPhrase);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithEverySecondLetter);
        if (numberOfTries == 4){
            return createSentenceDTOByValidator(progressPhraseByInput);
        }
        progressPhraseByInput = currentlyUsedSentence.getLanguageEng();

        return createSentenceDTOByValidator(progressPhraseByInput);
    }

    private String sharePhraseIntoWords() {
        return phraseDivider.sharePhraseIntoWords(currentlyUsedSentence.getLanguageEng());
    }

    private String validProgressPhraseByPatternAndFilter(String progressPhraseByInput, String modifiedPhrase) {

        PhrasesValidator phrasesValidator = new PhrasesValidator(progressPhraseByInput, currentlyUsedSentence.getLanguageEng());
        phrasesValidator.setModifiedPhrase(modifiedPhrase);
        return phrasesValidator.validProgressPhraseByPatternAndFilter();
    }

    private SentenceDTO createSentenceDTOByValidator(String progressPhrase) {

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
