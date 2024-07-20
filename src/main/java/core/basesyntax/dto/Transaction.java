package core.basesyntax.dto;

import lombok.Getter;
import core.basesyntax.model.OperationEnum;

@Getter
public class Transaction {

    private final OperationEnum operation;
    private final String product;
    private final int quantity;

    public Transaction(OperationEnum operation, String product, int quantity) {
        this.operation = operation;
        this.product = product;
        this.quantity = quantity;
    }
}
