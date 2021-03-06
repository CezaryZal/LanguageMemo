package com.cezaryzal.manager;

import com.cezaryzal.entity.InputAnswer;
import com.cezaryzal.entity.SentenceToAdd;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.difficult.MostDifficultShortSentence;
import com.cezaryzal.manager.result.ResponseService;
import com.cezaryzal.manager.service.repository.SentenceService;
import com.cezaryzal.manager.add.SupplementData;
import com.cezaryzal.manager.first.Starter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApiService {

    private SentenceService sentenceService;
    private SupplementData supplementData;
    private ResponseService responseService;
    private Starter starter;
    private MostDifficultShortSentence difficultSentence;

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

}
