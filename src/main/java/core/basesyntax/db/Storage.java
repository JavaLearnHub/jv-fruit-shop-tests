package core.basesyntax.db;

import java.util.List;
import java.util.Optional;

public interface Storage {

    void saveToStorage(String product, int quantity);

    Optional<Integer> getQuantity(String product);

    List<String> getProductsNames();

    void clear();
}
