package org.mfsafrica.util;

public final class GeneralHelper {
    private GeneralHelper() {}

    public static int getRandomIntInRange(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
