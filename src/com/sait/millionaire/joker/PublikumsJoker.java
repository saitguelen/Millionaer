package com.sait.millionaire.joker;
import com.sait.millionaire.Question;

public class PublikumsJoker implements  Joker{

    @Override
    public void benutzeJoker(Question frage) {
        int richtige = frage.getRichtigeAntwort();
        int[] stimmen = new int[4];

        stimmen[richtige] = 50 + (int) (Math.random() * 21); // z. B. 50–70%
        int rest = 100 - stimmen[richtige];

        for (int i = 0; i < 4; i++) {
            if (i != richtige) {
                int wert = (i != 3) ? (int) (Math.random() * (rest + 1)) : rest;
                stimmen[i] = wert;
                rest -= wert;
            }
        }

        System.out.println("📊 Publikum hat abgestimmt:");
        for (int i = 0; i < 4; i++) {
            System.out.println(frage.getOptionen()[i] + " → " + stimmen[i] + "%");
        }
    }

    @Override
    public String getName() {
        return "Publikumsjoker";
    }

}
