package stream.stream;

import stream.bean.Dish;
import stream.bean.Type;
import stream.data.StreamsData;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Stream.filter  -- 谓词筛选方法
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamFilter {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.filter（谓词筛选）");
        // 方法：filter    -- 参数：接收一个谓词（一个返回boolean的函数）    -- 返回：一个符合所有谓词的元素
        List<Dish> vegetarianMenu = StreamsData.menu.stream().filter(Dish::isVegetarian).collect(toList());
        vegetarianMenu.stream().forEach(System.out::println);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 查询筛选两个荤菜
        List<Dish> dishes = StreamsData.menu.stream().filter(d -> d.getType() == Type.MEAT).limit(2).collect(toList());
        dishes.stream().forEach(System.out::println);
        System.out.println();

    }

}
