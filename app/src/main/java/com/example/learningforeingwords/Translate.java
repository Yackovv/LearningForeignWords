package com.example.learningforeingwords;

import java.util.HashMap;
import java.util.Map;

public class Translate {
    private String englishWord;
    private String russianWord;
    private String otherWord;


    public Map getWordsForFireStore(){
        Map<String, Map<String, String>> words = new HashMap<>();
        Map<String, String> miniMap = new HashMap<>();
        miniMap.put(russianWord, otherWord);
        words.put(englishWord, miniMap);
        return words;
    }

    public void addWords(String englishWord, String russianWord, String otherWord){
        this.englishWord = englishWord;
        this.russianWord = russianWord;
        this.otherWord = otherWord;
    }
}
