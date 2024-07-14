package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.db.StorageImpl;
import core.basesyntax.dto.Transaction;
import org.junit.jupiter.api.Test;

import static core.basesyntax.model.OperationEnum.RETURN;
import static org.junit.jupiter.api.Assertions.*;

class ReturnOperationTest {

    private static final Storage storage = new StorageImpl();
    private static final ReturnOperation returnOperation = new ReturnOperation();

    @Test
    void makeReturn_rightDataProvided() {
        storage.saveToStorage("banana", 10);
        returnOperation.operate(new Transaction(RETURN, "banana", 20), storage);

        int actualBalance = storage.getQuantity("banana").orElse(0);
        int expectedBalance = 30;
        assertEquals(expectedBalance, actualBalance);
    }
}