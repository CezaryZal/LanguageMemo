package com.cezaryzal.manager.serviceRepo;

import com.cezaryzal.entity.Sentence;

import java.util.Optional;

public interface SentenceService {

    Optional<Sentence> findById(Long index);

    Iterable<Sentence> findAll();

    Sentence addNewSentence (Sentence sentence);

    Sentence updateSentence (Sentence sentence);

    void deleteSentenceById(Long index);
}
