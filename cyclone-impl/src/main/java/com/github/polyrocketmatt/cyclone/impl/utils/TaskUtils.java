package com.github.polyrocketmatt.cyclone.impl.utils;

public class TaskUtils {

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String randomIdentifier(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++)
            builder.append(ALPHABET.charAt((int) (Math.random() * ALPHABET.length())));
        return builder.toString();
    }

}
