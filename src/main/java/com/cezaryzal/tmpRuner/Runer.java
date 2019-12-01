package com.cezaryzal.tmpRuner;

import com.cezaryzal.manager.WordsChecker;

import java.util.Scanner;

public class Runer {
    public static void main(String[] args) {

        WordsChecker checker = new WordsChecker();

        String word;
        int correctAnswers = 0;
        int incorrectAnswers = 0;
        boolean isEnd = true;

        while (isEnd) {
            System.out.println("wpisz slowo:");


            Scanner scanner = new Scanner(System.in);
            word = scanner.next();

            if (word.equalsIgnoreCase("end") || word.equalsIgnoreCase("koniec")) {
                break;
            }
            boolean score = checker.checking(word);
            if(score)
                correctAnswers++;
            else
                incorrectAnswers++;
            System.out.println(score);

        };

        System.out.println("liczba poprawnie wpisanych wyrazów: " + correctAnswers);
        System.out.println("ilosc błednie wpisanych wyrazów: " + incorrectAnswers);

    }
}
