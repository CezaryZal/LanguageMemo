package com.cezaryzal.languageMemo.web.controllers.memo;

import com.cezaryzal.languageMemo.model.AppendSentence;
import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface MemoController {

    ComponentDtoOutput getFirstSentence ();
    ComponentDtoOutput resultByInputAnswer (ComponentDtoInput componentDtoInput);
    Map<String, String> getMapWithMostDifficultSentence();
    Optional<Integer> getCounter();
    String addNewSentence(AppendSentence appendSentence);
    Set<Sentence> searchSentenceListOfSimilarSpellingsByClues(String word);
    Set<Sentence> searchSentenceListOfSimilarSpellingsByCorrectAnswer(String word);

}
