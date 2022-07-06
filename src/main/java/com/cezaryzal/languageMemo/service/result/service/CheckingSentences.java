package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.result.SentencesComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
abstract class CheckingSentences {

    private final SentencesComparator sentencesComparator;

    @Autowired
    public CheckingSentences(SentencesComparator sentencesComparator) {
        this.sentencesComparator = sentencesComparator;
    }

    //TODO usunąć type z componentu TranslateComponentInput
    boolean checkingCorrectnessOfPhraseTranslation(ComponentDtoInput componentDtoInput,
                                                   Sentence currentlyUsedSentence) {

        return sentencesComparator.comparingInputPhrasesWithPattern(componentDtoInput, currentlyUsedSentence);
    }
}
