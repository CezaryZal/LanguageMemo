package com.cezaryzal.languageMemo.web.controllers.memo;

import com.cezaryzal.languageMemo.model.AppendSentence;
import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MemoController {

    ComponentDtoOutput getFirstSentence ();
    ComponentDtoOutput resultByInputAnswer (ComponentDtoInput componentDtoInput);
    Map<String, String> getMapWithMostDifficultSentence();
    Optional<Integer> getCounter();
    String addNewSentence(AppendSentence appendSentence);
    List<Sentence> searchSentenceListOfSimilarSpellingsByClues(String word);
    List<Sentence> searchSentenceListOfSimilarSpellingsByCorrectAnswer(String word);

}
