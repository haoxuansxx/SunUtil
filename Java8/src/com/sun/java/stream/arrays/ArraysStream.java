package stream.arrays;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Arrays.stream  -- 通过数组创建流
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class ArraysStream {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Arrays.stream");
        // 方法：Arrays.stream  -- 参数：接收一个数组  -- 返回：返回一个流
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        streamOfWords.forEach(System.out::println);
        System.out.println();
    }

}
