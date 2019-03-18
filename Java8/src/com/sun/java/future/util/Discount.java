package future.util;

import future.shop.bean.Quote;

import java.text.NumberFormat;

import static future.util.Delay.delay;

/**
 * 商品折扣类
 *
 * @Author Sun
 * @date 2019-03-16
 */
public class Discount {
    /**
     * 商品折扣枚举
     */
    public enum Code {
        /**
         * 普通会员
         */
        NONE(0),
        /**
         * 白银会员
         */
        SILVER(5),
        /**
         * 黄金会员
         */
        GOLD(10),
        /**
         * 白金会员
         */
        PLATINUM(15),
        /**
         * 钻石会员
         */
        DIAMOND(20);

        /**
         * 折扣百分比
         */
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    /**
     * 将折扣代码应用于商品最初的原始价格
     */
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode());
    }

    /**
     * 模 拟 Discount服务响应的延迟
     */
    private static double apply(double price, Code code) {
        delay();
        return (price * (100 - code.percentage) / 100);
    }
}