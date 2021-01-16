package com.cezaryzal.languageMemo.application;

import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.model.AppendSentence;
import com.cezaryzal.languageMemo.application.translate.add.SentenceModelCreator;
import com.cezaryzal.languageMemo.application.translate.difficult.ShortSentenceFromNativeDifficultImpl;
import com.cezaryzal.languageMemo.application.translate.first.TranslateFromNativeFirstImpl;
import com.cezaryzal.languageMemo.application.translate.result.ResponseService;
import com.cezaryzal.languageMemo.application.reposervice.SentenceRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class ApiService {

    private final SentenceRepoService sentenceRepoService;
    private final SentenceModelCreator sentenceModelCreator;
    private final ResponseService responseService;
    private final TranslateFromNativeFirstImpl translateFromNativeFirstImpl;
    private final ShortSentenceFromNativeDifficultImpl difficultSentence;

    @Autowired
    public ApiService(SentenceRepoService sentenceRepoService, SentenceModelCreator sentenceModelCreator,
                      ResponseService responseService, TranslateFromNativeFirstImpl translateFromNativeFirstImpl, ShortSentenceFromNativeDifficultImpl difficultSentence) {
        this.sentenceRepoService = sentenceRepoService;
        this.sentenceModelCreator = sentenceModelCreator;
        this.responseService = responseService;
        this.translateFromNativeFirstImpl = translateFromNativeFirstImpl;
        this.difficultSentence = difficultSentence;
    }

    public TranslateComponentDto getFirstSentenceDto(){
        return translateFromNativeFirstImpl.getFirstSentenceDto();
    }

    public String addNewSentenceThroughInputSentence (AppendSentence appendSentence){
        sentenceRepoService.addNewSentence(sentenceModelCreator.createByInput(appendSentence));

        return "Nowy record został umieszczony w bazie danych";
    }

    public TranslateComponentDto getResultByInputAnswer(TranslateComponentInput translateComponentInput){
        return responseService.resultByInputAnswer(translateComponentInput);
    }

    public Map<String, String> getMapWithMostDifficultSentence(){
        return difficultSentence.getMapDifficultSentence();
    }

    public Optional<Integer> getCounterReplayDateLessThanEqual() {
        return sentenceRepoService.getCounter(LocalDate.now());
    }

}
