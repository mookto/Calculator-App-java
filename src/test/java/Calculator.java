import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private String operator;
    private double firstNumber;
    private boolean isNewInput = true;

    public Calculator() {
        setTitle("Java Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center screen
        setLayout(new BorderLayout());

        // Display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9\\.]")) {
            if (isNewInput) {
                display.setText(cmd);
                isNewInput = false;
            } else {
                display.setText(display.getText() + cmd);
            }
        } else if (cmd.matches("[\\+\\-\\*/]")) {
            firstNumber = Double.parseDouble(display.getText());
            operator = cmd;
            isNewInput = true;
        } else if (cmd.equals("=")) {
            double secondNumber = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/": result = secondNumber != 0 ? firstNumber / secondNumber : 0; break;
            }

            display.setText(String.valueOf(result));
            isNewInput = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}
