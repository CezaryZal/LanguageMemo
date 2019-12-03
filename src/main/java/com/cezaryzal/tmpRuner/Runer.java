package com.cezaryzal.tmpRuner;

import com.cezaryzal.manager.Validator;
import com.cezaryzal.manager.SentenceChecker;

import java.util.Scanner;

public class Runer {
    public static void main(String[] args) {

        SentenceChecker checker = new SentenceChecker();
        Validator validator = new Validator();

        String correctSentence = "home";
        String word;
        int correctAnswers = 0;
        int incorrectAnswers = 0;
        boolean isEnd = true;
        String answer;


        while (isEnd) {
            System.out.println("wpisz slowo:");

            Scanner scanner = new Scanner(System.in);

            word = scanner.next();

            if (word.equalsIgnoreCase("end") || word.equalsIgnoreCase("koniec")) {
                break;
            }

            boolean score = checker.checking(word, correctSentence);
            if (score)
                correctAnswers++;
            else {
                incorrectAnswers++;
                answer = validator.parseSentence(word, correctSentence);
                System.out.println(answer);
            }
            System.out.println(score);

        };

        System.out.println("liczba poprawnie wpisanych wyrazów: " + correctAnswers);
        System.out.println("ilosc błednie wpisanych wyrazów: " + incorrectAnswers);

    }
}
