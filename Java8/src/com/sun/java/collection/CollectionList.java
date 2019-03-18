package collection;

import java.util.Arrays;
import java.util.List;

/**
 * Java 8 List集合新添加方法示例
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class CollectionList {

    /**
     * replaceAll           -- replaceAll方法会对列表中的每一个元素执行特定的操作，并用处理的结果替换该元素。
     * sort                 --
     */
    public static void main(String... args) {
        // 下面这段代码会打印输出[2,4,6,8,10]，因为列表中的元素被原地修改了
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.replaceAll(x -> x * 2);
        System.out.println(numbers);
    }

}
