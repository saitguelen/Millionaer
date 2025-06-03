package com.sait.millionaire.joker;

import com.sait.millionaire.Question;

public class TelefonJoker implements Joker {

    @Override
    public void benutzeJoker(Question frage) {
        int richtige = frage.getRichtigeAntwort();
        int vorschlag;

        if (Math.random() < 0.8) {
            vorschlag = richtige;
        } else {
            do {
                vorschlag = (int) (Math.random() * 4);
            } while (vorschlag == richtige);
        }

        System.out.println("📞 Dein Freund meint: " + frage.getOptionen()[vorschlag]);
    }

    @Override
    public String getName() {
        return "Telefonjoker";
    }
}
