package com.cezaryzal.tmpRuner;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.repository.SentencesRepository;
import com.cezaryzal.manager.Validator;
import com.cezaryzal.manager.SentenceChecker;

import java.util.*;

public class Runer {
    public static void main(String[] args) {

        SentenceChecker checker = new SentenceChecker();
        SentencesFromDB sentencesFromDB = new SentencesFromDB();

        int NumberOfCorrectAnswers = 0;
        int NumberOfIncorrectAnswers = 0;
        boolean isEnd = true;
        boolean isCorrectAnswers = false;

        while (isEnd) {
            Sentence currentSentence = sentencesFromDB.getRandomSentenceFromDB();
            if (isCorrectAnswers){
                currentSentence = sentencesFromDB.getRandomSentenceFromDB();
                isCorrectAnswers = false;
            }


            System.out.println("Przetłumacz słowo/zdanie:\n" + currentSentence.getLanguagePol());
            Scanner scanner = new Scanner(System.in);
            String inputWord = scanner.next();

            if (inputWord.equalsIgnoreCase("end") || inputWord.equalsIgnoreCase("koniec")) {
                break;
            }

            boolean score = checker.comparingSentences(inputWord, currentSentence.getLanguageEng());
            if (score) {
                NumberOfCorrectAnswers++;
                isCorrectAnswers = true;
            }
            else {
                NumberOfIncorrectAnswers++;
                Validator validator = new Validator(inputWord, currentSentence.getLanguageEng());
                validator.filterSentences();
                System.out.println(validator.getProgressSentence());
            }
            System.out.println(score);

        };

        System.out.println("liczba poprawnie wpisanych wyrazów: " + NumberOfCorrectAnswers);
        System.out.println("ilosc błednie wpisanych wyrazów: " + NumberOfIncorrectAnswers);

    }

    static class SentencesFromDB{
        public Sentence getRandomSentenceFromDB (){
            SentencesRepository  sentencesRepo = new SentencesRepository();
            Random random = new Random();
            return sentencesRepo.createRepo().get(random.nextInt(3));
        }
    }

}
