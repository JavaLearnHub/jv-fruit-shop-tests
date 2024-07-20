package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.dto.Transaction;

public class SupplyOperation implements Operation {

    @Override
    public void operate(Transaction transaction, Storage storage) {

        String transactionProduct = transaction.getProduct();
        int oldQuantity = storage.getQuantity(transactionProduct).orElse(0);

        storage.saveToStorage(transactionProduct, oldQuantity + transaction.getQuantity());
    }
}
