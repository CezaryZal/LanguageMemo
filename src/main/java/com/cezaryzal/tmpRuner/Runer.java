package com.cezaryzal.tmpRuner;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.repository.SentencesRepository;
import com.cezaryzal.manager.Validator;
import com.cezaryzal.manager.SentenceChecker;

import java.util.*;

public class Runer {
    public static void main(String[] args) {

        SentenceChecker checker = new SentenceChecker();
        Validator validator = new Validator();
        SentencesRepository  sentencesRepo = new SentencesRepository();
        Random random = new Random();

        int correctAnswers = 0;
        int incorrectAnswers = 0;
        boolean isEnd = true;
        String answer;

        while (isEnd) {
            Sentence currentSentence = sentencesRepo.createRepo().get(random.nextInt(3));

            System.out.println("Przetłumacz słowo/zdanie:\n" + currentSentence.getPolish());
            Scanner scanner = new Scanner(System.in);
            String word = scanner.next();

            if (word.equalsIgnoreCase("end") || word.equalsIgnoreCase("koniec")) {
                break;
            }

            boolean score = checker.checking(word, currentSentence.getEnglish());
            if (score)
                correctAnswers++;
            else {
                incorrectAnswers++;
                answer = validator.parseSentence(word, currentSentence.getEnglish());
                System.out.println(answer);
            }
            System.out.println(score);

        };

        System.out.println("liczba poprawnie wpisanych wyrazów: " + correctAnswers);
        System.out.println("ilosc błednie wpisanych wyrazów: " + incorrectAnswers);

    }
}
