package stream.stream;

import stream.bean.Dish;
import stream.data.StreamsData;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Stream.peek  -- 调试Stream流
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class StreamPeek {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.peek（调试）");
        // 方法：peek  -- 调试Stream流
        List<Dish> dishes = StreamsData.menu
                .stream().peek(System.out::println)
                .filter(d -> d.getCalories() > 300).peek(System.out::println)
                .limit(3).peek(System.out::println)
                .collect(toList());
        dishes.stream().forEach(System.out::println);
        System.out.println();
    }

}
