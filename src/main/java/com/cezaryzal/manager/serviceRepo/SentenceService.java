package com.cezaryzal.manager.serviceRepo;

import com.cezaryzal.entity.Sentence;

import java.time.LocalDate;
import java.util.Optional;

public interface SentenceService {

    Optional<Sentence> findById(Long index);

    Iterable<Sentence> findAllByReplayDateLessThanEqual(String localDate);

    Optional<Sentence> findRandomFirstByReplayDateLessThanEqual(String localDate);

    Iterable<Sentence> findAll();

    Sentence addNewSentence (Sentence sentence);

    Sentence updateSentence (Sentence sentence);

    void deleteSentenceById(Long index);
}
