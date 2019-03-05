package com.sun.java8.stream;

import com.sun.java8.stream.bean.Dish;
import com.sun.java8.stream.bean.Type;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sun.java8.stream.StreamsUtil.menu;
import static java.util.stream.Collectors.toList;

/**
 * Streams流   -- 测试类
 *
 * @Author Sun
 * @date 2019-02-27
 */
public class StreamsTest {

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 查询筛选两个荤菜
        List<Dish> dishes = menu.stream().filter(d -> d.getType() == Type.MEAT).limit(2).collect(toList());
        dishes.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 给定一个单词列表，返回另一个列表，显示每个单词中有几个字母
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
        wordLengths.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 获取每道菜的名称长度
        List<Integer> dishNameLengths = menu.stream().map(Dish::getName).map(String::length).collect(toList());
        dishNameLengths.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 给定一个数字列表，返回一个由每个数的平方构成的列表
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
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
        Integer menuNumbers = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
        long menuNumbers1 = menu.stream().count();
        System.out.println("菜单中总共有多少个菜：" + menuNumbers);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 斐波纳契元祖序列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(5).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 斐波纳契元祖序列
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(5).forEach(System.out::println);
        System.out.println();


    }
}
