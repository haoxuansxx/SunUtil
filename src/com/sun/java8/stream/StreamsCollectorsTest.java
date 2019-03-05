package com.sun.java8.stream;

import com.sun.java8.stream.bean.CaloricLevel;
import com.sun.java8.stream.bean.Dish;
import com.sun.java8.stream.bean.Type;

import java.util.*;

import static com.sun.java8.stream.StreamsUtil.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Streams流   -- Collectors测试类
 *
 * @Author Sun
 * @date 2019-03-01
 */
public class StreamsCollectorsTest {

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 将菜肴以不同的卡路里进行分类
        System.out.println("将菜肴以不同的卡路里进行分类");
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
            if(dish.getCalories() <= 400){
                return CaloricLevel.DIET;
            }else if(dish.getCalories() <= 700){
                return CaloricLevel.NORMAL;
            }else{
                return CaloricLevel.FAT;
            }
        }));
        System.out.println(dishesByCaloricLevel);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 将菜肴以类型分组并以不同卡路里进行分类
        System.out.println("将菜肴以不同的卡路里进行分类");
        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if(dish.getCalories() <= 401){
                        return CaloricLevel.DIET;
                    }else if(dish.getCalories() <= 700){
                        return CaloricLevel.NORMAL;
                    }else{
                        return CaloricLevel.FAT;
                    }
                }
        )));
        System.out.println(dishesByTypeCaloricLevel);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 数一数菜单中每类菜有多少个
        System.out.println("将菜肴以不同的卡路里进行分类");
        Map<Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 以类型分组查找每一组里面卡路里最高的菜肴
        System.out.println("以类型分组查找每一组里面卡路里最高的菜肴");
        Map<Type, Optional<Dish>> mostCaloricByType = menu.stream().collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 以类型分组汇总每一组里面卡路里总和
        System.out.println("以类型分组汇总每一组里面卡路里总和");
        Map<Type, Integer> totalCaloriesByType = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 素食和非素食中热量最高的菜肴
        System.out.println("素食和非素食中热量最高的菜肴");
        Map<Boolean, Dish> vegetarianDishesByType = menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("素食和非素食中热量最高的菜肴：" + vegetarianDishesByType);
        System.out.println();

    }

}
