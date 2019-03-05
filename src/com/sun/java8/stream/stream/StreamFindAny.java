package com.sun.java8.stream.stream;

import com.sun.java8.stream.data.StreamsData;
import com.sun.java8.stream.bean.Dish;

import java.util.Optional;

/**
 * Stream.findAny  -- 返回当前流中的任意一个元素
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamFindAny {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.findAny");
        // 方法：findAny  返回当前流中的任意一个元素
        Optional<Dish> dish = StreamsData.menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println("菜品中任意一个素食菜肴：" + dish);
        System.out.println();
    }

}
