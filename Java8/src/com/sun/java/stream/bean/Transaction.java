package stream.bean;

/**
 * 交易信息基础类
 *
 * @Author Sun
 * @date 2019-02-28
 */
public class Transaction {
    /**
     * 交易员
     */
    private final Trader trader;
    /**
     * 交易年份
     */
    private final int year;
    /**
     * 交易金额
     */
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}
