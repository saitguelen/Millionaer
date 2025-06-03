package com.sait.millionaire.gui;

import com.sait.millionaire.Question;
import com.sait.millionaire.QuestionLoader;
import com.sait.millionaire.data.FragenKategorie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GameWindow extends JFrame {
    private JLabel frageLabel;
    private JButton[] optionButtons;
    private List<Question> fragen;
    private int aktuelleFrage = 0;

    public GameWindow() {
        setTitle("Wer wird Millionär");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        frageLabel = new JLabel("Frage wird geladen...", SwingConstants.CENTER);
        frageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(frageLabel, BorderLayout.NORTH);

        JPanel optionPanel = new JPanel(new GridLayout(2, 2));
        optionButtons = new JButton[4];

        for (int i = 0; i < 4; i++) {
            int index = i;
            optionButtons[i] = new JButton();
            optionButtons[i].addActionListener((ActionEvent e) -> prüfeAntwort(index));
            optionPanel.add(optionButtons[i]);
        }

        add(optionPanel, BorderLayout.CENTER);

        // Fragen laden
        FragenKategorie kategorie = QuestionLoader.ladeFragenAusJson("fragen/grundlagen_java_fragen.json");
        if (kategorie != null) {
            fragen = kategorie.getFragen();
            zeigeFrage();
        } else {
            frageLabel.setText("Fragen konnten nicht geladen werden.");
        }

        setVisible(true);
    }

    private void zeigeFrage() {
        if (aktuelleFrage >= fragen.size()) {
            JOptionPane.showMessageDialog(this, "🎉 Du hast alle Fragen beantwortet!");
            dispose();
            return;
        }

        Question frage = fragen.get(aktuelleFrage);
        frageLabel.setText("<html><center>" + frage.getFrage() + "</center></html>");

        String[] optionen = frage.getOptionen();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(optionen[i]);
        }
    }

    private void prüfeAntwort(int index) {
        Question frage = fragen.get(aktuelleFrage);
        if (index == frage.getRichtigeAntwort()) {
            JOptionPane.showMessageDialog(this, "✅ Richtig!");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Falsch!\nRichtige Antwort: " + frage.getOptionen()[frage.getRichtigeAntwort()]);
            dispose();
            return;
        }
        aktuelleFrage++;
        zeigeFrage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }

}
