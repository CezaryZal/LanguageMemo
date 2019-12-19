package com.cezaryzal.api.memo;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.response.ResponseService;
import com.cezaryzal.manager.start.Starter;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MemoControllerImp implements MemoController {

    private ResponseService responseService;
    private Starter starter;

    public MemoControllerImp(ResponseService responseService, Starter starter) {
        this.responseService = responseService;
        this.starter = starter;
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
}
