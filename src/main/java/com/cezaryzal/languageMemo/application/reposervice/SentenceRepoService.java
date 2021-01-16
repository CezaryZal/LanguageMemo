package com.cezaryzal.languageMemo.application.reposervice;

import com.cezaryzal.languageMemo.application.model.SentenceModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SentenceRepoService {

    Optional<SentenceModel> findById(Long id);

    List<SentenceModel> findAllByReplayDateLessThanEqual(String localDate);

    List<SentenceModel> getListSentenceByLowestReplayLevel(int limitReplayLevel);

    Optional<SentenceModel> findRandomFirstByReplayDateLessThanEqual(Object localDate);

    List<SentenceModel> findAll();

    Optional<Integer> getCounter(LocalDate localDate);

    SentenceModel addNewSentence (SentenceModel sentenceModel);

    SentenceModel updateSentence (SentenceModel sentenceModel);

    void deleteSentenceById(Long id);
}
