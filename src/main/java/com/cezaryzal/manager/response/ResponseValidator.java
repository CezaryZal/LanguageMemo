package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.filter.PhraseSplitter;
import com.cezaryzal.manager.filter.LetterComparator;
import com.cezaryzal.manager.filter.PhraseValidator;
import org.springframework.stereotype.Component;

@Component
public class ResponseValidator {

    private final int VALUE_OF_USE_PHRASE_SPLITTER = 1;
    private final int VALUE_ABOVE_USE_PHRASE_VALIDATOR = 2;
    private Sentence currentlyUseSentence;

    private PhraseSplitter phraseSplitter;

    public ResponseValidator(PhraseSplitter phraseSplitter) {
        this.phraseSplitter = phraseSplitter;
    }

    public void setCurrentlyUseSentence(Sentence sentence) {
        this.currentlyUseSentence = sentence;
    }

    public SentenceDTO createSentenceDTOByInputAnswer(Answer inputAnswer) {
        LetterComparator letterComparator = new LetterComparator(inputAnswer.getPhrase(), currentlyUseSentence.getLanguageEng());
        String progressPhrase = letterComparator.createProgressThroughLastTries();
        int numberOfTries = inputAnswer.getNumberOfTries();
        switch (numberOfTries){
            case 1:
                PhraseValidator phraseValidator = new PhraseValidator(progressPhrase, currentlyUseSentence.getLanguageEng());
                String shareSentenceIntoWords = phraseSplitter.shareSentenceIntoWords(currentlyUseSentence.getLanguageEng());
                progressPhrase = phraseValidator.validInputPhraseByLetterComparatorAndPhraseSplitter(shareSentenceIntoWords);
                break;
            case 2:

        }
        numberOfTries++;



        return new SentenceDTO(
                inputAnswer.getSentenceId(),
                currentlyUseSentence.getLanguagePol(),
                progressPhrase,
                false,
                numberOfTries,
                currentlyUseSentence.getHint());
    }




}
