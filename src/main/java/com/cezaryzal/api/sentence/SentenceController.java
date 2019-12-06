package com.cezaryzal.api.sentence;

import com.cezaryzal.entity.Sentence;

import java.util.Optional;

public interface SentenceController {

    Optional<Sentence> findById(Long index);

    Iterable<Sentence> findAll();

    Sentence addNewSentence (Sentence sentence);

    Sentence updateSentence (Sentence sentence);

    void deleteSentenceById (Long index);
}
