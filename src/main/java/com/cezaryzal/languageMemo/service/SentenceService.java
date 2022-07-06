package com.cezaryzal.languageMemo.service;

import com.cezaryzal.languageMemo.model.AppendSentence;
import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import com.cezaryzal.languageMemo.service.create.SentenceCreator;
import com.cezaryzal.languageMemo.service.difficult.Difficult;
import com.cezaryzal.languageMemo.service.first.FirstComponentDtoOutput;
import com.cezaryzal.languageMemo.service.result.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class SentenceService {
    private final RepositorySentenceService repositorySentenceService;
    private final SentenceCreator sentenceCreator;
    private final FirstComponentDtoOutput firstComponentDtoOutput;
    private final ResultService resultService;
    private final Difficult difficultSentence;

    @Autowired
    public SentenceService(RepositorySentenceService repositorySentenceService,
                           SentenceCreator sentenceCreator,
                           FirstComponentDtoOutput firstComponentDtoOutput,
                           ResultService resultService,
                           Difficult difficultSentence) {
        this.repositorySentenceService = repositorySentenceService;
        this.sentenceCreator = sentenceCreator;
        this.firstComponentDtoOutput = firstComponentDtoOutput;
        this.resultService = resultService;
        this.difficultSentence = difficultSentence;
    }

    public String addNewSentenceThroughAppendedSentence(AppendSentence appendSentence){
        Sentence sentence = repositorySentenceService.addNewSentence(
                sentenceCreator.createSentenceByAppendedSentenceModel(appendSentence));
        return "Nowy record został dodany do bazy danych";
    }

    public ComponentDtoOutput getFirstComponentDtoOutput(){
        return firstComponentDtoOutput.getFirstComponentDtoOutput();
    }

    public ComponentDtoOutput getResultByInputAnswer(ComponentDtoInput componentDtoInput) {
        return resultService.resultByInputAnswer(componentDtoInput);
    }

    public Map<String, String> getMapWithMostDifficultSentence(){
        return difficultSentence.getMapDifficultSentence();
    }

    public Optional<Integer> getCounterReplayDateLessThanEqual() {
        return repositorySentenceService.getCounterReplayDateFromNativeLessThanEqual(LocalDate.now());
    }
}
