package core.basesyntax.service;

import core.basesyntax.dto.Transaction;
import core.basesyntax.model.OperationEnum;
import lombok.val;

import java.util.List;

public class TransactionParser {

    private static final String DATA_SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int PRODUCT_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;
    private static final int EXPECTED_ACTIVITY_LENGTH = 3;


    public static List<Transaction> parseToTransaction(List<String> storeActivities) {

        return storeActivities.stream()
                .map(TransactionParser::splitActivity)
                .map(TransactionParser::createTransaction)
                .toList();
    }

    private static String[] splitActivity(String activity) {

        val splitActivityArray = activity.strip().split(DATA_SEPARATOR);
        if (splitActivityArray.length != EXPECTED_ACTIVITY_LENGTH) {
            throw new IllegalArgumentException("Wrong file format at this line: "
                    + activity);
        }
        return splitActivityArray;
    }

    private static Transaction createTransaction(String[] transactionInfo) {

        return new Transaction(OperationEnum
                .getOperation(transactionInfo[OPERATION_INDEX]),
                transactionInfo[PRODUCT_INDEX],
                Integer.parseInt(transactionInfo[QUANTITY_INDEX]));
    }
}
