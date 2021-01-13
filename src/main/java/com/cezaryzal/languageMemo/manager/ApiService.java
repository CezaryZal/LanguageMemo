package com.cezaryzal.languageMemo.manager;

import com.cezaryzal.languageMemo.application.entity.InputAnswer;
import com.cezaryzal.languageMemo.application.entity.SentenceToAdd;
import com.cezaryzal.languageMemo.application.entity.SentenceDTO;
import com.cezaryzal.languageMemo.manager.add.SupplementData;
import com.cezaryzal.languageMemo.manager.difficult.MostDifficultShortSentence;
import com.cezaryzal.languageMemo.manager.first.Starter;
import com.cezaryzal.languageMemo.manager.result.ResponseService;
import com.cezaryzal.languageMemo.manager.service.repository.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class ApiService {

    private final SentenceService sentenceService;
    private final SupplementData supplementData;
    private final ResponseService responseService;
    private final Starter starter;
    private final MostDifficultShortSentence difficultSentence;

    @Autowired
    public ApiService(SentenceService sentenceService, SupplementData supplementData,
                      ResponseService responseService, Starter starter, MostDifficultShortSentence difficultSentence) {
        this.sentenceService = sentenceService;
        this.supplementData = supplementData;
        this.responseService = responseService;
        this.starter = starter;
        this.difficultSentence = difficultSentence;
    }

    public SentenceDTO getFirstSentenceDto(){
        return starter.getFirstSentenceDto();
    }

    public String addNewSentenceThroughInputSentence (SentenceToAdd sentenceToAdd){
        sentenceService.addNewSentence(supplementData.fillInMissingData(sentenceToAdd));

        return "Nowy record został umieszczony w bazie danych";
    }

    public SentenceDTO getResultByInputAnswer(InputAnswer inputAnswer){
        return responseService.resultByInputAnswer(inputAnswer);
    }

    public Map<String, String> getMapWithMostDifficultSentence(){
        return difficultSentence.getMapWithDifficultSentence();
    }

    public Optional<Integer> getCounterReplayDateLessThanEqual() {
        return sentenceService.getCounter(LocalDate.now());
    }

}
