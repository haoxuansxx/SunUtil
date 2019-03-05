package com.sun.java8.stream;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 斐波纳契元祖序列
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Fibonacci {

    public static void main(String... args) {

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
