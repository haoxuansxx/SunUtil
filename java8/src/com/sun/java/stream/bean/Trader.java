package stream.bean;

/**
 * 交易员基础类
 *
 * @Author Sun
 * @date 2019-02-28
 */
public class Trader {

    /**
     * 交易员姓名
     */
    private final String name;
    /**
     * 交易员所在城市
     */
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    @Override
    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}