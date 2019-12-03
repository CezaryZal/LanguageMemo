package com.cezaryzal.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor//to Spring data
@AllArgsConstructor
@Builder
public class Sentence {

    private String english;
    private String polish;
    private String hint;
    private int position;
    private LocalDate dateUpdate;


    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Sentence sentence = (Sentence) o;
//        return Objects.equals(english, sentence.english) &&
//                Objects.equals(polish, sentence.polish);
//    }

}
