package com.cezaryzal.languageMemo.service.grammar;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "service.grammar-store")
@Getter
public class DynamicStoreGrammar {

    public String getTranslatedSentence_dynamic(String verb,
                                                String noun,
                                                String time,
                                                String type,
                                                String subject,
                                                boolean know,
                                                boolean withNo){
        StringBuilder sentenceTranslated = new StringBuilder();

        if (know){
            sentenceTranslated.append("I don't know if ");
        }

        if (time.equals("przeszły")){
            boolean isRegularVerb = true;

            if (verb.equals("sell")){
                isRegularVerb = false;
                verb = "sold";
            }

            if (type.equals("pytające") && !know){
                sentenceTranslated.append("Did ");
            }
            sentenceTranslated.append(translateSubject(subject));
            if (type.equals("pytające") && withNo && !know){
                sentenceTranslated.append(" not ");
            }
            if (type.equals("przeczące") || (know && withNo)){
                sentenceTranslated.append("didn't ");
            }
            sentenceTranslated.append(verb);
            if (isRegularVerb && !withNo && (type.equals("twierdzące")  && !know)){
                sentenceTranslated.append("d");
            }
        } else {
            boolean isFirstPerson = subject.equals("On") || subject.equals("Ona") || subject.equals("Ono");

            if (type.equals("pytające") && !know){
                sentenceTranslated.append(getHelperVerb(isFirstPerson))
                        .append(" ");
            }

            sentenceTranslated.append(translateSubject(subject));

            if (type.equals("pytające") && withNo && !know){
                sentenceTranslated.append("not ");
            }

            if (type.equals("przeczące") || (withNo && know)){
                sentenceTranslated.append(getHelperVerb(isFirstPerson))
                        .append("n't ")
                        .append(verb);
            } else if (type.equals("twierdzące") || know) {
                sentenceTranslated.append(verb);
                if (isFirstPerson){
                    sentenceTranslated.append("s");
                }
            } else {
                sentenceTranslated.append(verb);
            }
        }

        sentenceTranslated.append(" ").append(noun);

        if (type.equals("pytające") && !know){
            sentenceTranslated.append("?");
        }
        return sentenceTranslated.toString();
    }

    private String getHelperVerb(boolean isFirstPerson){
        return isFirstPerson ? "does" : "do";
    }

    private String translateSubject(String subject){
        switch (subject){
            case "Ja":
                return "I ";
            case "On":
                return "He ";
            case "Ona":
                return "She ";
            case "Ono":
                return "It ";
            case "My":
                return "We ";
            case "Oni":
                return "They ";
            default:
                return "You ";
        }
    }
}
