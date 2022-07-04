package com.cezaryzal.languageMemo.repository.service;

import com.cezaryzal.languageMemo.model.SentenceModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RepoService {

    List<SentenceModel> findAllByReplayDateLessThanEqual(String localDate);

    List<SentenceModel> getListSentenceByLowestReplayLevel(int limitReplayLevel);

    Optional<SentenceModel> findRandomFirstByReplayDateLessThanEqual(Object inputDate);


    Optional<SentenceModel> findById(Long id);

    List<SentenceModel> findAll();

    Optional<Integer> getCounter(LocalDate localDate);

    SentenceModel addNewSentence (SentenceModel sentenceModel);

    SentenceModel updateSentence (SentenceModel sentenceModel);

    void deleteSentenceById(Long id);
}
