package com.github.polyrocketmatt.cyclone.impl.utils;

import java.util.Random;

public class NameResolver {

    private final static Random random = new Random();

    public static String resolveName(String prefix) {
        return prefix + random.nextInt(16384);
    }

}
