package org.example;
import Model.Polynomial;
import View.Interface;
import Controller.Controller;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface ui = new Interface();
            Controller controller = new Controller(ui);

            JFrame frame = new JFrame("Calculator de Polinoame");
            frame.setContentPane(ui.getPanel1());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
