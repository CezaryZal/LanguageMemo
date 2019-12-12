package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.filter.FirstLetter;
import com.cezaryzal.manager.filter.PhraseDivider;
import com.cezaryzal.manager.filter.InputLettersComparator;
import com.cezaryzal.manager.filter.PhrasesValidator;
import org.springframework.stereotype.Component;

@Component
public class ResponseValidator {

    private Sentence currentlyUseSentence;

    private FirstLetter firstLetter;
    private PhraseDivider phraseDivider;

    public ResponseValidator(FirstLetter firstLetter, PhraseDivider phraseDivider) {
        this.firstLetter = firstLetter;
        this.phraseDivider = phraseDivider;
    }

    public void setCurrentlyUseSentence(Sentence sentence) {
        this.currentlyUseSentence = sentence;
    }

    public SentenceDTO createSentenceDTOByInputAnswer(Answer inputAnswer) {
        InputLettersComparator inputLettersComparator = new InputLettersComparator(inputAnswer.getPhrase(), currentlyUseSentence.getLanguageEng());
        String progressPhraseByInput = inputLettersComparator.createProgressThroughLastTries();
        int numberOfTries = inputAnswer.getNumberOfTries();
        switch (numberOfTries){
            case 1:
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, sharePhraseIntoWords());
                break;
            case 2:
                String phraseWithFirstLetter = firstLetter.getPhraseWithFirstLetter(currentlyUseSentence.getLanguageEng());
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, sharePhraseIntoWords());
                progressPhraseByInput = validProgressPhraseByPatternAndFilter(progressPhraseByInput, phraseWithFirstLetter);
                break;
            case 3:

        }
        numberOfTries++;

        return new SentenceDTO(
                inputAnswer.getSentenceId(),
                currentlyUseSentence.getLanguagePol(),
                progressPhraseByInput,
                false,
                numberOfTries,
                currentlyUseSentence.getHint());
    }

    private String sharePhraseIntoWords(){
        return phraseDivider.sharePhraseIntoWords(currentlyUseSentence.getLanguageEng());
    }

    private String validProgressPhraseByPatternAndFilter(String progressPhraseByInput, String modifiedPhrase){
        PhrasesValidator phrasesValidator = new PhrasesValidator(progressPhraseByInput, currentlyUseSentence.getLanguageEng());
        phrasesValidator.setModifiedPhrase(modifiedPhrase);
        return phrasesValidator.validProgressPhraseByPatternAndFilter();
    }

}
