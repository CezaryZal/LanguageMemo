package com.cezaryzal.languageMemo.manager.service.repository;

import com.cezaryzal.languageMemo.application.entity.Sentence;
import com.cezaryzal.languageMemo.repository.SentenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SentenceServiceImp implements SentenceService{

    private SentenceRepo sentenceRepo;

    @Autowired
    public SentenceServiceImp(SentenceRepo sentenceRepo) {
        this.sentenceRepo = sentenceRepo;
    }

    @Override
    public Optional<Sentence> findById(Long id) {
        return sentenceRepo.findById(id);
    }

    @Override
    public List<Sentence> findAllByReplayDateLessThanEqual(String localDate) {
        return sentenceRepo.findByReplayDateLessThanEqual(LocalDate.parse(localDate));
    }

    @Override
    public List<Sentence> getListSentenceByLowestReplayLevel(int limitReplayLevel) {
        return sentenceRepo.listSentenceByLowestReplayLevel(limitReplayLevel);
    }

    @Override
    public Optional<Sentence> findRandomFirstByReplayDateLessThanEqual(String localDate){
        return sentenceRepo.findRandomFirstByReplayDateLessThanEqual(LocalDate.parse(localDate));
    }

    @Override
    public Optional<Integer> getCounter(LocalDate localDate) {
        return sentenceRepo.getCounterReplayDateLessThanEqual(localDate);
    }

    @Override
    public List<Sentence> findAll() {
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
    public void deleteSentenceById(Long id) {
        sentenceRepo.deleteById(id);
    }


}
