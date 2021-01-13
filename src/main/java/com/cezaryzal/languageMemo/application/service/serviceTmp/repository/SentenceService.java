package com.cezaryzal.languageMemo.application.service.serviceTmp.repository;

import com.cezaryzal.languageMemo.application.entity.Sentence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SentenceService {

    Optional<Sentence> findById(Long id);

    List<Sentence> findAllByReplayDateLessThanEqual(String localDate);

    List<Sentence> getListSentenceByLowestReplayLevel(int limitReplayLevel);

    Optional<Sentence> findRandomFirstByReplayDateLessThanEqual(String localDate);

    List<Sentence> findAll();

    Optional<Integer> getCounter(LocalDate localDate);

    Sentence addNewSentence (Sentence sentence);

    Sentence updateSentence (Sentence sentence);

    void deleteSentenceById(Long id);
}
