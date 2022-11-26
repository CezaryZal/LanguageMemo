package com.cezaryzal.languageMemo.service.grammar;

import java.util.*;

public class GrammarService {
    Random random = new Random();
//    https://www.speak-up.pl/szyk-zdania-angielski

    private final List<String> time = List.of(new String[]{"przeszły", "teraźniejszy"});
    private final List<String> types = List.of(new String[]{"pytające", "przeczące", "twierdzące"});
    private final List<String> subjects = List.of(new String[]{" Ja", " Ty", " On", " Ona", " Ono", " My", " Wy", " Oni"});

    private final String verb = "lubi";
    private final String verb1 = "sprzeda";
    private final String object = " jabł";


    public String createSentenceInPresentTime(){
        StringBuilder sentence;

        String time = getRandomTime();
        String type = getRandomType();
        String subject =  getRandomSubject();
        boolean know = getRandomBoolean();
        boolean withNo = getRandomBoolean();

        sentence = createSentenceOnPastTime(verb, time, type, subject, know, withNo);

        DynamicStoreGrammar dynamicStoreGrammar = new DynamicStoreGrammar();

        String sentenceToReturn = sentence + "\n\n\n\n\n";
        sentenceToReturn += dynamicStoreGrammar.getTranslatedSentence_dynamic(
                "like", " apples", time, type, subject.trim(), know, withNo);
        sentenceToReturn += "\n" + time + "; " + type + "; " + subject + "; know:" + know + "; withNo:" + withNo;
        return sentenceToReturn;
    }

    private StringBuilder createSentenceOnPastTime(String verb,
                                                   String time,
                                                   String type,
                                                   String subject,
                                                   boolean know,
                                                   boolean withNo) {
        StringBuilder sentence = new StringBuilder();

        if (know){
            sentence.append("Nie wiem czy");
        }
        if (type.equals("pytające")  && !know) {
            sentence.append("czy ");
        }

        sentence.append(subject);

        if (type.equals("przeczące") || (type.equals("pytające") && withNo) || (withNo && know)){
            sentence.append(" nie");
        }
        if (time.equals("przeszły")){
            sentence.append(" ").append(convertVerbInPastTimeBySubject(verb , String.valueOf(subject)));
        } else {
            sentence.append(" ").append(convertVerbInPresentTimeBySubject(verb , String.valueOf(subject)));
        }

        if (type.equals("przeczące") || withNo){
            sentence.append(" ").append(object).append("ek");
        } else {
            sentence.append(" ").append(object).append("ka");
        }

        if (type.equals("pytające") && !know){
            sentence.append("?");
        }
        return sentence;
    }

    private Map<String, String> getPossibleSentence(){
        Map<String, String> possibleSentence = new HashMap<>();

        possibleSentence.put("Skąd ja mma wiedzieć czy ja nie lubiłem jabłek", "How am I supposed to know If I didn't like apples");
        possibleSentence.put("Skąd ja mma wiedzieć czy Ty nie lubiłeś jabłek", "How am I supposed to know If You didn't like apples");
        possibleSentence.put("Skąd ja mma wiedzieć czy On nie lubił jabłek", "How am I supposed to know If He didn't like apples");
        possibleSentence.put("Skąd ja mma wiedzieć czy Ona nie lubiła jabłek", "How am I supposed to know If She didn't like apples");
        possibleSentence.put("Skąd ja mma wiedzieć czy Ono nie lubiło jabłek", "How am I supposed to know If It didn't like apples");
        possibleSentence.put("Skąd ja mma wiedzieć czy My nie lubiliśmy jabłek", "How am I supposed to know If We didn't like apples");
        possibleSentence.put("Skąd ja mma wiedzieć czy Wy nie lubiliście jabłek", "How am I supposed to know If You didn't like apples");
        possibleSentence.put("Skąd ja mma wiedzieć czy Oni nie lubili jabłek", "How am I supposed to know If They didn't like apples");

        return possibleSentence;
    }

    public String getRandomTime(){
        return time.get(random.nextInt(time.size()));
//        return "przeszły";
    }
    private String getRandomSubject(){
        return subjects.get(random.nextInt(subjects.size()));
    }
    private String getRandomType(){
        return types.get(random.nextInt(types.size()));
    }
    private boolean getRandomBoolean(){
        return random.nextBoolean();
    }

    private String convertVerbInPastTimeBySubject(String verb, String subject){
        switch (subject.trim()) {
            case "Ja":
                return verb.concat("łem");
            case "Ty":
                return verb.concat("łeś");
            case "On":
                return verb.concat("ł");
            case "Ona":
                return verb.concat("ła");
            case "Ono":
                return verb.concat("ło");
            case "My":
                return verb.concat("liśmy");
            case "Wy":
                return verb.concat("liście");
            case "Oni":
                return verb.concat("li");
            default: return verb;
        }
    }

    private String convertVerbInPresentTimeBySubject(String verb, String subject){
        switch (subject.trim()) {
            case "Ja":
                if (verb.charAt(verb.length() - 1) == 'a'){
                    verb = verb.concat("j");
                }
                return verb.concat("ę");
            case "Ty":
                return verb.concat("sz");
            case "My":
                return verb.concat("my");
            case "Wy":
                return verb.concat("cie");
            case "Oni":
                if (verb.charAt(verb.length() - 1) == 'a'){
                    verb = verb.concat("j");
                }
                return verb.concat("ą");
            default:
                if (verb.charAt(verb.length() - 1) == 'a'){
                    verb = verb.concat("je");
                } else if (verb.charAt(verb.length() - 1) == 'c') {
                    verb = verb.concat("e");
                }
                return verb;
        }
    }

}
