package com.cezaryzal.repository;

import com.cezaryzal.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;


public interface SentenceRepo extends JpaRepository<Sentence, Long> {

    Iterable<Sentence> findByReplayDateLessThanEqual(LocalDate localDate);

@Query(value = "select * from sentence where replay_date <= :localDate order by rand() limit 1",
        nativeQuery = true)
    Optional<Sentence> findRandomFirstByReplayDateLessThanEqual(@Param("localDate") LocalDate localDate);


}
