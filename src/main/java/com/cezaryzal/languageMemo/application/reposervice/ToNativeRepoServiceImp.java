package com.cezaryzal.languageMemo.application.reposervice;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import com.cezaryzal.languageMemo.repository.SentenceModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ToNativeRepoServiceImp extends CrudRepoService implements RepoService{

    @Autowired
    public ToNativeRepoServiceImp(SentenceModelRepo sentenceModelRepo) {
        super(sentenceModelRepo);
    }

    @Override
    public List<SentenceModel> findAllByReplayDateLessThanEqual(String localDate) {
        return sentenceModelRepo.
                findByReplayDateToNativeLessThanEqual(LocalDate.parse(localDate));
    }

    @Override
    public List<SentenceModel> getListSentenceByLowestReplayLevel(int limitReplayLevel) {
        return sentenceModelRepo.listSentenceToNativeByLowestReplayLevel(limitReplayLevel);
    }

    @Override
    public Optional<SentenceModel> findRandomFirstByReplayDateLessThanEqual(Object inputDate) {
        LocalDate date;
        if (inputDate.getClass().equals(String.class)){
            date = LocalDate.parse((String)inputDate);
        } else {
            date = (LocalDate) inputDate;
        }
        return sentenceModelRepo.findRandomFirstByReplayDateToNativeLessThanEqual(date);
    }

    @Override
    public Optional<Integer> getCounter(LocalDate localDate) {
        return sentenceModelRepo.getCounterReplayDateToNativeLessThanEqual(localDate);
    }
}
