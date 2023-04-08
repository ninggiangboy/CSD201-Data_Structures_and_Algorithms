package algorithms.text.compression;

import utils.Inputted;

import java.util.List;

import static algorithms.text.compression.LZW.*;

class LZWTest {

    public static void main(String[] args) {
        String text = "ABABABABABABA";
        text = Inputted.inputStringNonBlack("Enter Text: ", "Text cant be empty");
        List<Integer> encodedText = encode(text);
        String decodedText = decode(encodedText);

        System.out.println("Original text: " + text);
        System.out.println("Encoded text: " + encodedText);
        System.out.println("Decoded text: " + decodedText);
    }
}