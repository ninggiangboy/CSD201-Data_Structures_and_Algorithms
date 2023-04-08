package algorithms.text.compression;

import utils.Inputted;

import static algorithms.text.compression.RunLength.*;

class RunLengthTest {

    public static void main(String[] args) {
        String text = "FFFFOOOOFFFOOFFFFFOOOOOOOO";
        text = Inputted.inputStringNonBlack("Enter Text: ", "Text cant be empty");
        String encodedText = encode(text);
        String decodedText = decode(encodedText);

        System.out.println("Original text: " + text);
        System.out.println("Encoded text: " + encodedText);
        System.out.println("Decoded text: " + decodedText);
    }
}