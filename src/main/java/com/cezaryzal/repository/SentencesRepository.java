package com.cezaryzal.repository;

import com.cezaryzal.entity.Sentence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentencesRepository {

    public List<Sentence> createRepo (){
        Sentence sentence1 = Sentence.builder()
                .languageEng("home")
                .languagePol("dom")
                .hint("miejsce, nie budynek")
                .replayLevel(0)
                .dateNextUpdate(LocalDate.of(2019,12,5))
                .build();
        Sentence sentence2 = Sentence.builder()
                .languageEng("somebody")
                .languagePol("ktoś")
                .hint("osoba z wielu")
                .replayLevel(1)
                .dateNextUpdate(LocalDate.of(2019,12,6))
                .build();
        Sentence sentence3 = Sentence.builder()
                .languageEng("attic")
                .languagePol("poddasze")
                .hint("osoba z wielu")
                .replayLevel(1)
                .dateNextUpdate(LocalDate.of(2019,12, 5))
                .build();

        return new ArrayList<>(Arrays.asList(sentence1, sentence2, sentence3));
    }
}
