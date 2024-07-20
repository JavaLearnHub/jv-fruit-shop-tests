package core.basesyntax.service;

import lombok.SneakyThrows;

import java.io.*;

public class Writer {

    private static final ClassLoader CLASSLOADER = Reader.class.getClassLoader();

    @SneakyThrows(IOException.class)
    public static void writeToFile(String filePath, String infoToWrite) {

        BufferedWriter writer = new BufferedWriter(new FileWriter(CLASSLOADER.getResource(filePath).getPath()));
        writer.write(infoToWrite);
        writer.close();
    }
}
