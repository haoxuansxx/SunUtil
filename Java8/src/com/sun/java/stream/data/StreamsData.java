package stream.data;

import stream.bean.Dish;
import stream.bean.Type;
import transaction.Currency;
import transaction.Transaction;

import java.util.Arrays;
import java.util.List;

/**
 * Streams流   -- 原始数据类
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamsData {

    /**
     * 菜单数据
     */
    public static List<Dish> menu;

    /**
     * 交易数据
     */
    public static List<Transaction> transactions;

    static {
        StreamsData.menu = Arrays.asList(new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH));

        StreamsData.transactions = Arrays.asList(new Transaction(Currency.EUR, 1500.0),
                new Transaction(Currency.USD, 2300.0),
                new Transaction(Currency.GBP, 9900.0),
                new Transaction(Currency.EUR, 1100.0),
                new Transaction(Currency.JPY, 7800.0),
                new Transaction(Currency.CHF, 6700.0),
                new Transaction(Currency.EUR, 5600.0),
                new Transaction(Currency.USD, 4500.0),
                new Transaction(Currency.CHF, 3400.0),
                new Transaction(Currency.GBP, 3200.0),
                new Transaction(Currency.USD, 4600.0),
                new Transaction(Currency.JPY, 5700.0),
                new Transaction(Currency.EUR, 6800.0));
    }

}
