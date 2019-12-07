package com.cezaryzal.tmpRuner;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.repository.SentencesRepository;
import com.cezaryzal.manager.phrase.PhraseValidator;
import com.cezaryzal.manager.phrase.PhraseComparator;

import java.util.*;

public class Runer {
    public static void main(String[] args) {

        PhraseComparator checker = new PhraseComparator();
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

//            boolean score = checker.comparingPhrases(inputWord, currentSentence.getLanguageEng());
            boolean score = true;
            if (score) {
                NumberOfCorrectAnswers++;
                isCorrectAnswers = true;
            }
            else {
                NumberOfIncorrectAnswers++;
                PhraseValidator phraseValidator = new PhraseValidator(inputWord, currentSentence.getLanguageEng());
                phraseValidator.filterSentences();
                System.out.println(phraseValidator.getProgressSentence());
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
