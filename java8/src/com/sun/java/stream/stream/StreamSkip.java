package stream.stream;


import stream.bean.Dish;
import stream.data.StreamsData;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Stream.skip  -- 跳过元素
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamSkip {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.skip（跳过元素）");
        // 方法：skip  -- 参数：扔掉长度  -- 返回：扔掉了前n个元素的流
        List<Dish> dishes1 = StreamsData.menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(toList());
        dishes1.stream().forEach(System.out::println);
        System.out.println();
    }

}
