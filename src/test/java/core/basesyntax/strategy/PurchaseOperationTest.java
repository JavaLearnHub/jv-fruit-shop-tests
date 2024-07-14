package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.db.StorageImpl;
import core.basesyntax.dto.Transaction;
import org.junit.jupiter.api.Test;

import static core.basesyntax.model.OperationEnum.PURCHASE;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseOperationTest {

    private static final Storage storage = new StorageImpl();
    private static final PurchaseOperation purchaseOperation = new PurchaseOperation();

    @Test
    void makePurchase_rightDataProvided() {
        storage.saveToStorage("banana", 100);
        purchaseOperation.operate(new Transaction(PURCHASE, "banana", 20), storage);

        int actualBalance = storage.getQuantity("banana").orElse(0);
        int expectedBalance = 80;
        assertEquals(expectedBalance, actualBalance);
    }
}