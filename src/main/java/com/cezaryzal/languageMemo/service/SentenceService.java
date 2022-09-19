package com.cezaryzal.languageMemo.service;

import com.cezaryzal.languageMemo.model.*;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import com.cezaryzal.languageMemo.service.create.SentenceCreator;
import com.cezaryzal.languageMemo.service.difficult.Difficult;
import com.cezaryzal.languageMemo.service.first.FirstComponentDtoOutput;
import com.cezaryzal.languageMemo.service.result.service.ResultService;
import com.cezaryzal.languageMemo.service.search.FinderBySimilarSpellings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class SentenceService {
    private final RepositorySentenceService repositorySentenceService;
    private final SentenceCreator sentenceCreator;
    private final FirstComponentDtoOutput firstComponentDtoOutput;
    private final ResultService resultService;
    private final Difficult difficultSentence;
    private final FinderBySimilarSpellings finderBySimilarSpellings;

    @Autowired
    public SentenceService(RepositorySentenceService repositorySentenceService,
                           SentenceCreator sentenceCreator,
                           FirstComponentDtoOutput firstComponentDtoOutput,
                           ResultService resultService,
                           Difficult difficultSentence,
                           FinderBySimilarSpellings finderBySimilarSpellings) {
        this.repositorySentenceService = repositorySentenceService;
        this.sentenceCreator = sentenceCreator;
        this.firstComponentDtoOutput = firstComponentDtoOutput;
        this.resultService = resultService;
        this.difficultSentence = difficultSentence;
        this.finderBySimilarSpellings = finderBySimilarSpellings;
    }

    public String addNewSentenceThroughAppendedSentence(AppendSentence appendSentence) {
        Sentence similarSentence = getSimilarSentenceToNewSentence(
                appendSentence.getCorrectAnswer(),
                appendSentence.getClues());
        if (similarSentence == null || !isNotBlankSentence(appendSentence)) {
            repositorySentenceService.addNewSentence(
                    sentenceCreator.createSentenceByAppendedSentenceModel(appendSentence));
            return "New Sentence was appended to repository.";
        } else
            return "This Sentence has already been added to repository. Sentence: " + similarSentence.toString();
    }

    public ComponentDtoOutput getFirstComponentDtoOutput() {
         return firstComponentDtoOutput.getFirstComponentDtoOutput();
    }

    public ComponentDtoOutput getResultByInputAnswer(ComponentDtoInput componentDtoInput) {
        return resultService.resultByInputAnswer(componentDtoInput);
    }

    public Map<String, String> getMapWithMostDifficultSentence() {
        return difficultSentence.getMapDifficultSentence();
    }

    public Optional<Integer> getCounterReplayDateLessThanEqual() {
        return repositorySentenceService.getCounterReplayDateLessThanEqual(LocalDate.now());
    }

    public Set<Sentence> searchSentenceListOfSimilarSpellingsByClues(String sentenceInput) {
        return finderBySimilarSpellings.findSentenceListOfSimilarSpellingsByClues(
                sentenceInput,
                SentenceNavigator.CLUES);
    }

    public Set<Sentence> searchSentenceListOfSimilarSpellingsByAnswer(String sentenceInput) {
        return finderBySimilarSpellings.findSentenceListOfSimilarSpellingsByClues(
                sentenceInput,
                SentenceNavigator.CORRECT_ANSWER);
    }

    private Sentence getSimilarSentenceToNewSentence(String answerPattern, String cluesPattern) {
        return repositorySentenceService.getSentenceListByAnswerAndCluesContainingInsideString(
                finderBySimilarSpellings.parseWordBasedOnLength(answerPattern),
                finderBySimilarSpellings.parseWordBasedOnLength(cluesPattern));
    }

//    TODO Fine pattern to validation method
    private boolean isNotBlankSentence (AppendSentence appendSentence){
        if (appendSentence.getCorrectAnswer().isBlank() ||
                appendSentence.getCorrectAnswer().isEmpty() ||
                appendSentence.getCorrectAnswer() == null){
            return false;
        } else if (appendSentence.getClues().isBlank() ||
                appendSentence.getClues().isEmpty() ||
                appendSentence.getClues() == null){
            return false;
        } else if (appendSentence.getHint().isBlank() ||
                appendSentence.getHint().isEmpty() ||
                appendSentence.getHint() == null){
            return false;
        } else return !appendSentence.getExampleOfUse().isBlank() &&
                !appendSentence.getExampleOfUse().isEmpty() &&
                appendSentence.getExampleOfUse() != null;
    }
}
