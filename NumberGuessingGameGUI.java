import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class NumberGuessingGameGUI extends JFrame {

    private int randomNumber;
     private int attemptCount = 0;
    private int totalScore = 0;
    private int totalRounds = 0;

    private JTextField inputField;
    private JLabel statusLabel;
    private JLabel infoLabel;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));
        // Instructions
        JLabel titleLabel = new JLabel("Guess a number between 1 and 100");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);
        // Input field
        inputField = new JTextField();
        add(inputField);
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton guessButton = new JButton("Guess");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(guessButton);
        buttonPanel.add(exitButton);
        add(buttonPanel);
        // Feedback
        statusLabel = new JLabel("Enter your guess above.");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel);
        // Info (score and attempts)
        infoLabel = new JLabel("Attempts: 0 | Score: 0 | Rounds: 0");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(infoLabel);
        // Button actions
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        startNewRound();
    }
    private void processGuess() {
        String input = inputField.getText();
        try {
            int guess = Integer.parseInt(input);
            attemptCount++;
            if (guess == randomNumber) {
                totalScore++;
                totalRounds++;
                JOptionPane.showMessageDialog(this, "Correct! You guessed it in " + attemptCount + " attempts.");
                startNewRound();
            } else if (guess < randomNumber) {
                statusLabel.setText("Too low! Try again.");
            } else {
                statusLabel.setText("Too high! Try again.");
            }
            if (attemptCount >= 10 && guess != randomNumber) {
                totalRounds++;
                JOptionPane.showMessageDialog(this, "Out of attempts! The number was " + randomNumber);
                startNewRound();
            }
            updateInfo();
        } catch (NumberFormatException ex) {
            statusLabel.setText("Invalid input. Please enter a number.");
        }
    }
    private void updateInfo() {
        infoLabel.setText("Attempts: " + attemptCount + " | Score: " + totalScore + " | Rounds: " + totalRounds);
        inputField.setText("");
    }
    private void startNewRound() {
        randomNumber = new Random().nextInt(100) + 1;
        attemptCount = 0;
        statusLabel.setText("Enter your guess above.");
        updateInfo();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGameGUI game = new NumberGuessingGameGUI();
            game.setVisible(true);
        });
    }
}
