package ic.doc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    private final String listeningButton;

    public ButtonListener(String stringNumber) {
        listeningButton = stringNumber;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
