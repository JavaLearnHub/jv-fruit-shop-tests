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
                fruitShopService.handleActivities("src/test/java/core/basesyntax/resources/wrongOperation.txt",
                        "src/test/java/core/basesyntax/resources/fruitShopResult.txt"));
    }

    @Test
    void handleActivity_wrongQuantityProvided_failure() {
        assertThrows(WrongQuantityException.class, () ->
                fruitShopService.handleActivities("src/test/java/core/basesyntax/resources/wrongQuantity.txt",
                        "src/test/java/core/basesyntax/resources/fruitShopResult.txt"));
    }

    @Test
    void handleActivity_emptyFileProvided() throws WrongQuantityException {
        fruitShopService.handleActivities("src/test/java/core/basesyntax/resources/emptyFile.txt",
                "src/test/java/core/basesyntax/resources/fruitShopResult.txt");
        List<String> fruitShopResult = Reader.readLines("src/test/java/core/basesyntax/resources/fruitShopResult.txt");
        List<String> lol = new ArrayList<>();
        int actualLineQuantity = fruitShopResult.size();
        int expectedLineQuantity = 0;
        assertEquals(lol, fruitShopResult);
//        assertEquals(expectedLineQuantity, actualLineQuantity);
    }

    @Test
    void handleActivity_wrongWritePathProvided_failure() {
        assertThrows(FileNotFoundException.class, () -> fruitShopService.handleActivities("src/test/java/core/basesyntax/resources/emptyFile.txt",
                "src/test/java/core/basesyntax/resources"));
    }

    @Test
    void handleActivity_wrongReadPathProvided_failure() {
        assertThrows(AccessDeniedException.class, () -> fruitShopService.handleActivities("src/test/java/core/basesyntax/resources",
                "src/test/java/core/basesyntax/resources/fruitShopResult.txt"));
    }

    @Test
    void handleActivity_notFullElementProvided_failure() {
        assertThrows(IllegalArgumentException.class, () -> fruitShopService.handleActivities("src/test/java/core/basesyntax/resources/notFullElement.txt",
                "src/test/java/core/basesyntax/resources/fruitShopResult.txt"));
    }

    @Test
    void handleActivity_inCorrectElementProvided_failure() {
        assertThrows(IllegalArgumentException.class, () -> fruitShopService.handleActivities("src/test/java/core/basesyntax/resources/inCorrectElement.txt",
                "src/test/java/core/basesyntax/resources/fruitShopResult.txt"));
    }

    @Test
    void handleActivity_wrongSeparatorProvided_failure() {
        assertThrows(IllegalArgumentException.class, () -> fruitShopService.handleActivities("src/test/java/core/basesyntax/resources/wrongSeparator.txt",
                "src/test/java/core/basesyntax/resources/fruitShopResult.txt"));
    }

    @Test
    void handleActivity_wrongElementProvided_failure() {
        assertThrows(IllegalArgumentException.class, () -> fruitShopService.handleActivities("src/test/java/core/basesyntax/resources/wrongElement.txt",
                "src/test/java/core/basesyntax/resources/fruitShopResult.txt"));
    }

    @Test
    void handleActivity_rightDataProvided() throws WrongQuantityException {
        fruitShopService.handleActivities("src/test/java/core/basesyntax/resources/activitiesStore.txt",
                "src/test/java/core/basesyntax/resources/fruitShopResult.txt");

        List<String> actual = Reader.readLines("src/test/java/core/basesyntax/resources/fruitShopResult.txt");
        List<String> expected = new ArrayList<>();
        expected.add("banana,112");

        assertEquals(expected, actual);
    }
}