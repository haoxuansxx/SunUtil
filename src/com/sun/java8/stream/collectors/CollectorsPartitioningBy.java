package com.sun.java8.stream.collectors;

import com.sun.java8.data.StreamsData;
import com.sun.java8.stream.bean.Dish;
import com.sun.java8.stream.bean.Type;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Collectors.partitioningBy  -- 分区
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsPartitioningBy {

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.partitioningBy");
        // 以素食和非素食分区菜肴
        Map<Boolean, List<Dish>> partitionedMenu = StreamsData.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("以素食和非素食分区菜肴：" + partitionedMenu);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 素食和非素食中热量最高的菜肴
        System.out.println("素食和非素食中热量最高的菜肴");
        Map<Boolean, Dish> vegetarianDishesByType = StreamsData.menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("素食和非素食中热量最高的菜肴：" + vegetarianDishesByType);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.partitioningBy");
        // 以素食和非素食分区菜肴并以类型进行分组
        Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType1 = StreamsData.menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println("以素食和非素食分区菜肴并以类型进行分组：" + vegetarianDishesByType1);
        System.out.println();

    }

}
