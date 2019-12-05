package com.cezaryzal.tmpRuner;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.repository.SentencesRepository;
import com.cezaryzal.manager.Validator;
import com.cezaryzal.manager.SentenceChecker;

import java.util.*;

public class Runer {
    public static void main(String[] args) {

        SentenceChecker checker = new SentenceChecker();
        SentencesRepository  sentencesRepo = new SentencesRepository();
        Random random = new Random();

        int correctAnswers = 0;
        int incorrectAnswers = 0;
        boolean isEnd = true;
        String answer;

        while (isEnd) {
            Sentence currentSentence = sentencesRepo.createRepo().get(random.nextInt(3));

            System.out.println("Przetłumacz słowo/zdanie:\n" + currentSentence.getLanguagePol());
            Scanner scanner = new Scanner(System.in);
            String inputWord = scanner.next();

            if (inputWord.equalsIgnoreCase("end") || inputWord.equalsIgnoreCase("koniec")) {
                break;
            }

            boolean score = checker.comparingSentences(inputWord, currentSentence.getLanguageEng());
            if (score)
                correctAnswers++;
            else {
                incorrectAnswers++;
                Validator validator = new Validator(inputWord, currentSentence.getLanguageEng());
                validator.filterSentences();
                System.out.println(validator.getProgressSentence());
            }
            System.out.println(score);

        };

        System.out.println("liczba poprawnie wpisanych wyrazów: " + correctAnswers);
        System.out.println("ilosc błednie wpisanych wyrazów: " + incorrectAnswers);

    }
}
