package com.cezaryzal.api.memo;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.response.ResponseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemoControllerImp implements MemoController {

    private ResponseService responseService;

    public MemoControllerImp(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/first")
    @Override
    public SentenceDTO getFirstSentence() {
        return null;
    }

    @PostMapping ("/result")
    @Override
    public SentenceDTO resultByInputAnswer(@RequestBody Answer answer) {
        return responseService.resultByInputAnswer(answer);
    }
}
