package com.zavaly.utils;

import java.util.Random;

public class GeneratorsUtils {

    public static int getRandomIntNumber() {
        Random random = new Random();
        int number = random.nextInt(4000);
        return number;
    }
}
