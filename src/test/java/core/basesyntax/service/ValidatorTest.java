package core.basesyntax.service;

import core.basesyntax.db.Storage;
import core.basesyntax.db.StorageImpl;
import core.basesyntax.dto.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static core.basesyntax.model.OperationEnum.BALANCE;
import static core.basesyntax.model.OperationEnum.PURCHASE;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private static final Storage storage = new StorageImpl();

    @BeforeEach
    public void init(){
        storage.clear();
    }

    @Test
    void validation_wrongQuantityProvided_failure() {
        storage.saveToStorage("banana", 20);

        assertThrows(WrongQuantityException.class, () ->
                Validator.validate(new Transaction(PURCHASE,
                        "banana",
                        21),
                        storage));
    }

    @Test
    void validation_emptyStorageProvided_failure() {

        assertThrows(WrongQuantityException.class, () ->
                Validator.validate(new Transaction(PURCHASE,
                                "banana",
                                20),
                        storage));
    }

    @Test
    void validation_zeroQuantityProvided_failure() {

        assertThrows(WrongQuantityException.class, () ->
                Validator.validate(new Transaction(BALANCE,
                                "banana",
                                0),
                        storage));
    }

    @Test
    void validation_rightQuantityProvided_success() throws WrongQuantityException {
        storage.saveToStorage("banana", 100);
        storage.saveToStorage("apple", 30);

        assertDoesNotThrow(() -> Validator
                .validate(new Transaction(PURCHASE,
                        "banana",
                        50),
                        storage));
        assertDoesNotThrow(() -> Validator
                .validate(new Transaction(PURCHASE,
                        "apple",
                        10),
                        storage));
    }
}
