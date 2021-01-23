package com.cezaryzal.languageMemo.web.controllers.translate;

import com.cezaryzal.languageMemo.application.TranslateService;
import com.cezaryzal.languageMemo.application.model.AppendSentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/general")
public class GeneralTranslateController {

    private final TranslateService translateService;

    @Autowired
    public GeneralTranslateController(TranslateService translateService) {
        this.translateService = translateService;
    }

    @PostMapping("/add")
    public String addNewSentence(@RequestBody AppendSentence appendSentence){
        return translateService
                .handleFromNativeTranslateService()
                .addNewSentenceThroughInputSentence(appendSentence);
    }
}
