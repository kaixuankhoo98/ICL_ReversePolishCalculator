package ic.doc;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorDisplay implements DisplayInterface {
    private final ArrayList<JButton> buttonArrayList = new ArrayList<>();
    private final JTextField userInput = new JTextField(10);
    private final JTextField answer = new JTextField(10);
    private final JButton clear = new JButton("CE");
    private final JButton plus = new JButton("+");
    private final JButton minus = new JButton("-");
    private final JButton times = new JButton("×");
    private final JButton divide = new JButton("/");

    private final CalculatorModel model = new CalculatorModel(this);

    public CalculatorDisplay() {
        JFrame frame = new JFrame("Reverse Polish Calculator");
        frame.setSize(300, 400);

        // Creating each number button
        for (int i = 1; i <= 9; i++) {
            JButton numberButton = new JButton(Integer.toString(i));
            setDarkMode(numberButton);
            numberButton.addActionListener(new NumberButtonController(i));
            buttonArrayList.add(numberButton);
        }

        // CE button
        clear.addActionListener(e -> {
            model.clearStack();
            answer.setText("");
        });
        setDarkMode(clear);

        // Operand action listeners
        plus.addActionListener(new OperandButtonController("+"));
        setDarkMode(plus);
        minus.addActionListener(new OperandButtonController("-"));
        setDarkMode(minus);
        times.addActionListener(new OperandButtonController("*"));
        setDarkMode(times);
        divide.addActionListener(new OperandButtonController("/"));
        setDarkMode(divide);

        /* NOTE: I have a list of buttons (for numbers)
         *  I have chosen to keep it in the code, so that all the formatting
         *  is done in this section (rather than adding the buttons in the
         *  for loop above). I think this makes the code neater. */

        // Formatting the panels
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(3, 3));
        for (JButton button : buttonArrayList) {
            numberPanel.add(button);
        }
        JPanel textPanels = new JPanel();
        textPanels.setLayout(new GridLayout(2, 1));
        setDarkMode(userInput);
        textPanels.add(userInput);
        setDarkMode(answer);
        textPanels.add(answer);
        JPanel operandPanels = new JPanel();
        operandPanels.setLayout(new GridLayout(3, 2));
        operandPanels.setBackground(Color.DARK_GRAY);
        operandPanels.add(plus);
        operandPanels.add(minus);
        operandPanels.add(times);
        operandPanels.add(divide);
        operandPanels.add(clear);
        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(textPanels);
        panel.add(operandPanels);
        panel.add(numberPanel);

        // Setting panel to frame
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    // Color setters
    private void setDarkMode(JButton button) {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.LIGHT_GRAY);
    }

    private void setDarkMode(JTextField textField) {
        Font font1 = new Font("Bookman Old Style", Font.BOLD, 20);
        textField.setFont(font1);
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.BLACK);
    }

    @Override
    public void updateAnswerField() {
        answer.setText(String.valueOf(model.topOfStack()));
    }

    @Override
    public void updateInputField() {
        userInput.setText(model.getUserInput());
    }

    // Action listener classes
    private class NumberButtonController implements ActionListener {
        private final int num;

        public NumberButtonController(int num) {
            this.num = num;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            model.addToStack(num);
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
