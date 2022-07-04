package com.cezaryzal.languageMemo.translate.result.service;

import com.cezaryzal.languageMemo.model.SentenceModel;
import com.cezaryzal.languageMemo.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.translate.result.SentencesComparator;
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
    boolean checkingCorrectnessOfPhraseTranslation(TranslateComponentInput translateComponentInput,
                                                           SentenceModel currentlyUsedSentenceModel) {

        return sentencesComparator.comparingInputPhrasesWithPattern(translateComponentInput.getType(),
                translateComponentInput,
                currentlyUsedSentenceModel);
    }
}
