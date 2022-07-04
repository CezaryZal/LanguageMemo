package com.cezaryzal.languageMemo.web.controllers.translate;

import com.cezaryzal.languageMemo.TranslateService;
import com.cezaryzal.languageMemo.model.CreatingSentenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/general")
public class GeneralTranslateControllerImp {

    private final TranslateService translateService;

    @Autowired
    public GeneralTranslateControllerImp(TranslateService translateService) {
        this.translateService = translateService;
    }

    @PostMapping("/add")
    public String addNewSentence(@RequestBody CreatingSentenceModel creatingSentenceModel){
        return translateService
                .handleFromNativeTranslateService()
                .addNewSentenceThroughInputSentence(creatingSentenceModel);
    }
}
