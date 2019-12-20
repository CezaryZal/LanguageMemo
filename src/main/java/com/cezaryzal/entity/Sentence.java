package com.cezaryzal.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor//to Spring data
@AllArgsConstructor
@Builder
@Entity
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String languageEng;
    private String languagePol;
    private String hint;
    private int replayLevel;
    private LocalDate replayDate;

}
