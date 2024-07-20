package core.basesyntax.service;

import core.basesyntax.db.Storage;
import core.basesyntax.db.StorageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FruitShopServiceTest {

    private final Storage storage = new StorageImpl();
    private final FruitShopService fruitShopService = new FruitShopService(storage);

    @BeforeEach
    public void init() {
        storage.clear();
    }

    @Test
    void handleActivity_wrongOperationProvided_failure() {
        assertThrows(UnsupportedOperationException.class, () ->
                fruitShopService.handleActivities("wrongOperation.txt",
                        "fruitShopResult.txt"));
    }

    @Test
    void handleActivity_wrongQuantityProvided_failure() {
        assertThrows(WrongQuantityException.class, () ->
                fruitShopService.handleActivities("wrongQuantity.txt",
                        "fruitShopResult.txt"));
    }

    @Test
    void handleActivity_emptyFileProvided_success() throws WrongQuantityException {
        fruitShopService.handleActivities("emptyFile.txt",
                "fruitShopResult.txt");
        List<String> fruitShopResult = Reader.readLines("fruitShopResult.txt");

        int actualLineQuantity = fruitShopResult.size();
        int expectedLineQuantity = 0;

        assertEquals(expectedLineQuantity, actualLineQuantity);
    }

    @Test
    void handleActivity_wrongWritePathProvided_failure() {
        assertThrows(FileNotFoundException.class, () -> fruitShopService.handleActivities("emptyFile.txt",
                ""));
    }

    @Test
    void handleActivity_wrongReadPathProvided_failure() {
        assertThrows(AccessDeniedException.class, () -> fruitShopService.handleActivities("",
                "fruitShopResult.txt"));
    }

    @Test
    void handleActivity_notFullElementProvided_failure() {
        assertThrows(IllegalArgumentException.class, () -> fruitShopService.handleActivities("notFullElement.txt",
                "fruitShopResult.txt"));
    }

    @Test
    void handleActivity_inCorrectElementProvided_failure() {
        assertThrows(IllegalArgumentException.class, () -> fruitShopService.handleActivities("inCorrectElement.txt",
                "fruitShopResult.txt"));
    }

    @Test
    void handleActivity_wrongSeparatorProvided_failure() {
        assertThrows(IllegalArgumentException.class, () -> fruitShopService.handleActivities("wrongSeparator.txt",
                "fruitShopResult.txt"));
    }

    @Test
    void handleActivity_wrongElementProvided_failure() {
        assertThrows(IllegalArgumentException.class, () -> fruitShopService.handleActivities("wrongElement.txt",
                "fruitShopResult.txt"));
    }

    @Test
    void handleActivity_rightDataProvided_success() throws WrongQuantityException {
        fruitShopService.handleActivities("activitiesStore.txt",
                "fruitShopResult.txt");

        List<String> actual = Reader.readLines("fruitShopResult.txt");
        List<String> expected = new ArrayList<>();
        expected.add("banana,112");

        assertEquals(expected, actual);
    }
}
