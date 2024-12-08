public enum BoardStatusType {
    UNKNOWN('.'),
    MISSED('O'),
    HIT('*'),
    KILLED('X');

    private final char symbol;

    BoardStatusType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}