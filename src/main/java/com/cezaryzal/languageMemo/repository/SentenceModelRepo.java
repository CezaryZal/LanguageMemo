package com.cezaryzal.languageMemo.repository;

import com.cezaryzal.languageMemo.model.SentenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface SentenceModelRepo extends JpaRepository<SentenceModel, Long> {

    List<SentenceModel> findByReplayDateFromNativeLessThanEqual(LocalDate localDate);
    List<SentenceModel> findByReplayDateToNativeLessThanEqual(LocalDate localDate);

    @Query(value = "select * from sentencemodel where replay_level_from_native <= :limitReplayLevel", nativeQuery = true)
    List<SentenceModel> listSentenceFromNativeByLowestReplayLevel(@Param("limitReplayLevel") int limitReplayLevel);

    @Query(value = "select * from sentencemodel where replay_level_to_native <= :limitReplayLevel", nativeQuery = true)
    List<SentenceModel> listSentenceToNativeByLowestReplayLevel(@Param("limitReplayLevel") int limitReplayLevel);

    @Query(value = "select * from sentencemodel where replay_date_from_native <= :localDate order by rand() limit 1",
            nativeQuery = true)
    Optional<SentenceModel> findRandomFirstByReplayDateFromNativeLessThanEqual(@Param("localDate") LocalDate localDate);

    @Query(value = "select * from sentencemodel where replay_date_to_native <= :localDate order by rand() limit 1",
            nativeQuery = true)
    Optional<SentenceModel> findRandomFirstByReplayDateToNativeLessThanEqual(@Param("localDate") LocalDate localDate);

    @Query(value = "select count(*) from sentencemodel where replay_date_from_native <= :localDate",
            nativeQuery = true)
    Optional<Integer> getCounterReplayDateFromNativeLessThanEqual(@Param("localDate") LocalDate localDate);

    @Query(value = "select count(*) from sentencemodel where replay_date_to_native <= :localDate",
            nativeQuery = true)
    Optional<Integer> getCounterReplayDateToNativeLessThanEqual(@Param("localDate") LocalDate localDate);

}
