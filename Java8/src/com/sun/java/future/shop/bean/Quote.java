package future.shop.bean;

import future.util.Discount;

/**
 * 解析商品价格类
 *
 * @Author Sun
 * @date 2019-03-16
 */
public class Quote {

    /**
     * 商品名称
     */
    private final String shopName;
    /**
     * 商品价格
     */
    private final double price;
    /**
     * 商品折扣
     */
    private final Discount.Code discountCode;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = code;
    }

    /**
     * 解析商品价格数据
     * @param s 商品字符串
     */
    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}
