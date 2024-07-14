package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.dto.Transaction;

public interface Operation {

    void operate(Transaction transaction, Storage storage);
}
