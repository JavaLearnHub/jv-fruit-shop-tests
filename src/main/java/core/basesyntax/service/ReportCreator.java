package core.basesyntax.service;

import core.basesyntax.db.Storage;

public class ReportCreator {

    private static final String DATA_SEPARATOR = ",";
    private static final String FILE_HEADER = "fruit,quantity";

    public static String createReport(Storage storage) {

        StringBuilder storeActivitiesReport = new StringBuilder()
                .append(FILE_HEADER)
                .append(System.lineSeparator());

        storage.getProductsNames().forEach(product ->
                storeActivitiesReport
                        .append(product)
                        .append(DATA_SEPARATOR)
                        .append((storage.getQuantity(product)).orElse(0))
                        .append(System.lineSeparator()));

        return storeActivitiesReport.toString();
    }
}
