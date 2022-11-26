package com.cezaryzal.languageMemo.service.grammar;

import java.util.HashMap;
import java.util.Map;

public class StaticStoreGrammar {
    public Map<String, String> getTranslatedSentence_static(){
        Map<String, String> possibleSentence = new HashMap<>();

        possibleSentence.put("Nie wiem czy ja nie lubiłem jabłek", "I don't know If I didn't like apples");
        possibleSentence.put("Nie wiem czy Ty nie lubiłeś jabłek", "I don't know If You didn't like apples");
        possibleSentence.put("Nie wiem czy On nie lubił jabłek", "I don't know If He didn't like apples");
        possibleSentence.put("Nie wiem czy Ona nie lubiła jabłek", "I don't know If She didn't like apples");
        possibleSentence.put("Nie wiem czy Ono nie lubiło jabłek", "I don't know If It didn't like apples");
        possibleSentence.put("Nie wiem czy My nie lubiliśmy jabłek", "I don't know If We didn't like apples");
        possibleSentence.put("Nie wiem czy Wy nie lubiliście jabłek", "I don't know If You didn't like apples");
        possibleSentence.put("Nie wiem czy Oni nie lubili jabłek", "I don't know If They didn't like apples");

        possibleSentence.put("Nie wiem czy ja lubiłem jabłka", "I don't know If I liked apples");
        possibleSentence.put("Nie wiem czy Ty lubiłeś jabłka", "I don't know If You liked apples");
        possibleSentence.put("Nie wiem czy On lubił jabłka", "I don't know If He liked apples");
        possibleSentence.put("Nie wiem czy Ona lubiła jabłka", "I don't know If She liked apples");
        possibleSentence.put("Nie wiem czy Ono lubiło jabłka", "I don't know If It liked apples");
        possibleSentence.put("Nie wiem czy My lubiliśmy jabłka", "I don't know If We liked apples");
        possibleSentence.put("Nie wiem czy Wy lubiliście jabłka", "I don't know If You liked apples");
        possibleSentence.put("Nie wiem czy Oni lubili jabłka", "I don't know If They liked apples");

        possibleSentence.put("czy Ja nie lubiłem jabłek?", "Did I not like apples?");
        possibleSentence.put("czy Ty nie lubiłaś jabłek?", "Did You not like apples?");
        possibleSentence.put("czy On nie lubił jabłek?", "Did He not like apples?");
        possibleSentence.put("czy Ona nie lubiła jabłek?", "Did She not like apples?");
        possibleSentence.put("czy Ono nie lubiło jabłek?", "Did It not like apples?");
        possibleSentence.put("czy My nie lubiliśmy jabłek?", "Did We not like apples?");
        possibleSentence.put("czy Wy nie lubiliście jabłek?", "Did You not like apples?");
        possibleSentence.put("czy Oni nie lubili jabłek?", "Did They not like apples?");

        possibleSentence.put("czy Ja lubiłem jabłka?", "Did I like apples?");
        possibleSentence.put("czy Ty lubiłeś jabłka?", "Did You like apples?");
        possibleSentence.put("czy On lubił jabłka?", "Did He like apples?");
        possibleSentence.put("czy Ona lubiła jabłka?", "Did She like apples?");
        possibleSentence.put("czy Ono lubiło jabłka?", "Did It like apples?");
        possibleSentence.put("czy My lubiliśmy jabłka?", "Did We like apples?");
        possibleSentence.put("czy Wy lubiliście jabłka?", "Did You like apples?");
        possibleSentence.put("czy Oni lubili jabłka?", "Did They like apples?");

        possibleSentence.put("Ja nie lubiłem jabłek", "I didn't like apples");
        possibleSentence.put("Ty nie lubiłeś jabłek", "You didn't like apples");
        possibleSentence.put("On nie lubił jabłek", "He didn't like apples");
        possibleSentence.put("Ona nie lubiła jabłek", "She didn't like apples");
        possibleSentence.put("Ono nie lubiło jabłek", "It didn't like apples");
        possibleSentence.put("My nie lubiliśmy jabłek", "We didn't like apples");
        possibleSentence.put("Wy nie lubiliście jabłek", "You didn't like apples");
        possibleSentence.put("Oni nie lubili jabłek", "They didn't like apples");

        possibleSentence.put("Ja lubiłem jabłka", "I liked apples");
        possibleSentence.put("Ty lubiłeś jabłka", "You liked apples");
        possibleSentence.put("On lubił jabłka", "He liked apples");
        possibleSentence.put("Ona lubiła jabłka", "She liked apples");
        possibleSentence.put("Ono lubiło jabłka", "It liked apples");
        possibleSentence.put("My lubiliśmy jabłka", "We liked apples");
        possibleSentence.put("Wy lubiliście jabłka", "You liked apples");
        possibleSentence.put("Oni lubili jabłka", "They liked apples");



        possibleSentence.put("Ja chcę sprzedać jabłka", "I want to sell apples");
        possibleSentence.put("Ty chcesz sprzedać jabłka", "You want to sell apples");
        possibleSentence.put("On chce sprzedać jabłka", "He wants to sell apples");
        possibleSentence.put("Ona chce sprzedać jabłka", "She wants to sell apples");
        possibleSentence.put("Ono chce sprzedać jabłka", "It wants to sell apples");
        possibleSentence.put("My chcemy sprzedać jabłka", "We want to sell apples");
        possibleSentence.put("Wy chcemy sprzedać jabłka", "You want to sell apples");
        possibleSentence.put("Oni chcą sprzedać jabłka", "They want to sell apples");


        //use 'type', 'subject', 'withNo' and know
        possibleSentence.put("Nie wiem czy ja nie lubię jabłek", "I don't know If I don't like apples");
        possibleSentence.put("Nie wiem czy Ty nie lubisz jabłek", "I don't know If You don't like apples");
        possibleSentence.put("Nie wiem czy On nie lubi jabłek", "I don't know If He doesn't like apples");
        possibleSentence.put("Nie wiem czy Ona nie lubi jabłek", "I don't know If She doesn't like apples");
        possibleSentence.put("Nie wiem czy Ono nie lubi jabłek", "I don't know If It doesn't like apples");
        possibleSentence.put("Nie wiem czy My nie lubimy jabłek", "I don't know If We don't like apples");
        possibleSentence.put("Nie wiem czy Wy nie lubicie jabłek", "I don't know If You don't like apples");
        possibleSentence.put("Nie wiem czy Oni nie lubią jabłek", "I don't know If They don't like apples");

        //use 'type', 'subject' and know
        possibleSentence.put("Nie wiem czy ja lubię jabłek", "I don't know If I like apples");
        possibleSentence.put("Nie wiem czy Ty lubisz jabłek", "I don't know If You like apples");
        possibleSentence.put("Nie wiem czy On lubi jabłek", "I don't know If He likes apples");
        possibleSentence.put("Nie wiem czy Ona lubi jabłek", "I don't know If She likes apples");
        possibleSentence.put("Nie wiem czy Ono lubi jabłek", "I don't know If It likes apples");
        possibleSentence.put("Nie wiem czy My lubimy jabłek", "I don't know If We like apples");
        possibleSentence.put("Nie wiem czy Wy lubicie jabłek", "I don't know If You like apples");
        possibleSentence.put("Nie wiem czy Oni lubią jabłek", "I don't know If They like apples");

        //use 'type', 'subject' and 'withNo'
        possibleSentence.put("czy Ja nie lubię jabłek?", "Do I not like apples?");
        possibleSentence.put("czy Ty nie lubisz jabłek?", "Do You not like apples?");
        possibleSentence.put("czy On nie lubi jabłek?", "Does He not like apples?");
        possibleSentence.put("czy Ona nie lubi jabłek?", "Does She not like apples?");
        possibleSentence.put("czy Ono nie lubi jabłek?", "Does It not like apples?");
        possibleSentence.put("czy My nie lubimy jabłek?", "Do We not like apples?");
        possibleSentence.put("czy Wy nie lubicie jabłek?", "Do You not like apples?");
        possibleSentence.put("czy Oni nie lubią jabłek?", "Do They not like apples?");


        //use only 'type' and 'subject'
        possibleSentence.put("czy Ja lubię jabłka?", "Do I like apples?");
        possibleSentence.put("czy Ty lubisz jabłka?", "Do You like apples?");
        possibleSentence.put("czy On lubi jabłka?", "Dose He like apples?");
        possibleSentence.put("czy Ona lubi jabłka?", "Dose She like apples?");
        possibleSentence.put("czy Ono lubi jabłka?", "Does It like apples?");
        possibleSentence.put("czy My lubimy jabłka?", "Do We like apples?");
        possibleSentence.put("czy Wy lubicie jabłka?", "Do You like apples?");
        possibleSentence.put("czy Oni lubią jabłka?", "Do They like apples?");

        possibleSentence.put("Ja nie lubię jabłek", "I don't like apples");
        possibleSentence.put("Ty nie lubisz jabłek", "You don't like apples");
        possibleSentence.put("On nie lubi jabłek", "He doesn't like apples");
        possibleSentence.put("Ona nie lubi jabłek", "She doesn't like apples");
        possibleSentence.put("Ono nie lubi jabłek", "It doesn't like apples");
        possibleSentence.put("My nie lubimy jabłek", "We don't like apples");
        possibleSentence.put("Wy nie lubicie jabłek", "You don't like apples");
        possibleSentence.put("Oni nie lubią jabłek", "They don't like apples");

        possibleSentence.put("Ja lubię jabłka", "I like apples");
        possibleSentence.put("Ty lubisz jabłka", "You like apples");
        possibleSentence.put("On lubi jabłka", "He likes apples");
        possibleSentence.put("Ona lubi jabłka", "She likes apples");
        possibleSentence.put("Ono lubi jabłka", "It likes apples");
        possibleSentence.put("My lubimy jabłka", "We like apples");
        possibleSentence.put("Wy lubicie jabłka", "You like apples");
        possibleSentence.put("Oni lubią jabłka", "They like apples");

//        possibleSentence.put("Ja robi to", "I do it");
//        possibleSentence.put("Ty robi to", "You do it");
//        possibleSentence.put("On robi to", "He does it");
//        possibleSentence.put("Ona robi to", "She does it");
//        possibleSentence.put("Ono robi to", "It does it");
//        possibleSentence.put("My robi to", "We do it");
//        possibleSentence.put("Wy robi to", "You do it");
//        possibleSentence.put("Oni robi to", "They do it");
        return possibleSentence;
    }
}
