package org.example.qa.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

public class Generator {
    public static Random random = new Random();
    public static String randomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
