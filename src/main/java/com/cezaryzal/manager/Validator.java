package com.cezaryzal.manager;

public class Validator {

    public String parseSentence (String inputSentence, String correctSentence) {

        StringBuilder progress = new StringBuilder();

        for (int i = 0; i < correctSentence.length(); i++) {
            char incorrect = '_';

            try {
                if (inputSentence.toLowerCase().charAt(i)==correctSentence.toLowerCase().charAt(i)){
                    progress.append(correctSentence.charAt(i));
                }else
                    progress.append(incorrect);
            } catch (StringIndexOutOfBoundsException e){
                progress.append(incorrect);
            }
        }
        return String.valueOf(progress);
    }

}
