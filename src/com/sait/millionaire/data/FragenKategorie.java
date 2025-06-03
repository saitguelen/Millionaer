package com.sait.millionaire.data;

import com.sait.millionaire.Question;

import java.util.List;

public class FragenKategorie {
    private String kategorie;
    private List<Question> fragen;

    public FragenKategorie() {}

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public List<Question> getFragen() {
        return fragen;
    }

    public void setFragen(List<Question> fragen) {
        this.fragen = fragen;
    }
}