package com.cezaryzal.entity.repository;

import com.cezaryzal.entity.Sentence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentencesRepository {

    public List<Sentence> createRepo (){
        Sentence correctSentence1 = Sentence.builder()
                .english("home")
                .polish("dom")
                .hint("miejsce, nie budynek")
                .position(0)
                .dateUpdate(LocalDate.of(2019,12,5))
                .build();
        Sentence correctSentence2 = Sentence.builder()
                .english("somebody")
                .polish("ktoś")
                .hint("osoba z wielu")
                .position(1)
                .dateUpdate(LocalDate.of(2019,12,6))
                .build();
        Sentence correctSentence3 = Sentence.builder()
                .english("attic")
                .polish("poddasze")
                .hint("osoba z wielu")
                .position(1)
                .dateUpdate(LocalDate.of(2019,12, 5))
                .build();

        List<Sentence> sentenceList = new ArrayList<>(Arrays.asList(correctSentence1, correctSentence2, correctSentence3));
        return sentenceList;
    }
}
