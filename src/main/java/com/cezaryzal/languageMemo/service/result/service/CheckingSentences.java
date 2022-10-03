package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.stereotype.Service;

@Service
abstract class CheckingSentences {

    //TODO usunąć type z componentu TranslateComponentInput
    boolean checkingCorrectnessOfPhraseTranslation(MemoItemDtoInput memoItemDtoInput,
                                                   Sentence currentlyUsedSentence) {

        return memoItemDtoInput
                .getPhrase()
                .equalsIgnoreCase(currentlyUsedSentence
                                        .getCorrectAnswer());
    }
}
