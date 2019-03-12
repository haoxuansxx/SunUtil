package stream.collectors;

import stream.bean.CaloricLevel;
import stream.bean.Dish;
import stream.bean.Type;
import stream.data.StreamsData;
import transaction.Currency;
import transaction.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Collectors.groupingBy  -- 分类
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsGroupingBy {

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.groupingBy");
        // 对交易按照货币分组
        Map<Currency, List<Transaction>> transactionByCurrencies = StreamsData.transactions.stream().collect(groupingBy(Transaction::getCurrency));
        transactionByCurrencies.forEach((key, value) -> {
            System.out.println("分组类型：" + key);
            value.stream().forEach(o -> System.out.println(o.toString()));
        });
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 将菜肴以不同的卡路里进行分类
        System.out.println("将菜肴以不同的卡路里进行分类");
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = StreamsData.menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));
        System.out.println(dishesByCaloricLevel);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 将菜肴以类型分组并以不同卡路里进行分类
        System.out.println("将菜肴以不同的卡路里进行分类");
        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = StreamsData.menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 401) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }
        )));
        System.out.println(dishesByTypeCaloricLevel);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 数一数菜单中每类菜有多少个
        System.out.println("将菜肴以不同的卡路里进行分类");
        Map<Type, Long> typesCount = StreamsData.menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 以类型分组查找每一组里面卡路里最高的菜肴
        System.out.println("以类型分组查找每一组里面卡路里最高的菜肴");
        Map<Type, Optional<Dish>> mostCaloricByType = StreamsData.menu.stream().collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 以类型分组汇总每一组里面卡路里总和
        System.out.println("以类型分组汇总每一组里面卡路里总和");
        Map<Type, Integer> totalCaloriesByType = StreamsData.menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);
        System.out.println();
    }

}
