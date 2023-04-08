package algorithms.text.compression;

import utils.Inputted;

import java.util.Map;

import static algorithms.text.compression.Huffman.*;

class HuffmanTest {

    public static void main(String[] args) {
        String text = "hello world";
        text = Inputted.inputStringNonBlack("Enter Text: ", "Text cant be empty");
        Map<Character, String> huffmanCodes = buildHuffmanTree(text);
        String encodedText = encode(text, huffmanCodes);
        String decodedText = decode(encodedText, huffmanCodes);

        System.out.println("Original text: " + text);
        System.out.println("Encoded text: " + encodedText);
        System.out.println("Decoded text: " + decodedText);
    }
}