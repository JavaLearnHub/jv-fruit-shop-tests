package core.basesyntax.model;

public enum OperationEnum {
    BALANCE("b"),
    SUPPLY("s"),
    PURCHASE("p"),
    RETURN("r");

    private final String code;

    OperationEnum(String code) {

        this.code = code;
    }

    public static OperationEnum getOperation(String code) {
        for (OperationEnum op : OperationEnum.values()) {
            if (op.code.equals(code)) {
                return op;
            }
        }
        throw new UnsupportedOperationException();
    }

}
