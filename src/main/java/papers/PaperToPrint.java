package papers;

public class PaperToPrint {
    private final String data;
    private final int amount;

    public PaperToPrint(String data, int amount) {
        this.data = data;
        this.amount = amount;
    }

    public String getData() {
        return data;
    }

    public int getAmount() {
        return amount;
    }
}
