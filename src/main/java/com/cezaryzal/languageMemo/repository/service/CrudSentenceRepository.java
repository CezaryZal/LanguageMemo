package com.cezaryzal.languageMemo.repository.service;

import com.cezaryzal.languageMemo.repository.SentenceRepository;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
abstract class CrudSentenceRepository {
    final SentenceRepository sentenceRepository;

    @Autowired
    public CrudSentenceRepository(SentenceRepository sentenceRepository) {
        this.sentenceRepository = sentenceRepository;
    }

    public Optional<Sentence> findById(Long id){
        return sentenceRepository.findById(id);
    }

    public List<Sentence> findAll(){
        return sentenceRepository.findAll();
    }

    public Sentence addNewSentence(Sentence sentence){
        return sentenceRepository.save(sentence);
    }

    public Sentence updateSentence(Sentence sentence){
        return sentenceRepository.save(sentence);
    }

    public void deleteSentenceById(Long id){
        sentenceRepository.deleteById(id);
    }

}
