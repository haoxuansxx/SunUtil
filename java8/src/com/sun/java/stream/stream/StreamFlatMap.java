package stream.stream;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Stream.flatMap  -- 把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class StreamFlatMap {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Stream.flatMap");
        // 方法：flatMap（把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流）  -- 参数：接收一个函数  -- 返回：返回一个合并后的流
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<String> uniqueCharacters = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(toList());
        uniqueCharacters.stream().forEach(System.out::println);
        System.out.println();

    }

}
