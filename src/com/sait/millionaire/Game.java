package com.sait.millionaire;

import com.sait.millionaire.data.FragenKategorie;
import com.sait.millionaire.gui.GameWindow;
import com.sait.millionaire.joker.Joker;
import com.sait.millionaire.joker.Joker5050;
import com.sait.millionaire.joker.PublikumsJoker;
import com.sait.millionaire.joker.TelefonJoker;

import com.sait.millionaire.data.ScoreEntry;
import com.sait.millionaire.data.ScoreManager;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        //javax.swing.SwingUtilities.invokeLater(() -> new GameWindow());
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte deinen Namen eingeben: ");
        String spielerName = scanner.nextLine();

        // Kategori seçimi
        System.out.println("Verfügbare Kategorien:");
        System.out.println("1. Grundlagen von Java");
        System.out.println("2. if_else_switch");
        System.out.println("3. Schleifen_for_while");
        System.out.println("4. Arrays und Strings");
        System.out.println("5. OOP Grundlagen");
        System.out.print("Bitte wähle eine Kategorie (1-5): ");
        int auswahl = scanner.nextInt();
        scanner.nextLine(); // Dummy-Zeile

        String dateipfad = switch (auswahl) {
            case 1 -> "fragen/grundlagen_java_fragen.json";
            case 2 -> "fragen/if_else_switch_fragen.json";
            case 3 -> "fragen/schleifen_fragen.json";
            case 4 -> "fragen/arrays_strings.json";
            case 5 -> "fragen/oop_grundlagen.json";
            default -> {
                System.out.println("Ungültige Auswahl. Spiel beendet.");
                yield null;
            }
        };

        if (dateipfad == null) return;

        FragenKategorie kategorie = QuestionLoader.ladeFragenAusJson(dateipfad);
        if (kategorie == null) {
            System.out.println("Fehler beim Laden der Fragen.");
            return;
        }

        List<Question> fragenListe = kategorie.getFragen();

        List<Joker> jokerListe = new ArrayList<>();
        jokerListe.add(new Joker5050());
        jokerListe.add(new TelefonJoker());
        jokerListe.add(new PublikumsJoker());

        boolean[] jokerVerfuegbar = {true, true, true}; //nur einmal benutzt

        // Preisgeld-Tabelle
        int[] geldPyramide = {
                100, 200, 300, 500, 1000,
                2000, 4000, 8000, 16000, 32000,
                64000, 125000, 250000, 500000, 1000000
        };

        int sicherheitsStufe = -1;

        // Beispiel-Fragen



        for (int i = 0; i < fragenListe.size(); i++) {
            Question frage = fragenListe.get(i);

            System.out.println("\nFrage " + (i + 1) + ":");
            System.out.println(frage.getFrage());

            // Schritt 1: Zeige zuerst alle Antwortmöglichkeiten
            for (String option : frage.getOptionen()) {
                System.out.println(option);
            }

            // Joker-Abfrage
            System.out.println("🃏 Verfügbare Joker:");
            for (int j = 0; j < jokerListe.size(); j++) {
                System.out.println((j + 1) + ": " + jokerListe.get(j).getName() + " [" + (jokerVerfuegbar[j] ? "ja" : "nein") + "]");
            }
            System.out.print("Willst du einen Joker benutzen? (ja/nein): ");
            String jokerWahl = scanner.nextLine().toLowerCase();

            if (jokerWahl.equals("ja") || jokerWahl.startsWith("j")) {
                System.out.print("Welchen Joker willst du benutzen? (1=50:50, 2=Telefon, 3=Publikum): ");
                int jokerNummer = Integer.parseInt(scanner.nextLine());

                if (jokerNummer >= 1 && jokerNummer <= 3 && jokerVerfuegbar[jokerNummer - 1]) {
                    jokerVerfuegbar[jokerNummer - 1] = false;
                    jokerListe.get(jokerNummer - 1).benutzeJoker(frage);
                } else {
                    System.out.println("❌ Ungültiger Joker oder bereits benutzt.");
                }
            } else {
                // Eğer joker kullanılmazsa seçenekleri tekrar göster
                for (String option : frage.getOptionen()) {
                    System.out.println(option);
                }
            }


            // Antwort einlesen
            System.out.print("Deine Antwort (A/B/C/D): ");
            String antwort = scanner.nextLine().toUpperCase();
            int antwortIndex = switch (antwort) {
                case "A" -> 0;
                case "B" -> 1;
                case "C" -> 2;
                case "D" -> 3;
                default -> -1;
            };

            if (antwortIndex < 0 || antwortIndex > 3) {
                System.out.println("⚠️ Ungültige Eingabe!");
                i--;
                continue;
            }

            if (antwortIndex == frage.getRichtigeAntwort()) {
                System.out.println("✅ Richtig! Du hast " + geldPyramide[i] + "€ gewonnen.");
                if (i == 4 || i == 9) {
                    sicherheitsStufe = i;
                }
            } else {
                System.out.println("❌ Falsch!");
                System.out.println("Die richtige Antwort war: " + frage.getOptionen()[frage.getRichtigeAntwort()]);
                int gewonnen = (sicherheitsStufe >= 0) ? geldPyramide[sicherheitsStufe] : 0;
                System.out.println("Du gehst mit " + gewonnen + "€ nach Hause.");
                ScoreManager.saveScore(new ScoreEntry(spielerName, gewonnen));
                break;
            }

            if (i < fragenListe.size() - 1) {
                System.out.print("🛑 Willst du das Spiel beenden? (ja/nein): ");
                String exit = scanner.nextLine().toLowerCase();
                if (exit.equals("ja") || exit.startsWith("j")) {
                    System.out.println("Du hast freiwillig aufgehört mit " + geldPyramide[i] + "€.");
                    break;
                }
            } else {
                System.out.println("\n🎉 Herzlichen Glückwunsch! Du bist Millionär! 💰");
                ScoreManager.saveScore(new ScoreEntry(spielerName, 1000000));
            }
        }
        System.out.println("\n🏆 Höchste Scores:");
        for (ScoreEntry entry : ScoreManager.getHighScores()) {
            System.out.println(entry);
        }
        scanner.close();
    }
}
