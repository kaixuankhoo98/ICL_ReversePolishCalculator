package ic.doc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorDisplay implements Updatable {
    private final ArrayList<JButton> buttonArrayList = new ArrayList<JButton>();
    private final JTextField userInput = new JTextField(10);
    private final JTextField answer = new JTextField(10);
    private final Integer numberOfButtons = 9;
    private final JButton clear = new JButton("CE");
    private String inputString = "";


    public CalculatorDisplay() {
        JFrame frame = new JFrame("Reverse Polish Calculator");
        frame.setSize(400, 300);

        // Creating each number button
        for(int i = 1; i <= numberOfButtons; i++){
            String stringNumber = Integer.toString(i);
            JButton numberButton = new JButton(stringNumber);
            numberButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    inputString = inputString + stringNumber + " "; // add the number to the string
                    userInput.setText(inputString);
                }
            });
            buttonArrayList.add(numberButton);
        }

        // CE button clears inputString
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputString = "";
                userInput.setText(inputString);
            }
        });

        // Formatting the panel
        JPanel panel = new JPanel();
        for (int i = 0; i < buttonArrayList.size(); ++i ) {
            panel.add(buttonArrayList.get(i));
        }
        panel.add(clear);
        panel.add(userInput);
        panel.add(answer);

        // Setting panel to frame
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
