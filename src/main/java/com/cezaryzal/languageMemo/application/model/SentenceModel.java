package com.cezaryzal.languageMemo.application.model;


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
@NoArgsConstructor//to Spring data
@AllArgsConstructor
@Builder
@Entity(name = "sentence")
public class SentenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String languageEng;
    private String languagePol;
    private String hintFromNative;
    private String hintToNative;
    private int replayLevelFromNative;
    private int replayLevelToNative;
    private LocalDate replayDateFromNative;
    private LocalDate replayDateToNative;
    private LocalDate dateCreated;

}
