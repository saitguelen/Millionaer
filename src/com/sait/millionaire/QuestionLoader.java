package com.sait.millionaire;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sait.millionaire.data.FragenKategorie;

import java.io.FileReader;
import java.io.IOException;

public class QuestionLoader {

    public static FragenKategorie ladeFragenAusJson(String dateipfad) {
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(dateipfad)) {
            return gson.fromJson(reader, FragenKategorie.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

