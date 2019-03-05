package com.sun.java8.stream.stream;

import com.sun.java8.data.StreamsData;
import com.sun.java8.stream.bean.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Stream.map  -- 映射
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamMap {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);


        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.map（映射）");
        // 方法：map  -- 参数：接收一个函数  -- 返回：根据参数返回确认返回数据
        List<String> dishNames = StreamsData.menu.stream().map(Dish::getName).collect(toList());
        dishNames.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 给定一个单词列表，返回另一个列表，显示每个单词中有几个字母
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
        wordLengths.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 获取每道菜的名称长度
        List<Integer> dishNameLengths = StreamsData.menu.stream().map(Dish::getName).map(String::length).collect(toList());
        dishNameLengths.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 给定一个数字列表，返回一个由每个数的平方构成的列表
        List<Integer> squares = numbers.stream().map(n -> n * n).collect(toList());
        squares.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(toList());
        pairs.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 扩展前一个例子，只返回总和能被3整除的数对。例如(2, 4)和(3, 3)是可以的
        List<int[]> pairs1 = numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(toList());
        pairs1.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 数一数菜单中总共有多少个菜
        Integer menuNumbers = StreamsData.menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
        long menuNumbers1 = StreamsData.menu.stream().count();
        System.out.println("菜单中总共有多少个菜：" + menuNumbers);
        System.out.println();
    }

}
