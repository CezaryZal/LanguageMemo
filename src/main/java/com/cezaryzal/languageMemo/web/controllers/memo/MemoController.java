package com.cezaryzal.languageMemo.web.controllers.memo;

import com.cezaryzal.languageMemo.model.AppendSentence;
import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface MemoController {

    MemoItemDtoOutput getFirstSentence ();
    MemoItemDtoOutput resultByInputAnswer (MemoItemDtoInput memoItemDtoInput);
    Map<String, String> getMapWithMostDifficultSentence();
    Optional<Integer> getCounter();
    String addNewSentence(AppendSentence appendSentence);
    Set<Sentence> searchSentenceListOfSimilarSpellingsByClues(String word);
    Set<Sentence> searchSentenceListOfSimilarSpellingsByCorrectAnswer(String word);

}
