package core.basesyntax.service;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {

    private static final String WRITER_RESULT_PATH = "writerResult.txt";
    private static final String WRONG_PATH = "";

    @Test
    void writeToFile_emptyReportProvided_success() {
        Writer.writeToFile(WRITER_RESULT_PATH, "");

        int actualLinesQuantity = Reader.readLines(WRITER_RESULT_PATH).size();
        int expectedLinesQuantity = 0;

        assertEquals(expectedLinesQuantity, actualLinesQuantity);
    }

    @Test
    void writeToFile_wrongPathProvided_failure() {
        assertThrows(FileNotFoundException.class, ()->Writer.writeToFile(WRONG_PATH, ""));
    }

    @Test
    void writeToFile_rightReportProvided_success() {
        Writer.writeToFile(WRITER_RESULT_PATH, "fruit,quantity\nbanana,152\napple,90");

        List<String> writerResult = Reader.readLines(WRITER_RESULT_PATH);

        int actualLinesQuantity = writerResult.size();
        int expectedLinesQuantity = 2;
        assertEquals(expectedLinesQuantity, actualLinesQuantity);

        String actualFirstLine = writerResult.get(0);
        String expectedFirstLine = "banana,152";
        assertEquals(expectedFirstLine, actualFirstLine);

        String actualSecondLine = writerResult.get(1);
        String expectedSecondLine = "apple,90";
        assertEquals(expectedSecondLine, actualSecondLine);
    }
}
