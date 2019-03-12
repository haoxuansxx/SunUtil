package stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * IntStreams流   -- 找出1到100之间所有的勾股数
 *
 * @Author Sun
 * @date 2019-02-28
 */
public class IntStreamsPythagoreanTriples {

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 1. 找出1-100之间所有的勾股数 勾股数定义：a*a + b*b = c*c
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 1. 找出1-100之间所有的勾股数 a*a + b*b = c*c
        Stream<Double[]> pythagoreanTriples1 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new Double[]{(double) a, (double) b, Math.sqrt(a * a + b * b)})
                ).filter(t -> t[2] % 1 == 0);
        pythagoreanTriples1.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }

}
