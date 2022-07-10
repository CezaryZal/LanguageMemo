package com.cezaryzal.languageMemo.repository.service;

import com.cezaryzal.languageMemo.repository.entity.Sentence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RepositorySentenceService {

    Optional<Sentence> findById(Long id);
    List<Sentence> findAll();
    Sentence addNewSentence(Sentence sentence);
    public Sentence updateSentence(Sentence sentence);
    public void deleteSentenceById(Long id);

    List<Sentence> getByReplayDateLessThanEqual(LocalDate localDate);
    Optional<Sentence> getRandomByReplayDateLessThanEqual(Object localDate);
    List<Sentence> getListSentenceByLowerReplayLevel(int limitReplayLevel);
    List<Sentence> getSentenceByCorrectAnswer(String correctAnswer);
    Optional<Integer> getCounterReplayDateLessThanEqual(LocalDate localDate);
    List<Sentence> getSentenceListByCluesContainingInsideString(String pattern);
    List<Sentence> getSentenceListByAnswerContainingInsideString(String pattern);

}
