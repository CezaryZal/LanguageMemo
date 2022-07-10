package com.cezaryzal.languageMemo.repository.service;

import com.cezaryzal.languageMemo.repository.SentenceJpaRepository;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
abstract class CrudSentenceRepository {
    final SentenceJpaRepository sentenceJpaRepository;

    @Autowired
    public CrudSentenceRepository(SentenceJpaRepository sentenceJpaRepository) {
        this.sentenceJpaRepository = sentenceJpaRepository;
    }

    public Optional<Sentence> findById(Long id){
        return sentenceJpaRepository.findById(id);
    }

    public List<Sentence> findAll(){
        return sentenceJpaRepository.findAll();
    }

    public Sentence addNewSentence(Sentence sentence){
        return sentenceJpaRepository.save(sentence);
    }

    public Sentence updateSentence(Sentence sentence){
        return sentenceJpaRepository.save(sentence);
    }

    public void deleteSentenceById(Long id){
        sentenceJpaRepository.deleteById(id);
    }

}
