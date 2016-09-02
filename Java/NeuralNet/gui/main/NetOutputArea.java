package gui.main;

import java.awt.BorderLayout;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.uti.Utilitiess;

public class NetOutputArea extends JPanel {

    private static final long serialVersionUID = -7078462918829040304L;

    private JTextArea outputTextArea;

    public NetOutputArea() {

        outputTextArea = new JTextArea();

        try {
            outputTextArea.setFont(Utilitiess.createFont("/fonts/GravityBook.ttf").deriveFont(15f));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        outputTextArea.setEditable(false);
    }

    public void appendText(String c) {
        outputTextArea.append(c);
    }

    public void setText(String c) {
        outputTextArea.setText(c);
    }

    public StringBuilder getText() {

        try {
            return new StringBuilder(outputTextArea.getText());
        } catch (NullPointerException e) {
            return null;
        }
    }
}