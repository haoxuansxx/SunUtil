package com.sun.java8.stream.collectors;

import com.sun.java8.data.StreamsData;
import com.sun.java8.stream.bean.Dish;
import com.sun.java8.stream.bean.Type;

import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Collectors.collectingAndThen  -- 重载返回类型
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsCollectingAndThen {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.collectingAndThen");
        // 以类型分组查找每一组里面卡路里最高的菜肴
        Map<Type, Dish> mostCaloricByType = StreamsData.menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("以类型分组查找每一组里面卡路里最高的菜肴：" + mostCaloricByType);
        System.out.println();
    }

}
