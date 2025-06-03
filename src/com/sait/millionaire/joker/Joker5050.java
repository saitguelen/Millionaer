package com.sait.millionaire.joker;

import com.sait.millionaire.Question;

import java.util.ArrayList;
import java.util.Collections;

public class Joker5050 implements Joker {

    @Override
    public void benutzeJoker(Question frage) {
        int richtige = frage.getRichtigeAntwort();

        ArrayList<Integer> falsch = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i != richtige) falsch.add(i);
        }

        Collections.shuffle(falsch);//Die Möglichkeit, eine der falschen Antworten zufällig auszuwählen.
        int behalten = falsch.get(0);// Es wird eine zufällige falsche Antwort ausgewählt.

        System.out.println("👉 50:50 Joker aktiviert! Zwei falsche Antworten wurden entfernt:");
        for (int i = 0; i < 4; i++) {
            if (i == richtige || i == behalten) {
                System.out.println(frage.getOptionen()[i]);
            }
        }
    }

    @Override
    public String getName() {
        return "50:50";
    }
}
