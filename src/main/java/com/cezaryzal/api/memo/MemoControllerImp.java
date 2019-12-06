package com.cezaryzal.api.memo;

import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.response.ResponseService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoControllerImp implements MemoController {

    private ResponseService responseService;

    public MemoControllerImp(ResponseService responseService) {
        this.responseService = responseService;
    }

    @Override
    public SentenceDTO getFirstSentence() {
        return null;
    }

    @Override
    public SentenceDTO resultByInputSentenceDTO(SentenceDTO sentenceDTO) {
        return null;
    }
}
