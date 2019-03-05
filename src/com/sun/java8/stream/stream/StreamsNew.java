package com.sun.java8.stream.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Streams流   -- 创建流Api
 *
 * @Author Sun
 * @date 2019-02-28
 */
public class StreamsNew {

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 由值创建流
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action ");
        stream.map(String::toUpperCase).forEach(System.out::println);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 创建一个空流
        stream = Stream.empty();
        stream.forEach(System.out::println);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 由数组创建流
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println("总和是：" + sum);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 由文件生成流
        /**
         * 使用Files.lines得到一个流，其中的每个元素都是给定文件中的一行，对line调用split方法将行拆分成单词。
         * 应该注意的是，你该如何使用flatMap产生一个扁平的单词流，而不是给每一行生成一个单词流。
         * 最后，把distinct和count方法链接起来，数数流中有多少各不相同的单词。
         *
         */
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("aaa.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
            System.out.println("有多少个不相同的单词：" + uniqueWords);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        System.out.println("--------------------- 我是分割线 ------------------------");
        // -- 由函数生成流：创建无限流
        // 方法：Stream.iterate（迭代）   -- 参数：一个初始值、还有一个依次应用在每个产生的新值上的Lambda（UnaryOperator<t>类型）
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // -- 由函数生成流：创建无限流
        // 方法：Stream.iterate   -- 参数：一个初始值、还有一个依次应用在每个产生的新值上的Lambda（UnaryOperator<t>类型）
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // -- 由函数生成流：创建无限流
        // 方法：Stream.generate   -- 参数：接收一个Supplier<t>类型的Lambda提供新的值
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

    }

}
