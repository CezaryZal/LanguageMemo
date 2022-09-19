package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.stereotype.Service;

@Service
abstract class CheckingSentences {

    //TODO usunąć type z componentu TranslateComponentInput
    boolean checkingCorrectnessOfPhraseTranslation(ComponentDtoInput componentDtoInput,
                                                   Sentence currentlyUsedSentence) {

        return componentDtoInput
                .getPhrase()
                .equalsIgnoreCase(currentlyUsedSentence
                                        .getCorrectAnswer());
    }
}
