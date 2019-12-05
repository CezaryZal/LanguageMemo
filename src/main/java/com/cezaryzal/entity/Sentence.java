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
@Entity(name = "sentence")
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "language_eng")
    private String languageEng;

    @Column(name = "language_pol")
    private String languagePol;

    @Column(name = "hint")
    private String hint;

    @Column(name = "replay_level")
    private int replayLevel;


    private LocalDate dateNextUpdate;


    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Sentence sentence = (Sentence) o;
//        return Objects.equals(english, sentence.english) &&
//                Objects.equals(polish, sentence.polish);
//    }

}
