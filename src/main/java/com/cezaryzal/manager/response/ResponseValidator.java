package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.phrase.PhraseValidator;
import org.springframework.stereotype.Component;

@Component
public class ResponseValidator {

    private Sentence currentlyUseSentence;

    private PhraseValidator phraseValidator;

    public ResponseValidator(PhraseValidator phraseValidator) {
        this.phraseValidator = phraseValidator;
    }

    public SentenceDTO createSentenceDTOByInputAnswer(Answer inputAnswer) {
        phraseValidator.setInputPhrase(inputAnswer.getInputPhrase());
        phraseValidator.setPatternPhrase(currentlyUseSentence.getLanguageEng());
        return new SentenceDTO(
                inputAnswer.getSentenceId(),
                currentlyUseSentence.getLanguagePol(),
                phraseValidator.createProgressThroughLastTries(),
                false,
                1,
                currentlyUseSentence.getHint());
    }

    public void setCurrentlyUseSentence(Sentence sentence) {
        this.currentlyUseSentence = sentence;
    }
}
