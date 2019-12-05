package com.cezaryzal.repository;

import com.cezaryzal.entity.Sentence;
import org.springframework.data.repository.CrudRepository;

public interface SentenceRepo extends CrudRepository<Sentence, Long> {
}
