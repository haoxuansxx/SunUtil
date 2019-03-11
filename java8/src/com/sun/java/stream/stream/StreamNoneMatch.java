package stream.stream;


import stream.data.StreamsData;

/**
 * Stream.noneMatch  -- 确保流中所有元素都不能匹配给定的谓词
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamNoneMatch {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.noneMatch");
        // 方法：noneMatch  确保流中所有元素都不能匹配给定的谓词
        boolean isHealthy = StreamsData.menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        System.out.println("菜品是否有利于健康：" + isHealthy);
        System.out.println();
    }

}
