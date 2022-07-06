package com.cezaryzal.languageMemo.repository;

import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {

    List<Sentence> findByReplayDateLessThanEqual(LocalDate localDate);
    List<Sentence> findByCorrectAnswer(String correctAnswer);

    //SQL get query works: (input) '2021-12-20' < (replay_date) '2021-12-20'
    @Query(value = "SELECT * FROM sentence WHERE replay_date <= :localDate ORDER BY rand() LIMIT 1",
            nativeQuery = true)
    Optional<Sentence> findRandomByReplayDateLessThanEqual(@Param("localDate") LocalDate localDate);

    @Query(value = "SELECT * FROM sentence WHERE replay_level <= :limitReplayLevel", nativeQuery = true)
    List<Sentence> listSentenceByLowerReplayLevel(@PathVariable("limitReplayLevel") int limitReplayLevel);

    @Query(value = "SELECT count(*) FROM sentence WHERE replay_date <= :localDate",
            nativeQuery = true)
    Optional<Integer> getCounterReplayDateFromNativeLessThanEqual(@Param("localDate") LocalDate localDate);
}
