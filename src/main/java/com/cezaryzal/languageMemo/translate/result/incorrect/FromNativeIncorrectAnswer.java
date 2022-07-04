package com.cezaryzal.languageMemo.translate.result.incorrect;

import com.cezaryzal.languageMemo.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.model.SentenceModel;
import com.cezaryzal.languageMemo.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.translate.result.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FromNativeIncorrectAnswer {

    private final PhraseDivider phraseDivider;
    private final FirstLetter firstLetter;
    private final FirstLetters firstLetters;
    private final EverySecondLetter everySecondLetter;

    @Autowired
    public FromNativeIncorrectAnswer(PhraseDivider phraseDivider, FirstLetter firstLetter,
                                     FirstLetters firstLetters, EverySecondLetter everySecondLetter) {
        this.phraseDivider = phraseDivider;
        this.firstLetter = firstLetter;
        this.firstLetters = firstLetters;
        this.everySecondLetter = everySecondLetter;
    }

    public TranslateComponentDto inputValidationBasedOnNumberOfTries(TranslateComponentInput translateComponentInput,
                                                                     SentenceModel currentlyUsedSentenceModel) {

        String patternPhrase = currentlyUsedSentenceModel.getLanguageEng();
        InputLettersComparator inputLettersComparator =
                new InputLettersComparator(translateComponentInput.getPhrase(), patternPhrase);
        String progressPhraseByInput = inputLettersComparator.createProgressThroughLastTries();
        int numberOfTries = translateComponentInput.getNumberOfTries();

        if (numberOfTries == 0){
            return createSentenceDTOByValidator(progressPhraseByInput,
                                                translateComponentInput,
                                                currentlyUsedSentenceModel);
        }
        String sharePhraseIntoWords = sharePhraseIntoWords(currentlyUsedSentenceModel);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput,
                                                                    sharePhraseIntoWords,
                                                                    currentlyUsedSentenceModel);

        if (numberOfTries == 1) {
            return createSentenceDTOByValidator(progressPhraseByInput,
                                                translateComponentInput,
                                                currentlyUsedSentenceModel);
        }
        String phraseWithFirstLetter = firstLetter.getPhraseWithFirstLetter(patternPhrase);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput,
                                                                    phraseWithFirstLetter,
                                                                    currentlyUsedSentenceModel);

        if (numberOfTries == 2){
            return createSentenceDTOByValidator(progressPhraseByInput,
                                                translateComponentInput,
                                                currentlyUsedSentenceModel);
        }
        String phraseWithFirstLetters = firstLetters.getPhraseWithFirstLetters(
                                                            sharePhraseIntoWords(currentlyUsedSentenceModel),
                                                            patternPhrase);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput,
                                                                    phraseWithFirstLetters,
                                                                    currentlyUsedSentenceModel);

        if (numberOfTries == 3){
            return createSentenceDTOByValidator(progressPhraseByInput,
                                                translateComponentInput,
                                                currentlyUsedSentenceModel);
        }
        String phraseWithEverySecondLetter = everySecondLetter.getPhraseWithEverySecondLetter(patternPhrase);
        progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput,
                                                                    phraseWithEverySecondLetter,
                                                                    currentlyUsedSentenceModel);

        if (numberOfTries == 4){
            return createSentenceDTOByValidator(progressPhraseByInput,
                                                translateComponentInput,
                                                currentlyUsedSentenceModel);
        }
        progressPhraseByInput = currentlyUsedSentenceModel.getLanguageEng();

        return createSentenceDTOByValidator(progressPhraseByInput,
                                            translateComponentInput,
                                            currentlyUsedSentenceModel);
    }

    private String sharePhraseIntoWords(SentenceModel currentlyUsedSentenceModel) {
        return phraseDivider.sharePhraseIntoWords(currentlyUsedSentenceModel.getLanguageEng());
    }

    private String validProgressPhraseByPatternAndFilter(String progressPhraseByInput,
                                                         String modifiedPhrase,
                                                         SentenceModel currentlyUsedSentenceModel) {
        PhrasesValidator phrasesValidator = new PhrasesValidator(progressPhraseByInput,
                                                                currentlyUsedSentenceModel.getLanguageEng());

        return phrasesValidator.validProgressPhraseByPatternAndFilter(modifiedPhrase);
    }

    private TranslateComponentDto createSentenceDTOByValidator(String progressPhrase,
                                                               TranslateComponentInput translateComponentInput,
                                                               SentenceModel currentlyUsedSentenceModel) {
        int numberOfTries = translateComponentInput.getNumberOfTries() + 1;

        return TranslateComponentDto.builder()
                .sentenceId(translateComponentInput.getSentenceId())
                .headerToTranslate(currentlyUsedSentenceModel.getHintFromNative())
                .progressThroughLastTries(progressPhrase)
                .isCorrectAnswer(false)
                .numberOfTries(numberOfTries)
                .hint(currentlyUsedSentenceModel.getHintFromNative())
                .build();
    }

}
