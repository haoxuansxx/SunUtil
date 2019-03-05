package com.sun.java8.stream;

import com.sun.java8.stream.bean.Dish;
import com.sun.java8.stream.bean.Type;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Streams流   -- API类
 *
 * @Author Sun
 * @date 2019-02-27
 */
public class StreamsUtil {

    protected static List<Dish> menu;

    static {
        StreamsUtil.menu = Arrays.asList(new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH));
    }

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.filter（谓词筛选）");
        // 方法：filter    -- 参数：接收一个谓词（一个返回boolean的函数）    -- 返回：一个符合所有谓词的元素
        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(toList());
        vegetarianMenu.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.distinct（筛选各异的元素）");
        // 方法：distinct  -- 返回：一个元素各异（根据流所生成元素的hashCode和equals方法实现）的流
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.limit（截短流）");
        // 方法：limit  -- 参数：长度  -- 返回：一个不超过给定长度的流
        List<Dish> dishes = menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(toList());
        dishes.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.skip（跳过元素）");
        // 方法：skip  -- 参数：扔掉长度  -- 返回：扔掉了前n个元素的流
        List<Dish> dishes1 = menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(toList());
        dishes1.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.map（映射）");
        // 方法：map  -- 参数：接收一个函数  -- 返回：根据参数返回确认返回数据
        List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());
        dishNames.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Arrays.stream");
        // 方法：Arrays.stream  -- 参数：接收一个数组  -- 返回：返回一个流
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        streamOfWords.forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.flatMap");
        // 方法：flatMap（把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流）  -- 参数：接收一个函数  -- 返回：返回一个合并后的流
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<String> uniqueCharacters = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(toList());
        uniqueCharacters.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.anyMatch");
        // 方法：anyMatch  流中是否有一个元素能匹配给定的谓词
        boolean bool = menu.stream().anyMatch(Dish::isVegetarian);
        System.out.println("菜单里面是否有素食：" + bool);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.allMatch");
        // 方法：allMatch  流中是否都能匹配给定的谓词
        boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println("菜品是否有利于健康：" + isHealthy);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.noneMatch");
        // 方法：noneMatch  确保流中所有元素都不能匹配给定的谓词
        isHealthy = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        System.out.println("菜品是否有利于健康：" + isHealthy);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.findAny");
        // 方法：findAny  返回当前流中的任意元素
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println("菜品中任意一个素食菜肴：" + dish);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.findFirst");
        // 方法：findFirst  返回当前流中的任意元素
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
        System.out.println("第一个平方能被3整除的数：" + firstSquareDivisibleByThree);
        System.out.println();

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
