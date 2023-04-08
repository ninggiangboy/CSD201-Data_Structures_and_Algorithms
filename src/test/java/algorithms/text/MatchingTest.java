package algorithms.text;

import utils.Inputted;
import utils.Menu;

import static algorithms.text.Matching.*;

class MatchingTest {

    private static final String[] menu = {
            "Brute Force Search",
            "KMP Search"
    };

    public static void main(String[] args) {
        String text = Inputted.inputStringNonBlack("Text: ", "Empty");
        String pattern = Inputted.inputStringNonBlack("Pattern: ", "Empty");
        int index = 0;
        switch (Menu.getChoice(menu)) {
            case 1:
                index = bruteForceSearch(text, pattern);
                break;
            case 2:
                index = KMPSearch(text, pattern);
                break;
        }
        System.out.println("Index: " + index);
    }
}