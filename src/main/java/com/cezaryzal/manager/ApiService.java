package com.cezaryzal.manager;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.AddSentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.result.ResponseService;
import com.cezaryzal.manager.service.repository.SentenceService;
import com.cezaryzal.manager.add.SupplementData;
import com.cezaryzal.manager.start.Starter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private SentenceService sentenceService;
    private SupplementData supplementData;
    private ResponseService responseService;
    private Starter starter;

    @Autowired
    public ApiService(SentenceService sentenceService, SupplementData supplementData,
                      ResponseService responseService, Starter starter) {
        this.sentenceService = sentenceService;
        this.supplementData = supplementData;
        this.responseService = responseService;
        this.starter = starter;
    }

    public SentenceDTO getFirstSentenceDto(){
        return starter.getFirstSentenceDto();
    }

    public String addNewSentenceThroughInputSentence (AddSentence addSentence){
        sentenceService.addNewSentence(supplementData.fillInMissingData(addSentence));

        return "Nowy record został umieszczony w bazie danych";
    }

    public SentenceDTO getResultByInputAnswer(Answer answer){
        return responseService.resultByInputAnswer(answer);
    }



}
