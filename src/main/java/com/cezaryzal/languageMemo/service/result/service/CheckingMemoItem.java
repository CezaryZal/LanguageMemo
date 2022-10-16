package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import org.springframework.stereotype.Service;

@Service
abstract class CheckingMemoItem {

    //TODO usunąć type z componentu TranslateComponentInput
    boolean checkingCorrectnessOfPhraseTranslation(MemoItemDtoInput memoItemDtoInput,
                                                   MemoItem currentlyUsedMemoItem) {

        return memoItemDtoInput
                .getPhrase()
                .equalsIgnoreCase(currentlyUsedMemoItem
                                        .getCorrectAnswer());
    }
}
