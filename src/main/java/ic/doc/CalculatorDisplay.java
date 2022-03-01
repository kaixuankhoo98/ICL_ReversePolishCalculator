package ic.doc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorDisplay implements Updatable {
    private final ArrayList<JButton> buttonArrayList = new ArrayList<JButton>();
    private final JTextField answer = new JTextField(10);
    private final Integer numberOfButtons = 9;

    public CalculatorDisplay() {
        JFrame frame = new JFrame("Reverse Polish Calculator");
        frame.setSize(400, 300);
        for(int i = 1; i <= numberOfButtons; i++){
            String stringNumber = Integer.toString(i);
            JButton numberButton = new JButton(stringNumber);
            numberButton.addActionListener(new ButtonListener(stringNumber));
            buttonArrayList.add(numberButton);
        }
        JPanel panel = new JPanel();
        for (int i = 0; i < buttonArrayList.size(); ++i ) {
            panel.add(buttonArrayList.get(i));
        }
        panel.add(answer);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
