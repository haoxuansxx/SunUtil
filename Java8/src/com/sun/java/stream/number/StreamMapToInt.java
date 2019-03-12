package stream.number;

import stream.bean.Dish;
import stream.data.StreamsData;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream.mapToInt  -- IntStream数值流
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamMapToInt {

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：IntStream.sum");
        // 菜单的所有菜肴卡路里求和
        int calories = StreamsData.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("所有菜肴卡路里之和：" + calories);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：mapToInt");
        // 方法：mapToInt  -- 将stream转换为IntStream
        IntStream intStream = StreamsData.menu.stream().mapToInt(Dish::getCalories);

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：boxed");
        // 方法：boxed -- 将数值流转换为stream
        Stream<Integer> stream = intStream.boxed();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：boxed");
        // 方法：max -- 获取数值流中最大值   -- 返回：OptionalInt
        OptionalInt maxCalories = StreamsData.menu.stream().mapToInt(Dish::getCalories).max();
        // -- orElse：如果没有值，则显示一个默认值
        int max = maxCalories.orElse(1);

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：rangeClosed");
        // 方法：rangeClosed -- 参数：数值1，数值2  -- 返回一个1到100的数值流（包含结束值）
        IntStream eventNumbers = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0);
        // -- 从1到100有50个偶数
        System.out.println(eventNumbers.count());

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：range");
        // 方法：range -- 参数：数值1，数值2  -- 返回：返回一个1到99的数值流（不包含结束值）
        IntStream eventNumbers1 = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0);
        // -- 从1到99有49个偶数
        System.out.println(eventNumbers1.count());

    }

}
