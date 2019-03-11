package stream.stream;


import stream.bean.Dish;
import stream.data.StreamsData;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Stream.limit  -- 截短流，将流截取到指定长度
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamLimit {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.limit（截短流）");
        // 方法：limit  -- 参数：长度  -- 返回：一个不超过给定长度的流
        List<Dish> dishes = StreamsData.menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(toList());
        dishes.stream().forEach(System.out::println);
        System.out.println();
    }

}
