package algorithms.text.compression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {

    public static List<Integer> encode(String text) {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }

        String current = "";
        List<Integer> encodedText = new ArrayList<>();
        for (char c : text.toCharArray()) {
            String next = current + c;
            if (dictionary.containsKey(next)) {
                current = next;
            } else {
                encodedText.add(dictionary.get(current));
                dictionary.put(next, dictionary.size());
                current = String.valueOf(c);
            }
        }

        if (!current.isEmpty()) {
            encodedText.add(dictionary.get(current));
        }

        return encodedText;
    }

    public static String decode(List<Integer> encodedText) {
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }

        String current = "";
        StringBuilder decodedText = new StringBuilder();
        for (int code : encodedText) {
            String entry;
            if (dictionary.containsKey(code)) {
                entry = dictionary.get(code);
            } else if (code == dictionary.size()) {
                entry = current + current.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid encoded text");
            }

            decodedText.append(entry);
            if (!current.isEmpty()) {
                dictionary.put(dictionary.size(), current + entry.charAt(0));
            }
            current = entry;
        }

        return decodedText.toString();
    }
}
