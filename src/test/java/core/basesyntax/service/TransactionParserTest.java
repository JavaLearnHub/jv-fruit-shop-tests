package core.basesyntax.service;

import core.basesyntax.dto.Transaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static core.basesyntax.model.OperationEnum.BALANCE;
import static core.basesyntax.model.OperationEnum.SUPPLY;
import static org.junit.jupiter.api.Assertions.*;

class TransactionParserTest {

    @Test
    void getTransactionsList_wrongSeparator_failure() {
        List<String> storeActivities = new ArrayList<>();
        storeActivities.add("b/banana/20");
        assertThrows(IllegalArgumentException.class, ()->TransactionParser.parseToTransaction(storeActivities));
    }

    @Test
    void getTransactionsList_wrongElementProvided_failure() {
        List<String> storeActivities = new ArrayList<>();
        storeActivities.add("b,20,banana");
        assertThrows(IllegalArgumentException.class, ()->TransactionParser.parseToTransaction(storeActivities));
    }

    @Test
    void getTransactionsList_emptyList() {
        List<String> storeActivities = new ArrayList<>();
        List<Transaction> actual = TransactionParser.parseToTransaction(storeActivities);
        List<Transaction> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void getTransactionsList_notFullElementProvided_failure() {
        List<String> storeActivities = new ArrayList<>();
        storeActivities.add("b,20,");
        assertThrows(IllegalArgumentException.class, ()->TransactionParser.parseToTransaction(storeActivities));
    }

    @Test
    void getTransactionsList_inCorrectElementProvided_failure() {
        List<String> storeActivities = new ArrayList<>();
        storeActivities.add("348576402765");
        assertThrows(IllegalArgumentException.class, ()->TransactionParser.parseToTransaction(storeActivities));
    }

    @Test
    void getTransactionsList_rightDataProvided() {
        List<String> storeActivities = new ArrayList<>();
        storeActivities.add("b,banana,20");
        storeActivities.add("s,apple,100");

        List<Transaction> actualList = TransactionParser.parseToTransaction(storeActivities);
        Transaction firstActualTransaction = actualList.get(0);
        Transaction secondActualTransaction = actualList.get(1);

        assertEquals(BALANCE, firstActualTransaction.getOperation());
        assertEquals("banana", firstActualTransaction.getProduct());
        assertEquals(20, firstActualTransaction.getQuantity());

        assertEquals(SUPPLY, secondActualTransaction.getOperation());
        assertEquals("apple", secondActualTransaction.getProduct());
        assertEquals(100, secondActualTransaction.getQuantity());
    }
}