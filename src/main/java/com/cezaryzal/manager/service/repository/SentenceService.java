package com.cezaryzal.manager.service.repository;

import com.cezaryzal.entity.Sentence;

import java.util.List;
import java.util.Optional;

public interface SentenceService {

    Optional<Sentence> findById(Long id);

    List<Sentence> findAllByReplayDateLessThanEqual(String localDate);

    Optional<Sentence> findRandomFirstByReplayDateLessThanEqual(String localDate);

    List<Sentence> findAll();

    Sentence addNewSentence (Sentence sentence);

    Sentence updateSentence (Sentence sentence);

    void deleteSentenceById(Long id);
}
