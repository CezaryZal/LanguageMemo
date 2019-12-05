package com.cezaryzal.manager.service;

import com.cezaryzal.entity.Sentence;

import java.util.Optional;

public interface SentenceService {

    public Optional<Sentence> findById(Long index);

    public Iterable<Sentence> findAll();

    public Sentence addNewSentence (Sentence sentence);

    public Sentence updateSentence (Sentence sentence);

    public void deleteSentenceById(Long index);
}
