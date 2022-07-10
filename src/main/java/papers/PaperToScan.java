package papers;

public class PaperToScan {
    private final String data;
    private final int amount;

    public PaperToScan(String data, int amount) {
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
