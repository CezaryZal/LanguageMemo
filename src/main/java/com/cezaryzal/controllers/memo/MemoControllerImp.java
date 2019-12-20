package com.cezaryzal.controllers.memo;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.InputSentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.ApiService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MemoControllerImp implements MemoController {

    private ApiService apiService;

    public MemoControllerImp(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/first")
    @Override
    public SentenceDTO getFirstSentence() {
        return apiService.getFirstSentenceDto();
    }

    @PostMapping ("/result")
    @Override
    public SentenceDTO resultByInputAnswer(@RequestBody Answer answer) {
        return apiService.getResultByInputAnswer(answer);
    }

    @PostMapping ("/add")
    @Override
    public String addNewSentence(@RequestBody InputSentence inputSentence) {
        return apiService.addNewSentenceThroughInputSentence(inputSentence);
    }
}
