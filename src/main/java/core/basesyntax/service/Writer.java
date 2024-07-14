package core.basesyntax.service;

import lombok.SneakyThrows;

import java.io.*;

public class Writer {

    @SneakyThrows(IOException.class)
    public static void writeToFile(String filePath, String infoToWrite) {

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(infoToWrite);
        writer.close();
    }
}
