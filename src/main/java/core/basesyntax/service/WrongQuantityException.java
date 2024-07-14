package core.basesyntax.service;

public class WrongQuantityException extends Exception {
    public WrongQuantityException(String errorMessage) {
        super(errorMessage);
    }
}
