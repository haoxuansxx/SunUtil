package com.sun.java8.stream.stream;

import com.sun.java8.stream.data.StreamsData;
import com.sun.java8.stream.bean.Dish;

/**
 * Stream.anyMatch  -- 流中是否有一个元素能匹配给定的谓词
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamAnyMatch {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.anyMatch");
        // 方法：anyMatch  流中是否有一个元素能匹配给定的谓词
        boolean bool = StreamsData.menu.stream().anyMatch(Dish::isVegetarian);
        System.out.println("菜单里面是否有素食：" + bool);
        System.out.println();
    }

}
