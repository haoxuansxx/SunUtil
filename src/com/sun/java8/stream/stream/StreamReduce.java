package com.sun.java8.stream.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Stream.reduce  -- 数据处理
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamReduce {

    public static void main(String... args) {

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.reduce");
        // 方法：reduce   -- 参数：一个初始值、一个BinaryOperator<T>函数
        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("数组所有元素相加：" + sum);
        Integer sum1 = numbers.stream().reduce(0, Integer::sum);
        System.out.println("数组所有元素相加：" + sum1);
        Integer product = numbers.stream().reduce(0, (a, b) -> a * b);
        System.out.println("数组所有元素相乘：" + product);


        // 重载：reduce   -- 参数：一个BinaryOperator<T>函数    -- 返回：一个Optional
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println("数组元素最大值：" + max.orElse(0));
        Optional<Integer> product1 = numbers.stream().reduce(Integer::min);
        System.out.println("数组元素最小值：" + product1.orElse(0));
        System.out.println();

    }

}
