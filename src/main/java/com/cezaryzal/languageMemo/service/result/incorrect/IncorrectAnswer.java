package com.cezaryzal.languageMemo.service.result.incorrect;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.result.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncorrectAnswer {

    private final PhraseDivider phraseDivider;
    private final FirstLetter firstLetter;
    private final FirstLetters firstLetters;
    private final EverySecondLetter everySecondLetter;

    @Autowired
    public IncorrectAnswer(PhraseDivider phraseDivider, FirstLetter firstLetter,
                           FirstLetters firstLetters, EverySecondLetter everySecondLetter) {
        this.phraseDivider = phraseDivider;
        this.firstLetter = firstLetter;
        this.firstLetters = firstLetters;
        this.everySecondLetter = everySecondLetter;
    }

    public ComponentDtoOutput inputValidationBasedOnNumberOfTries(ComponentDtoInput componentDtoInput,
                                                                  Sentence currentlyUsedSentence) {

        String patternPhrase = currentlyUsedSentence.getCorrectAnswer();
        InputLettersComparator inputLettersComparator =
                new InputLettersComparator(componentDtoInput.getPhrase(), patternPhrase);
        String progressPhraseByInput = inputLettersComparator.createProgressThroughLastTries();
        int numberOfTries = componentDtoInput.getNumberOfTries();

        if (numberOfTries == 0){
            return createSentenceDTOByValidator(progressPhraseByInput,
                    componentDtoInput,
                    currentlyUsedSentence);
        }
        String sharePhraseIntoWords = sharePhraseIntoWords(currentlyUsedSentence);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput,
                                                                    sharePhraseIntoWords,
                currentlyUsedSentence);

        if (numberOfTries == 1) {
            return createSentenceDTOByValidator(progressPhraseByInput,
                    componentDtoInput,
                    currentlyUsedSentence);
        }
        String phraseWithFirstLetter = firstLetter.getPhraseWithFirstLetter(patternPhrase);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput,
                                                                    phraseWithFirstLetter,
                currentlyUsedSentence);

        if (numberOfTries == 2){
            return createSentenceDTOByValidator(progressPhraseByInput,
                    componentDtoInput,
                    currentlyUsedSentence);
        }
        String phraseWithFirstLetters = firstLetters.getPhraseWithFirstLetters(
                                                            sharePhraseIntoWords(currentlyUsedSentence),
                                                            patternPhrase);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput,
                                                                    phraseWithFirstLetters,
                currentlyUsedSentence);

        if (numberOfTries == 3){
            return createSentenceDTOByValidator(progressPhraseByInput,
                    componentDtoInput,
                    currentlyUsedSentence);
        }
        String phraseWithEverySecondLetter = everySecondLetter.getPhraseWithEverySecondLetter(patternPhrase);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput,
                                                                    phraseWithEverySecondLetter,
                currentlyUsedSentence);

        if (numberOfTries == 4){
            return createSentenceDTOByValidator(progressPhraseByInput,
                    componentDtoInput,
                    currentlyUsedSentence);
        }
        progressPhraseByInput = currentlyUsedSentence.getCorrectAnswer();

        return createSentenceDTOByValidator(progressPhraseByInput,
                componentDtoInput,
                currentlyUsedSentence);
    }

    private String sharePhraseIntoWords(Sentence currentlyUsedSentence) {
        return phraseDivider.sharePhraseIntoWords(currentlyUsedSentence.getCorrectAnswer());
    }

    private String validProgressPhraseByPatternAndFilter(String progressPhraseByInput,
                                                         String modifiedPhrase,
                                                         Sentence currentlyUsedSentence) {
        PhrasesValidator phrasesValidator = new PhrasesValidator(progressPhraseByInput,
                                                                currentlyUsedSentence.getCorrectAnswer());

        return phrasesValidator.validProgressPhraseByPatternAndFilter(modifiedPhrase);
    }

    private ComponentDtoOutput createSentenceDTOByValidator(String progressPhrase,
                                                            ComponentDtoInput componentDtoInput,
                                                            Sentence currentlyUsedSentence) {
        int numberOfTries = componentDtoInput.getNumberOfTries() + 1;

        return ComponentDtoOutput.builder()
                .sentenceId(componentDtoInput.getSentenceId())
                .headerToTranslate(currentlyUsedSentence.getClues())
                .progressThroughLastTries(progressPhrase)
                .isCorrectAnswer(false)
                .numberOfTries(numberOfTries)
                .hint(currentlyUsedSentence.getHint())
                .build();
    }

}
