// Java program to create a simple calculator

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator extends JFrame implements ActionListener {
    // create a frame
    static JFrame frame;

    // create a textfield
    static JTextField textField = new JTextField(21);

    // store operator and operands
    String operand1, operator, operand2;


    // create number buttons and some operators
    static JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, be, beq, beq1, bsq;

    static JPanel p;

    static JPanel l1, l2, l3, l4, l5;

    // default constructor
    Calculator() {
        operand1 = operator = operand2 = "";
    }

    // main function
    public static void main(String[] args) {
        // create a object of class
        Calculator calc = new Calculator();

        //create a frame and set specs
        setFrameSpecs();

        setButtonsWithActionListeners(calc);

        addButtonsToPanel();

        frame.add(p);
        // add panel to frame

        frame.setResizable(false);

        frame.show();

    }

    private static void setFrameSpecs() {

        // create a frame
        frame = new JFrame("Calculator");


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //opens the frame in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setSize(250, 200);
    }

    private static void addButtonsToPanel() {
        p = new JPanel();

        l1 = new JPanel();
        l2 = new JPanel();
        l3 = new JPanel();
        l4 = new JPanel();
        l5 = new JPanel();

        l1.setLayout(new BoxLayout(l1, BoxLayout.X_AXIS));
        l2.setLayout(new BoxLayout(l2, BoxLayout.X_AXIS));
        l3.setLayout(new BoxLayout(l3, BoxLayout.X_AXIS));
        l4.setLayout(new BoxLayout(l4, BoxLayout.X_AXIS));
        l5.setLayout(new BoxLayout(l5, BoxLayout.X_AXIS));

        p.add(textField);
        l1.add(beq,BorderLayout.EAST);
        l2.add(b7);
        l2.add(b8);
        l2.add(b9);
        l2.add(bd);
        l2.add(bsq); //second line
        l3.add(b4);
        l3.add(b5);
        l3.add(b6);
        l3.add(bm); // third line
        l4.add(b1);
        l4.add(b2);
        l4.add(b3);
        l4.add(bs); // 4th line
        l5.add(b0);
        l5.add(be);
        l5.add(beq1);
        l5.add(ba);

        // add elements to panel
        p.add(l1,BorderLayout.EAST);
        p.add(l2, BorderLayout.WEST);
        p.add(l3, BorderLayout.WEST);
        p.add(l4, BorderLayout.WEST);
        p.add(l5, BorderLayout.WEST);

        //TODO- add 1/x button,% button,backspace button.
    }

    private static void setButtonsWithActionListeners(Calculator calc) {

        // create number buttons
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        // equals button
        beq1 = new JButton("=");

        // create operator buttons
        ba = new JButton("+");
        bs = new JButton("-");
        bd = new JButton("/");
        bm = new JButton("*");
        beq = new JButton("C");
        bsq = new JButton("sqt");

        // create . button
        be = new JButton(".");

        // add action listeners
        bm.addActionListener(calc);
        bd.addActionListener(calc);
        bs.addActionListener(calc);
        ba.addActionListener(calc);
        b9.addActionListener(calc);
        b8.addActionListener(calc);
        b7.addActionListener(calc);
        b6.addActionListener(calc);
        b5.addActionListener(calc);
        b4.addActionListener(calc);
        b3.addActionListener(calc);
        b2.addActionListener(calc);
        b1.addActionListener(calc);
        b0.addActionListener(calc);
        be.addActionListener(calc);
        beq.addActionListener(calc);
        beq1.addActionListener(calc);
        bsq.addActionListener(calc);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        // if the value is a number
        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
            // if operand is present then add to second no
            if (!operator.equals(""))
                operand2 = operand2 + s;
            else
                operand1 = operand1 + s;

            // set the value of text
            textField.setText(operand1 + operator + operand2);

        } else if (s.charAt(0) == 'C') {
            // clear the one letter
            operand1 = operator = operand2 = "";

            // set the value of text
            textField.setText(operand1 + operator + operand2);

        } else if (s.charAt(0) == '=') {

            double te = apply();

            // store the value in 1st

            // set the value of text

            textField.setText(operand1 + operator + operand2 + "=" + te);

            // convert it to string
            operand1 = Double.toString(te);
            operator = operand2 = "";

        } else if (s.equals("sqt")) {
            if (!operand1.equals("")) {
                double te = apply();
                operand1 = String.valueOf(te);
                operator = operand2 = "";
            } else {
                operator = s;
                textField.setText(s);
            }
        } else {

            // if there was no operand
            if (operator.equals("") || operand2.equals("")) {
                operator = s;

                // else evaluate
            } else {
                double te = apply();
                // store the value in 1st

                // convert it to string
                operand1 = Double.toString(te);

                // place the operator
                operator = s;

                // make the operand blank
                operand2 = "";

            }

            // set the value of text
            textField.setText(operand1 + operator + operand2);
        }

        System.out.println("im here");
        System.out.println("operand1    " + operand1);
        System.out.println("operator    " + operator);
        System.out.println("operand2    " + operand2);
// todo - delete
    }

    private double apply() {
        return switch (operator) {
            case "+" -> (Double.parseDouble(operand1) + Double.parseDouble(operand2));
            case "-" -> (Double.parseDouble(operand1) - Double.parseDouble(operand2));
            case "/" -> (Double.parseDouble(operand1) / Double.parseDouble(operand2));
            case "sqt" -> Math.sqrt(Double.parseDouble(operand2));
            default -> (Double.parseDouble(operand1) * Double.parseDouble(operand2));
        };
    }
}
