package algorithms.text.compression;

public class RunLength {

    public static String encode(String text) {
        StringBuilder encodedText = new StringBuilder();
        int count = 1;
        char prevChar = text.charAt(0);

        for (int i = 1; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar == prevChar) {
                count++;
            } else {
                encodedText.append(prevChar);
                encodedText.append(count);
                count = 1;
                prevChar = currentChar;
            }
        }

        encodedText.append(prevChar);
        encodedText.append(count);

        return encodedText.toString();
    }

    public static String decode(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        for (int i = 0; i < encodedText.length(); i += 2) {
            char character = encodedText.charAt(i);
            int count = Integer.parseInt(String.valueOf(encodedText.charAt(i + 1)));
            for (int j = 0; j < count; j++) {
                decodedText.append(character);
            }
        }
        return decodedText.toString();
    }
}
