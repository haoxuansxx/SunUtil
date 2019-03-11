package transaction;

/**
 * 累计金额交易类
 *
 * @Author Sun
 * @date 2019-02-20
 */
public class Transaction {

    private final Currency currency;
    private final double value;

    public Transaction(Currency currency, double value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return currency + " " + value;
    }

}
