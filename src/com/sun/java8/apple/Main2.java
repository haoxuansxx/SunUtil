package com.sun.java8.apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * Apple类 测试程序入口2  -- Lambda 默认表达式接口
 *
 * @Author Sun
 * @date 2019-02-22
 */
public class Main2 {

    /**
     * 测试程序主入口
     */
    public static void main(String... args) {

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // Comparator<T> (T,T) -> int
        Comparator<String> comparator = (a, b) -> a.length() * b.length();
        System.out.println("Comparator compare :\t\t------------------" + comparator.compare("aaa", "bbb"));

        // Predicate<T> T -> boolean
        Predicate<Apple> predicate1 = (Apple a1) -> a1.getWeight() > 150;
        System.out.println("Predicate test :\t\t------------------" + predicate1.test(inventory.get(0)));
        // 谓词组合
        Predicate<Apple> predicate2 = predicate1.and(a -> "red".equals(a.getColor()));
        Predicate<Apple> predicate3 = predicate2.negate();
        System.out.println("Predicate negate test :\t\t-----------------" + predicate3.test(inventory.get(0)));

        // Consumer<T> T -> void
        Consumer<Apple> consumer1 = (Apple a1) -> System.out.println("Consumer test :\t\t-------------" + a1.toString());
        consumer1.accept(inventory.get(0));

        // Function<T,R> T -> R
        Function<Integer, Integer> function1 = x -> x + 1;
        Function<Integer, Integer> function2 = x -> x * 2;
        Function<Integer, Integer> function3 = function1.andThen(function2);
        Function<Integer, Integer> function4 = function1.compose(function2);
        System.out.println("Function andThen :\t\t------------------" + function3.apply(1));
        System.out.println("Function compose :\t\t------------------" + function4.apply(1));

        Function<Integer, Apple> function5 = Apple::new;
        System.out.println("Function apply :---------------------" + function5.apply(115).toString());

        // Supplier<T> () -> T
        Supplier<Apple> supplier = () -> new Apple(111, "green");
        System.out.println("Supplier get :\t\t------------------" + supplier.get().toString());

        Supplier<Apple> supplier1 = Apple::new;
        System.out.println("Supplier new get :---------------------" + supplier1.get().toString());

        // UnaryOperator<T> T -> T
        UnaryOperator<Apple> unaryOperator = (Apple a1) -> a1;
        System.out.println("UnaryOperator apply :\t\t-----------------" + unaryOperator.apply(inventory.get(0)).toString());

        // FunctionalInterfaceDemo <T, R> T -> R
        FunctionalInterfaceDemo<String, Integer> functionDemo = (String i) -> i.length();
        System.out.println("FunctionalInterfaceDemo run :\t\t----------------" + functionDemo.run("AAAAAAAAAAAAAAAAAAAAAAAAAAAA"));

        FunctionalInterfaceDemo1<String, String, Integer, Apple> functionalDemo1 = (type, color, weight) -> new Apple(type, color, weight);
        System.out.println("FunctionalInterfaceDemo1 run :\t\t----------------" + functionalDemo1.run("红苹果", "red", 180).toString());

        FunctionalInterfaceDemo1<String, String, Integer, Apple> functionalDemo2 = Apple::new;
        System.out.println("FunctionalInterfaceDemo1 run :\t\t----------------" + functionalDemo2.run("绿苹果", "green", 90).toString());

    }

}
