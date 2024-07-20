package core.basesyntax.service;

import core.basesyntax.db.Storage;
import core.basesyntax.db.StorageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportCreatorTest {

    private static final Storage storage = new StorageImpl();

    @BeforeEach
    public void init(){
        storage.clear();
    }

    @Test
    void getReport_emptyFile_success() {
        String actual = ReportCreator.createReport(storage);
        String expected = new StringBuilder()
                .append("fruit,quantity")
                .append(System.lineSeparator())
                .toString();

        assertEquals(expected, actual);
    }

    @Test
    void getReport_rightDataFile_success() {
        storage.saveToStorage("banana", 152);
        storage.saveToStorage("apple", 90);

        String actual = ReportCreator.createReport(storage);
        String expected = new StringBuilder()
                .append("fruit,quantity")
                .append(System.lineSeparator())
                .append("banana,152")
                .append(System.lineSeparator())
                .append("apple,90")
                .append(System.lineSeparator())
                .toString();

        assertEquals(expected, actual);
    }
}
