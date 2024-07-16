package core.basesyntax.service;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    private static final String WRONG_FILE_PATH = "wrong file path";
    private static final String EMPTY_FILE = "emptyFile.txt";
    private static final String RIGHT_FORMAT_FILE = "activitiesStore.txt";

    @Test
    void getFileRows_wrongFilePath_failure() {
        assertThrows(NoSuchFileException.class, () -> Reader.readLines(WRONG_FILE_PATH));
    }

    @Test
    void getFileRows_emptyFile_success() {
        List<String> actual = Reader.readLines(EMPTY_FILE);
        List<String> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    void getFileRows_rightDataFile_success() {
        List<String> actual = Reader.readLines(RIGHT_FORMAT_FILE);
        List<String> expected = new ArrayList<>();

        expected.add("b,banana,20");
        expected.add("s,banana,100");
        expected.add("p,banana,13");
        expected.add("r,banana,5");

        assertEquals(expected, actual);
    }
}
