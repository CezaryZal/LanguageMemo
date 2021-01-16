package com.cezaryzal.languageMemo.application.translate.add;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.application.model.AppendSentence;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SentenceModelCreator {

    public SentenceModel createByInput(AppendSentence appendSentence){
         return SentenceModel.builder()
                 .id(null)
                 .languageEng(appendSentence.getLanguageEng())
                 .languagePol(appendSentence.getLanguagePol())
                 .hintFromNative(appendSentence.getHintFromNative())
                 .hintToNative(appendSentence.getHintToNative())
                 .replayLevelFromNative(0)
                 .replayLevelToNative(0)
                 .replayDateFromNative(LocalDate.now())
                 .replayDateToNative(LocalDate.now())
                 .dateCreated(LocalDate.now())
                 .build();
    }

}
