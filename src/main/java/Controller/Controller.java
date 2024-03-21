package Controller;
import Model.Polynomial;
import Model.DivisionResult;
import View.Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Interface view;
    private Polynomial polynomial1;
    private Polynomial polynomial2;

    public Controller(Interface view) {
        this.view = view;
        polynomial1 = new Polynomial();
        polynomial2 = new Polynomial();

        view.getButton1().addActionListener(new Button1Listener());
        view.getButton2().addActionListener(new Button2Listener());
        view.getButton3().addActionListener(new Button3Listener());
        view.getButton4().addActionListener(new Button4Listener());
        view.getDxButton().addActionListener(new DxButtonListener());
        view.getButton6().addActionListener(new Button6Listener());
    }

    class Button1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String polynomialString1 = view.getTextField1().getText();
            polynomial1 = polynomial1.conversion(polynomialString1);

            String polynomialString2 = view.getTextField2().getText();
            polynomial2 = polynomial2.conversion(polynomialString2);

            if (polynomial1.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 1 incorect. Reintroduceti valorile");
                return;
            }

            if (polynomial2.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 2 incorect. Reintroduceti valorile");
                return;
            }

            Polynomial result = polynomial1.addition(polynomial2);
            view.getTextArea1().setText(result.toString());
        }
    }

    class Button2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String polynomialString1 = view.getTextField1().getText();
            polynomial1 = polynomial1.conversion(polynomialString1);

            String polynomialString2 = view.getTextField2().getText();
            polynomial2 = polynomial2.conversion(polynomialString2);

            if (polynomial1.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 1 incorect. Reintroduceti valorile");
                return;
            }

            if (polynomial2.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 2 incorect. Reintroduceti valorile");
                return;
            }

            Polynomial result = polynomial1.subtraction(polynomial2);
            view.getTextArea1().setText(result.toString());
        }
    }

    class Button3Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String polynomialString1 = view.getTextField1().getText();
            polynomial1 = polynomial1.conversion(polynomialString1);

            String polynomialString2 = view.getTextField2().getText();
            polynomial2 = polynomial2.conversion(polynomialString2);

            if (polynomial1.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 1 incorect. Reintroduceti valorile");
                return;
            }

            if (polynomial2.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 2 incorect. Reintroduceti valorile");
                return;
            }

            Polynomial result = polynomial1.multiplication(polynomial2);
            view.getTextArea1().setText(result.toString());
        }
    }

    class Button4Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String polynomialString1 = view.getTextField1().getText();
            polynomial1 = polynomial1.conversion(polynomialString1);

            String polynomialString2 = view.getTextField2().getText();
            polynomial2 = polynomial2.conversion(polynomialString2);

            if (polynomial1.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 1 incorect. Reintroduceti valorile");
                return;
            }

            if (polynomial2.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 2 incorect. Reintroduceti valorile");
                return;
            }

            DivisionResult divisionResult = polynomial1.division(polynomial2);
            Polynomial quotient = divisionResult.getQuotient();
            Polynomial remainder = divisionResult.getRemainder();

            String resultText = "Quotient:\n" + quotient.toString() + "\nRemainder:\n" + remainder.toString();
            view.getTextArea1().setText(resultText);
        }
    }


    class DxButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String polynomialString1 = view.getTextField1().getText();
            polynomial1 = polynomial1.conversion(polynomialString1);

            if (polynomial1.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 1 incorect. Reintroduceti valorile");
                return;
            }

            Polynomial result = polynomial1.derivative();
            view.getTextArea1().setText(result.toString());
        }
    }

    class Button6Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String polynomialString1 = view.getTextField1().getText();
            polynomial1 = polynomial1.conversion(polynomialString1);

            if (polynomial1.getCoefficient().isEmpty()) {
                view.getTextArea1().setText("Polinom 1 incorect. Reintroduceti valorile");
                return;
            }

            Polynomial result = polynomial1.integration();
            view.getTextArea1().setText(result.toString());
        }
    }
}