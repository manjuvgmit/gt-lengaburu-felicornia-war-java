package com.geektrust.lengaburu.war.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class MiscUtils {

    public static List<String> getInputFromFile(String inputFilePath) throws IOException {
        return Files.readAllLines(Paths.get(inputFilePath));
    }

    public static Integer getIntegerValue(String value, String replaceText) {
        return Integer.parseInt(value.replaceAll(replaceText, ""));
    }

//    public static Integer getFactoredValue() {
//
//    }
}
