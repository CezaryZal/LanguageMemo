package com.cezaryzal.controllers.memo;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.InputSentence;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.response.ResponseService;
import com.cezaryzal.manager.serviceSentence.beforeAddNew.SupplementData;
import com.cezaryzal.manager.start.Starter;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MemoControllerImp implements MemoController {

    private ResponseService responseService;
    private Starter starter;
    private SupplementData supplementData;

    public MemoControllerImp(ResponseService responseService, Starter starter, SupplementData supplementData) {
        this.responseService = responseService;
        this.starter = starter;
        this.supplementData = supplementData;
    }

    @GetMapping("/first")
    @Override
    public SentenceDTO getFirstSentence() {
        return starter.getFirstSentenceDto();
    }

    @PostMapping ("/result")
    @Override
    public SentenceDTO resultByInputAnswer(@RequestBody Answer answer) {
        return responseService.resultByInputAnswer(answer);
    }

    @PostMapping ("/add")
    @Override
    public Sentence addNewSentence(@RequestBody InputSentence inputSentence) {
        return supplementData.addNewSentenceThroughInputSentence(inputSentence);
    }
}
