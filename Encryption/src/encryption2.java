import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class encryption2 {
    public static String encrypt(String text, int shift) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the input file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter the shift value: ");
        int shift = scanner.nextInt();

        File file = new File(filePath);

        try {
            Scanner fileScanner = new Scanner(file);
            StringBuilder textBuilder = new StringBuilder();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                textBuilder.append(line).append("\n");
            }

            fileScanner.close();

            String text = textBuilder.toString();
            String encryptedText = encrypt(text, shift);

            System.out.println("Encrypted Text: " + encryptedText);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }

        scanner.close();
    }
}

