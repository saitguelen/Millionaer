package com.sait.millionaire.data;

public class ScoreEntry {
    private String name;
    private int score;

    public ScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String getName(){
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ScoreEntry{" +
                "name='" + name + '\'' +
                ", score=" + score + " €"+
                '}';
    }
}
