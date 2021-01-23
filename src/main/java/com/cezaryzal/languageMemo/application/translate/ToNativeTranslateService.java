package com.cezaryzal.languageMemo.application.translate;

import com.cezaryzal.languageMemo.application.model.AppendSentence;
import com.cezaryzal.languageMemo.application.reposervice.RepoService;
import com.cezaryzal.languageMemo.application.translate.add.SentenceModelCreator;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentDto;
import com.cezaryzal.languageMemo.application.translate.components.TranslateComponentInput;
import com.cezaryzal.languageMemo.application.translate.difficult.Difficult;
import com.cezaryzal.languageMemo.application.translate.first.First;
import com.cezaryzal.languageMemo.application.translate.result.service.ToNativeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class ToNativeTranslateService {

    private final RepoService repoService;
    private final SentenceModelCreator sentenceModelCreator;
    private final ToNativeResultService toNativeResultService;
    private final First toNativeFirstImpl;
    private final Difficult difficultSentence;

    @Autowired
    public ToNativeTranslateService(@Qualifier("toNativeRepoServiceImp") RepoService repoService,
                                    SentenceModelCreator sentenceModelCreator,
                                    ToNativeResultService toNativeResultService,
                                    First toNativeFirstImpl,
                                    @Qualifier("shortSentenceToNativeDifficultImpl") Difficult difficultSentence) {
        this.repoService = repoService;
        this.sentenceModelCreator = sentenceModelCreator;
        this.toNativeResultService = toNativeResultService;
        this.toNativeFirstImpl = toNativeFirstImpl;
        this.difficultSentence = difficultSentence;
    }

    public TranslateComponentDto getFirstSentenceDto(){
        return toNativeFirstImpl.getFirstSentenceDto();
    }

    public String addNewSentenceThroughInputSentence (AppendSentence appendSentence){
        repoService.addNewSentence(sentenceModelCreator.createByInput(appendSentence));

        return "Nowy record został umieszczony w bazie danych";
    }

    public TranslateComponentDto getResultByInputAnswer(TranslateComponentInput translateComponentInput){
        return toNativeResultService.resultByInputAnswer(translateComponentInput);
    }

    public Map<String, String> getMapWithMostDifficultSentence(){
        return difficultSentence.getMapDifficultSentence();
    }

    public Optional<Integer> getCounterReplayDateLessThanEqual() {
        return repoService.getCounter(LocalDate.now());
    }
}
