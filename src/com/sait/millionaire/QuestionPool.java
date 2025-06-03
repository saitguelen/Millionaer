package com.sait.millionaire;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;


public class QuestionPool {

    public static List<Question> getFragen() {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("fragen.json");

            Type listType = new TypeToken<List<Question>>() {}.getType();
            List<Question> fragenListe = gson.fromJson(reader, listType);

            reader.close();
            return fragenListe;

        } catch (Exception e) {
            System.out.println("❌ Fehler beim Laden der Fragen: " + e.getMessage());
            return List.of();
        }
    }
}