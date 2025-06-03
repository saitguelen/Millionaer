package com.sait.millionaire.gui;

import javax.swing.*;

public class NameDialog {
    public static String frageSpielerName() {
        return JOptionPane.showInputDialog(
                null,
                "Bitte deinen Namen eingeben:",
                "Spielername",
                JOptionPane.QUESTION_MESSAGE
        );
    }
}
