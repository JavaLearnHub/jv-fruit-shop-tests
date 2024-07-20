package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.dto.Transaction;

public class BalanceOperation implements Operation {

    @Override
    public void operate(Transaction transaction, Storage storage) {
        
        storage.saveToStorage(transaction.getProduct(), transaction.getQuantity());
    }
}
