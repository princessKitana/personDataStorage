package com.pds.core.util;

import java.util.Random;

public final class Util {

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static int getRandomIntNumber() {
        Random random = new Random();
        return random.nextInt(1000 - 1) + 1;
    }

    public static class RandomEnum<E extends Enum<E>> {

        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }

}
