package com.cezaryzal.languageMemo.service.random;

import com.cezaryzal.languageMemo.repository.entity.Sentence;

public interface RandomSentence {

    Sentence getRandomSentenceByTodayDate();
}
