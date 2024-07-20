package core.basesyntax.service;

import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Optional;

public class Reader {

    private static final ClassLoader CLASSLOADER = Reader.class.getClassLoader();
    private static final int LINE_TO_SKIP = 1;

    @SneakyThrows(IOException.class)
    public static List<String> readLines(String filePath) {

        File file = Optional.ofNullable(CLASSLOADER.getResource(filePath))
                .map(URL::getFile)
                .map(File::new)
                .orElseThrow((() -> new NoSuchFileException("File not found")));

        return Files.lines(file.toPath())
                .skip(LINE_TO_SKIP)
                .filter(line -> !line.trim().isEmpty())
                .toList();
    }
}
