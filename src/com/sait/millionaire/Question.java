package com.sait.millionaire;

public class Question {
    private String frage; // Soru metni
    private String[] optionen; // Seçenekler
    private int richtigeAntwort; // Doğru şık
    private String schwierigkeit; // Zorluk seviyesi

    public Question() {}

    public Question(String frage, String[] optionen, int richtigeAntwort, String schwierigkeit) {
        this.frage = frage;
        this.optionen = optionen;
        this.richtigeAntwort = richtigeAntwort;
        this.schwierigkeit = schwierigkeit;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String[] getOptionen() {
        return optionen;
    }

    public void setOptionen(String[] optionen) {
        this.optionen = optionen;
    }

    public int getRichtigeAntwort() {
        return richtigeAntwort;
    }

    public void setRichtigeAntwort(int richtigeAntwort) {
        this.richtigeAntwort = richtigeAntwort;
    }

    public String getSchwierigkeit() {
        return schwierigkeit;
    }

    public void setSchwierigkeit(String schwierigkeit) {
        this.schwierigkeit = schwierigkeit;
    }
}
