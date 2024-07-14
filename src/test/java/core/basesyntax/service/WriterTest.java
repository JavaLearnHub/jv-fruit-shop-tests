package core.basesyntax.service;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {

    private static final String WRITER_RESULT_PATH = "src/test/java/core/basesyntax/resources/writerResult.txt";
    private static final String WRONG_PATH = "src/test/java/core/basesyntax/resources";

    @Test
    void writeToFile_emptyReportProvided() {
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
    void writeToFile_rightReportProvided() {
        Writer.writeToFile(WRITER_RESULT_PATH, "fruit,quantity\nbanana,152\napple,90");

        List<String> writerResult = Reader.readLines(WRITER_RESULT_PATH);

        int actualLinesQuantity = writerResult.size();
        int expectedLinesQuantity = 2;

        String actualFirstLine = writerResult.get(0);
        String expectedFirstLine = "banana,152";

        String actualSecondLine = writerResult.get(1);
        String expectedSecondLine = "apple,90";

        assertEquals(expectedLinesQuantity, actualLinesQuantity);
        assertEquals(expectedFirstLine, actualFirstLine);
        assertEquals(expectedSecondLine, actualSecondLine);
    }

}