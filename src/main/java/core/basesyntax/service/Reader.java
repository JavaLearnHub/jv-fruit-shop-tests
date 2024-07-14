package core.basesyntax.service;

import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Reader {

    private static final int LINE_TO_SKIP = 1;

    @SneakyThrows(IOException.class)
    public static List<String> readLines(String filePath) {

        return Files.lines(new File(filePath).toPath())
                .skip(LINE_TO_SKIP)
                .filter(line -> !line.trim().isEmpty())
                .toList();
    }
}
