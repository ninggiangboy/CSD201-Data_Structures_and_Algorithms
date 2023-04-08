package algorithms.text.compression;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    private static class Node implements Comparable<Node> {
        char character;
        int frequency;
        Node left;
        Node right;

        Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node other) {
            return this.frequency - other.frequency;
        }
    }

    public static Map<Character, String> buildHuffmanTree(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            pq.offer(node);
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.offer(parent);
        }

        Node root = pq.poll();
        Map<Character, String> huffmanCodes = new HashMap<>();
        buildHuffmanCodes(root, "", huffmanCodes);

        return huffmanCodes;
    }

    private static void buildHuffmanCodes(Node node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.character != '\0') {
            huffmanCodes.put(node.character, code);
        }
        buildHuffmanCodes(node.left, code + "0", huffmanCodes);
        buildHuffmanCodes(node.right, code + "1", huffmanCodes);
    }

    public static String encode(String text, Map<Character, String> huffmanCodes) {
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }
        return encodedText.toString();
    }

    public static String decode(String encodedText, Map<Character, String> huffmanCodes) {
        StringBuilder decodedText = new StringBuilder();
        String currentCode = "";
        for (int i = 0; i < encodedText.length(); i++) {
            currentCode += encodedText.charAt(i);
            for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
                if (entry.getValue().equals(currentCode)) {
                    decodedText.append(entry.getKey());
                    currentCode = "";
                    break;
                }
            }
        }
        return decodedText.toString();
    }
}
