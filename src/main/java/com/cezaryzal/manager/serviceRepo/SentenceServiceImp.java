package com.cezaryzal.manager.serviceRepo;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.repository.SentenceRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SentenceServiceImp implements SentenceService{

    private SentenceRepo sentenceRepo;

    public SentenceServiceImp(SentenceRepo sentenceRepo) {
        this.sentenceRepo = sentenceRepo;
    }

    @Override
    public Optional<Sentence> findById(Long index) {
        return sentenceRepo.findById(index);
    }

    @Override
    public Iterable<Sentence> findAll() {
        return sentenceRepo.findAll();
    }

    @Override
    public Sentence addNewSentence(Sentence sentence) {
        return sentenceRepo.save(sentence);
    }

    @Override
    public Sentence updateSentence(Sentence sentence) {
        return sentenceRepo.save(sentence);
    }

    @Override
    public void deleteSentenceById(Long index) {
        sentenceRepo.deleteById(index);

    }
}
