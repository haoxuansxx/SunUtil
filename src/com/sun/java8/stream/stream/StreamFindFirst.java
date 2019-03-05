package com.sun.java8.stream.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Stream.findFirst  -- 返回当前流中的任意元素
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamFindFirst {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.findFirst");
        // 方法：findFirst  返回当前流中的任意元素
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
        System.out.println("第一个平方能被3整除的数：" + firstSquareDivisibleByThree);
        System.out.println();
    }

}
