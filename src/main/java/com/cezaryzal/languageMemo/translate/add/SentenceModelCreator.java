package com.cezaryzal.languageMemo.translate.add;

import com.cezaryzal.languageMemo.model.SentenceModel;
import com.cezaryzal.languageMemo.model.CreatingSentenceModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SentenceModelCreator {

    public SentenceModel createByInput(CreatingSentenceModel creatingSentenceModel){
         return SentenceModel.builder()
                 .id(null)
                 .languageEng(creatingSentenceModel.getLanguageEng())
                 .languagePol(creatingSentenceModel.getLanguagePol())
                 .hintFromNative(creatingSentenceModel.getHintFromNative())
                 .hintToNative(creatingSentenceModel.getHintToNative())
                 .replayLevelFromNative(0)
                 .replayLevelToNative(0)
                 .replayDateFromNative(LocalDate.now())
                 .replayDateToNative(LocalDate.now())
                 .dateCreated(LocalDate.now())
                 .build();
    }

}
