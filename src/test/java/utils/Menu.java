package utils;

public class Menu {
    /**
     * Displays a menu of options to the user, prompts them to select an option, and
     * returns their choice as an integer.
     *
     * @param options an array of strings representing the menu options to be
     *                displayed.
     * @return the integer representing the user's chosen option.
     *
     * @throws IllegalArgumentException if the given options array is null or empty.
     */
    public static int getChoice(String[] options) {
        int minChoice = 1;
        int maxChoice = options.length;
        printMenu(options);
        String msg = "Choice [" + minChoice + " - " + maxChoice + "]: ";
        String errorNumberFormat = "Please enter the number of option you want to choose!";
        String errorOutOfRange = "Please enter choice in range [" + minChoice + " - " + maxChoice + "]";
        return Inputted.inputIntNumber(msg, errorNumberFormat, errorOutOfRange, minChoice, maxChoice);
    }

    /**
     * Prints a menu of options to the console, with each option numbered.
     *
     * @param options an array of strings representing the menu options to be
     *                displayed.
     *
     * @throws IllegalArgumentException if the given options array is null or empty.
     */
    public static void printMenu(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.printf("<%d> %s%n", i + 1, options[i]);
        }
        System.out.println();
    }
}
