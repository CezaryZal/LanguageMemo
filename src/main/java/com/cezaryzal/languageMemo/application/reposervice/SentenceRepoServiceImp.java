package com.cezaryzal.languageMemo.application.reposervice;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.repository.SentenceModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SentenceRepoServiceImp implements SentenceRepoService {

    private final SentenceModelRepo sentenceModelRepo;

    @Autowired
    public SentenceRepoServiceImp(SentenceModelRepo sentenceModelRepo) {
        this.sentenceModelRepo = sentenceModelRepo;
    }

    @Override
    public Optional<SentenceModel> findById(Long id) {
        return sentenceModelRepo.findById(id);
    }

    @Override
    public List<SentenceModel> findAllByReplayDateLessThanEqual(String localDate) {
        return sentenceModelRepo.findByReplayDateFromNativeLessThanEqual(LocalDate.parse(localDate));
    }

    @Override
    public List<SentenceModel> getListSentenceByLowestReplayLevel(int limitReplayLevel) {
        return sentenceModelRepo.listSentenceByLowestReplayLevel(limitReplayLevel);
    }

    @Override
    public Optional<SentenceModel> findRandomFirstByReplayDateLessThanEqual(Object localDate){
        LocalDate date;
        if (localDate.getClass().equals(String.class)){
            date = LocalDate.parse((String)localDate);
        } else {
            date = (LocalDate) localDate;
        }
        return sentenceModelRepo.findRandomFirstByReplayDateFromNativeLessThanEqual(date);
    }

    @Override
    public Optional<Integer> getCounter(LocalDate localDate) {
        return sentenceModelRepo.getCounterReplayDateFromNativeLessThanEqual(localDate);
    }

    @Override
    public List<SentenceModel> findAll() {
        return sentenceModelRepo.findAll();
    }

    @Override
    public SentenceModel addNewSentence(SentenceModel sentenceModel) {
        return sentenceModelRepo.save(sentenceModel);
    }

    @Override
    public SentenceModel updateSentence(SentenceModel sentenceModel) {
        return sentenceModelRepo.save(sentenceModel);
    }

    @Override
    public void deleteSentenceById(Long id) {
        sentenceModelRepo.deleteById(id);
    }


}
