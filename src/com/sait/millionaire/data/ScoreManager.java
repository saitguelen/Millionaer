package com.sait.millionaire.data;

import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String SCORE_FILE = "highscores.txt";

    public static void saveScore(ScoreEntry entry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE, true))) {
            writer.write(entry.getName() + "," + entry.getScore());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern des Scores.");
        }
    }

    public static List<ScoreEntry> getHighScores() {
        List<ScoreEntry> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    scores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Scores.");
        }

        scores.sort((a, b) -> Integer.compare(b.getScore(), a.getScore())); // en yüksek başa
        return scores;
    }
}

