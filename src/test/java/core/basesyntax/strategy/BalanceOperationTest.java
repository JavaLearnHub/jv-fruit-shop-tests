package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.db.StorageImpl;
import core.basesyntax.dto.Transaction;
import org.junit.jupiter.api.Test;

import static core.basesyntax.model.OperationEnum.BALANCE;
import static org.junit.jupiter.api.Assertions.*;

class BalanceOperationTest {

    private final Storage storage = new StorageImpl();
    private final Operation balanceOperation = new BalanceOperation();

    @Test
    void setBalance_rightDataProvided_success() {
        balanceOperation.operate(new Transaction(BALANCE, "banana", 20), storage);

        int actualBalance = storage.getQuantity("banana").orElse(0);
        int expectedBalance = 20;

        assertEquals(expectedBalance, actualBalance);
    }
}
