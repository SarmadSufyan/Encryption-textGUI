import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EncryptionGUI {

    private JFrame frame;
    private JTextField inputField;
    private JTextField shiftField;
    private JTextArea resultArea;
    private JComboBox<String> optionComboBox;
    private JLabel backgroundLabel; // JLabel for the background image

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new EncryptionGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EncryptionGUI() {
        frame = new JFrame();
        frame.setTitle("Text Encryption");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        addBackground(); // Add the background image
        addComponents();
        frame.setVisible(true);
    }

    private void addBackground() {
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\COMPUTER POINT\\Desktop\\encryption\\Encryption\\src\\yay (2).jpeg"); // Change to your image path
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 500, 500);
        frame.add(backgroundLabel);
    }

    private void addComponents() {
        JLabel optionLabel = new JLabel("Select Option:");
        optionLabel.setBounds(20, 20, 120, 20);
        backgroundLabel.add(optionLabel);

        String[] options = {"Enter File Path", "Enter Text"};
        optionComboBox = new JComboBox<>(options);
        optionComboBox.setBounds(150, 20, 200, 20);
        backgroundLabel.add(optionComboBox);

        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setBounds(20, 60, 120, 20);
        backgroundLabel.add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(150, 60, 200, 20);
        backgroundLabel.add(inputField);

        JLabel shiftLabel = new JLabel("Shift Value:");
        shiftLabel.setBounds(20, 100, 120, 20);
        backgroundLabel.add(shiftLabel);

        shiftField = new JTextField();
        shiftField.setBounds(150, 100, 50, 20);
        backgroundLabel.add(shiftField);

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(20, 140, 100, 30);
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptAndDisplay();
            }
        });
        backgroundLabel.add(encryptButton);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 180, 330, 100);
        resultArea.setEditable(false);
        backgroundLabel.add(resultArea);
    }

    private void encryptAndDisplay() {
        String selectedOption = (String) optionComboBox.getSelectedItem();
        String input = inputField.getText();
        String shiftValue = shiftField.getText();

        try {
            int shift = Integer.parseInt(shiftValue);

            if ("Enter File Path".equals(selectedOption)) {
                encryptFromFile(input, shift);
            } else if ("Enter Text".equals(selectedOption)) {
                encryptText(input, shift);
            } else {
                resultArea.setText("Invalid option selected.");
            }
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid shift value. Please enter a valid integer.");
        }
    }

    private void encryptFromFile(String filePath, int shift) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            StringBuilder textBuilder = new StringBuilder();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                textBuilder.append(line).append("\n");
            }

            fileScanner.close();

            String text = textBuilder.toString();
            String encryptedText = encrypt(text, shift);

            resultArea.setText("Encrypted Text:\n" + encryptedText);
        } catch (FileNotFoundException e) {
            resultArea.setText("File not found: " + filePath);
        }
    }

    private void encryptText(String text, int shift) {
        String encryptedText = encrypt(text, shift);
        resultArea.setText("Encrypted Text:\n" + encryptedText);
    }

    private String encrypt(String text, int shift) {
        // Your encryption logic here (same as in your original code)
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                char shiftedChar = (char) (ch + shift);

                if (Character.isUpperCase(ch)) {
                    shiftedChar = (char) ((shiftedChar - 'A') % 26 + 'A');
                } else if (Character.isLowerCase(ch)) {
                    shiftedChar = (char) ((shiftedChar - 'a') % 26 + 'a');
                } else {
                    shiftedChar = (char) ((shiftedChar - '0') % 10 + '0');
                }

                encryptedText.append(shiftedChar);
            } else {
                encryptedText.append(ch);
            }
        }

        return encryptedText.toString();
    }
}
