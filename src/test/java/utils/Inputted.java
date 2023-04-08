package utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Inputted {

    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * Prompts the user for input with the given message, validates the input
     * using the given regular expression, and returns the validated input. If
     * the user's input does not match the regular expression, an error message
     * is displayed and the user is prompted to try again.
     *
     * @param msg       the message to display to the user when prompting for input.
     * @param regex     the regular expression used to validate the user's input.
     * @param errFormat the error message to display to the user when their input
     *                  does
     *                  not match the regular expression.
     * @return the validated user input.
     *
     * @throws IllegalArgumentException if any of the input parameters are null.
     */
    private static String getByRegex(String msg, String regex, String errFormat) {
        String output;
        while (true) {
            System.out.print(msg);
            output = INPUT.nextLine().trim();
            if (output.matches(regex)) {
                return output;
            } else {
                System.out.println(errFormat);
            }
        }
    }

    /**
     * Prompts the user for input with the given message and validates the input
     * to ensure it contains at least one non-whitespace character. If the
     * user's input is invalid, an error message is displayed and the user is
     * prompted to try again.
     *
     * @param msg the message to display to the user when prompting for input.
     * @param err the error message to display to the user when their input is
     *            invalid.
     * @return the validated user input.
     *
     * @throws IllegalArgumentException if any of the input parameters are null.
     */
    public static String inputStringNonBlack(String msg, String err) {
        String regex = "^.+$";
        String output = getByRegex(msg, regex, err);
        return output;
    }

    /**
     *
     * Prompts the user to input an integer number within a given range
     * (inclusive) using regular expression.
     *
     * @param msg               the message to display to the user
     * @param errorNumberFormat the error message to display if the input format
     *                          is incorrect
     * @param errorOutOfRange   the error message to display if the input is out
     *                          of range
     * @param min               the minimum value of the input range (inclusive)
     * @param max               the maximum value of the input range (inclusive)
     * @return the integer number input by the user
     */
    public static int inputIntNumber(
            String msg,
            String errorNumberFormat,
            String errorOutOfRange,
            int min,
            int max) {
        if (max < min) {
            int temp = max;
            max = min;
            min = temp;
        }
        String regex = "^[+-]?\\d+$";
        BigInteger output;
        while (true) {
            output = new BigInteger(getByRegex(msg, regex, errorNumberFormat));
            if (output.compareTo(BigInteger.valueOf(min)) < 0
                    || output.compareTo(BigInteger.valueOf(max)) > 0) {
                System.out.println(errorOutOfRange);
            } else {
                return output.intValue();
            }
        }
    }

    /**
     *
     * Prompts the user to input a double number within a given range
     * (inclusive) using regular expression.
     *
     * @param msg               the message to display to the user
     * @param errorNumberFormat the error message to display if the input format
     *                          is incorrect
     * @param errorOutOfRange   the error message to display if the input is out
     *                          of range
     * @param min               the minimum value of the input range (inclusive)
     * @param max               the maximum value of the input range (inclusive)
     * @return the double number input by the user
     */
    public static double inputDoubleNumber(
            String msg,
            String errorNumberFormat,
            String errorOutOfRange,
            double min,
            double max) {
        if (max < min) {
            double temp = max;
            max = min;
            min = temp;
        }
        String regex = "^-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?$";
        BigDecimal output;
        while (true) {
            output = new BigDecimal(getByRegex(msg, regex, errorNumberFormat));
            if (output.compareTo(BigDecimal.valueOf(min)) < 0
                    || output.compareTo(BigDecimal.valueOf(max)) > 0) {
                System.out.println(errorOutOfRange);
            } else {
                return output.doubleValue();
            }
        }
    }

    /**
     *
     * Prompts the user with a message and waits for them to input "y" or "n"
     * (case-insensitive), or "yes" or "no" (case-insensitive). If the input is
     * valid, returns true for "y" or "yes", and false for "n" or "no". If the
     * input is invalid, continues prompting until a valid input is received.
     *
     * @param msg the message to prompt the user with
     * @return true for "y" or "yes", false for "n" or "no"
     */
    public static boolean pressYN(String msg) {
        String regex = "^(?i)(y|n|yes|no)$";
        while (true) {
            switch (getByRegex(msg, "Y/N only!!!", regex).toLowerCase()) {
                case "y":
                case "yes":
                    return true;
                case "n":
                case "no":
                    return false;
            }
        }
    }

    /**
     *
     * Prompts the user with a message and waits for them to input an email
     * address with the format <account name>@<domain>. If the input is valid,
     * returns the input as a String. If the input is invalid, continues
     * prompting until a valid input is received.
     *
     * @param msg the message to prompt the user with
     * @return the valid email address entered by the user as a String
     */
    public static String inputEmail(String msg) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String output = getByRegex(msg, regex, "Please enter email with format <account name>@<domain>!");
        return output.toLowerCase();
    }

    /**
     *
     * Prompts the user with a message and waits for them to input a phone
     * number in one of the following formats:
     * ###-###-####
     * (###) ###-####
     * ### ### ####
     * ###.###.####
     * ##########
     * If the input is valid, returns the input as a normalized phone number (digits
     * only with no separators). If the input is invalid, continues prompting until
     * a valid input is received.
     *
     * @param msg the message to prompt the user with
     * @return the valid phone number entered by the user as a String ###.###.####
     */
    public static String inputPhone(String msg) {
        String regex = "^(\\d{3}[-.\\s]??|\\(\\d{3}\\) ?|\\d{3} ?)\\d{3}[-.\\s]??\\d{4}$";
        String output = getByRegex(msg, regex, "Please enter valid phone number (must have 10 digits)!!");
        output = output.replaceAll("\\D", "").replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3");
        return output;
    }
}
