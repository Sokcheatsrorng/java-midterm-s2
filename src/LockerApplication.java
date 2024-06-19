import javax.swing.*;
import java.awt.*;

public class LockerApplication {
    private JFrame frame;
    private JTextField passwordField;
    private JLabel statusLabel;
    private String storedPassword = null;
    private StringBuilder enteredPassword = new StringBuilder();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LockerApplication().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Lock Class");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(4, 3));
        passwordField = new JTextField();
        passwordField.setEditable(false);
        statusLabel = new JLabel("Enter Password");

        for (int i = 1; i <= 9; i++) {
            addButton(panel, String.valueOf(i));
        }

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearPassword());
        panel.add(clearButton);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> enterPassword());
        panel.add(enterButton);

        panel.add(passwordField);
        panel.add(statusLabel);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void addButton(Container parent, String name) {
        JButton button = new JButton(name);
        button.addActionListener(e -> appendPassword(name));
        parent.add(button);
    }

    private void appendPassword(String digit) {
        enteredPassword.append(digit);
        passwordField.setText(enteredPassword.toString());
    }

    private void clearPassword() {
        enteredPassword.setLength(0);
        passwordField.setText("");
    }

    private void enterPassword() {
        if (storedPassword == null) {
            storedPassword = enteredPassword.toString();
            statusLabel.setText("Password Set");
        } else {
            if (storedPassword.equals(enteredPassword.toString())) {
                statusLabel.setText("Correct Password");
            } else {
                statusLabel.setText("Incorrect Password");
            }
        }
        clearPassword();
    }
}
