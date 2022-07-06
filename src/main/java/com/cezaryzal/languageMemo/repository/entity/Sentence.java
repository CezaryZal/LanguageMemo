package com.cezaryzal.languageMemo.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "sentence")
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clues;
    private String correctAnswer;
    private String hint;
    private String exampleOfUse;
    private String partOfSpeechClues;
    private String similarWord;
    private int replayLevel;
    private LocalDate replayDate;
    private LocalDate dateCreated;
}
