package com.cezaryzal.languageMemo.web.controllers.sentence;

import com.cezaryzal.languageMemo.application.model.SentenceModel;

import java.util.List;
import java.util.Optional;

public interface SentenceModelController {

    Optional<SentenceModel> findById(Long id);

    List<SentenceModel> findAllByReplayDateLessThanEqual(String localDate);

    List<SentenceModel> getListSentenceByLowestReplayLevel(int limitReplayLevel);

    Optional<SentenceModel> findRandomFirstByReplayDateLessThanEqual(String localDate);

    List<SentenceModel> findAll();

    SentenceModel addNewSentence (SentenceModel sentenceModel);

    SentenceModel updateSentence (SentenceModel sentenceModel);

    void deleteSentenceById (Long id);
}
