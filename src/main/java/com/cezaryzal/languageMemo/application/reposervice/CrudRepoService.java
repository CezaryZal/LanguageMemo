package com.cezaryzal.languageMemo.application.reposervice;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.repository.SentenceModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
abstract class CrudRepoService {

    final SentenceModelRepo sentenceModelRepo;
    
    @Autowired
    public CrudRepoService(SentenceModelRepo sentenceModelRepo) {
        this.sentenceModelRepo = sentenceModelRepo;
    }

    
    public Optional<SentenceModel> findById(Long id) {
        return sentenceModelRepo.findById(id);
    }

    public List<SentenceModel> findAll() {
        return sentenceModelRepo.findAll();
    }

    public SentenceModel addNewSentence(SentenceModel sentenceModel) {
        return sentenceModelRepo.save(sentenceModel);
    }

    public SentenceModel updateSentence(SentenceModel sentenceModel) {
        return sentenceModelRepo.save(sentenceModel);
    }

    public void deleteSentenceById(Long id) {
        sentenceModelRepo.deleteById(id);
    }
}
