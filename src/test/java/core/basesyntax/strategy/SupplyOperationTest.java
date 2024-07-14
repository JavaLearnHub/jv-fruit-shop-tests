package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.db.StorageImpl;
import core.basesyntax.dto.Transaction;
import org.junit.jupiter.api.Test;

import static core.basesyntax.model.OperationEnum.SUPPLY;
import static org.junit.jupiter.api.Assertions.*;

class SupplyOperationTest {
    private static final Storage storage = new StorageImpl();
    private static final SupplyOperation supplyOperation = new SupplyOperation();

    @Test
    void makePurchase_rightDataProvided() {
        storage.saveToStorage("banana", 10);
        supplyOperation.operate(new Transaction(SUPPLY, "banana", 20), storage);

        int actualBalance = storage.getQuantity("banana").orElse(0);
        int expectedBalance = 30;
        assertEquals(expectedBalance, actualBalance);
    }
}