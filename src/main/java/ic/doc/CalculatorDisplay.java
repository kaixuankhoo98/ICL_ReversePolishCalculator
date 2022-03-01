package ic.doc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorDisplay implements DisplayInterface {
    private final ArrayList<JButton> buttonArrayList = new ArrayList<>(); // TODO is this necessary?
    private final JTextField userInput = new JTextField(10);
    private final JTextField answer = new JTextField(10);
    private final Integer numberOfButtons = 9;
    private final JButton clear = new JButton("CE");
    private final JButton plus = new JButton("+");
    private final JButton minus = new JButton("-");
    private String inputString = "";

    private final CalculatorModel model = new CalculatorModel(this);

    public CalculatorDisplay() {
        JFrame frame = new JFrame("Reverse Polish Calculator");
        frame.setSize(400, 300);

        // Creating each number button
        for(int i = 1; i <= numberOfButtons; i++){
            JButton numberButton = new JButton(Integer.toString(i));
            numberButton.addActionListener(new NumberButtonController(i));
            buttonArrayList.add(numberButton);
        }

        // CE button clears inputString
        clear.addActionListener(e -> {
            inputString = "";
            userInput.setText(inputString);
            model.clearStack();
            answer.setText("");
        });

        // Operand action listeners
        plus.addActionListener(new OperandButtonController("+"));
        minus.addActionListener(new OperandButtonController("-"));

        // Formatting the panel
        JPanel panel = new JPanel();
        for (JButton jButton : buttonArrayList) {
            panel.add(jButton);
        }
        panel.add(clear);
        panel.add(plus);
        panel.add(minus);
        panel.add(userInput);
        panel.add(answer);

        // Setting panel to frame
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void updateAnswerField() {
        answer.setText(String.valueOf(model.topOfStack()));
    }

    private class NumberButtonController implements ActionListener {
        private final int num;

        public NumberButtonController(int num) {
            this.num = num;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            inputString = inputString + num + " "; // add the number to the string
            userInput.setText(inputString);
            model.addToStack(num);
            // add to stack in model
        }
    }

    private class OperandButtonController implements ActionListener {
        private final String operand;

        OperandButtonController(String operand) {
            this.operand = operand;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            model.calculate(operand);
        }
    }
}
