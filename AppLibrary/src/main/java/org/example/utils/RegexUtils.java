package org.example.utils;

import org.example.exceptions.NotPositiveNumberException;
import org.example.exceptions.ToLessThanFromException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tribushko Danil
 * @since 19.12.2024
 * Утилита для работы с регулярными выражениями
 */
public class RegexUtils {
    private static final String NUMBERS = "0-9";
    private static final String LETTERS = "a-zA-Z";
    private static final String LETTERS_LOWER = "a-z";
    private static final String LETTERS_UPPER = "A-Z";
    private static final String NUMBERS_LETTERS = NUMBERS + LETTERS;
    private static final String LEFT_BORDER = "[";
    private static final String RIGHT_BORDER = "]";
    private static final String START_STRING = "^";
    private static final String END_STRING = "$";
    private static final String LEFT_COUNT_BORDER = "{";
    private static final String RIGHT_COUNT_BORDER = "}";

    private RegexUtils() {
    }

    public static String emailRegex() {
        return START_STRING +
                chars(NUMBERS_LETTERS) +
                addCount(1, 15) +
                charsList(0, 1, "-,.") +
                chars(NUMBERS_LETTERS) +
                addCount(1, 15) +
                charsList(1, "@") +
                charsList(1, 8, LETTERS_LOWER) +
                charsList(0, 1, ".") +
                charsList(2, 4, LETTERS_LOWER) +
                END_STRING;
    }

    public static String addCount(int from, int to) {
        checkNumbers(from, to);
        return LEFT_COUNT_BORDER +
                from +
                "," +
                to +
                RIGHT_COUNT_BORDER;
    }

    public static String addCount(int from) {
        checkNumber(from);
        return LEFT_COUNT_BORDER +
                from +
                RIGHT_COUNT_BORDER;
    }

    public static String charsList(int from, String string) {
        checkNumber(from);
        return chars(string) + addCount(from);
    }

    public static String charsList(int from, int to, String string) {
        checkNumbers(from, to);
        return chars(string) + addCount(from, to);
    }

    public static String chars(String chars) {
        return LEFT_BORDER + chars + RIGHT_BORDER;
    }

    private static void checkNumber(int from) {
        if (from < 0) {
            throw new NotPositiveNumberException();
        }
    }

    private static void checkNumbers(int from, int to) {
        if (from < 0 || to < 0) {
            throw new NotPositiveNumberException();
        }
        if (to < from) {
            throw new ToLessThanFromException();
        }
    }

    public static Pattern pattern(String regex) {
        return Pattern.compile(regex);
    }

    public static Matcher matcher(Pattern pattern, String text) {
        return pattern.matcher(text);
    }

    public static Matcher matcher(String regex, String text) {
        return Pattern.compile(regex).matcher(text);
    }
}
