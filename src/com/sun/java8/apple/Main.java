package com.sun.java8.apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Apple类 测试程序入口1  -- Lambda 基础用法
 *
 * @Author Sun
 * @date 2019-02-20
 */
public class Main {

    /**
     * 测试程序主入口
     */
    public static void main(String ... args){

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples2);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);

        // []
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));
        System.out.println(weirdApples);

    }

    /**
     * 以函数式传参输出苹果相关属性
     * @param inventory
     * @param p
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }


}
