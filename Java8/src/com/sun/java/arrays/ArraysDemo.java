package arrays;

import java.util.Arrays;

/**
 * Java 8 Arrays新添加方法示例
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class ArraysDemo {

    /**
     * parallelSort：    -- parallelSort方法会以并发的方式对指定的数组进行排序，你可以使用自然顺序，也可以为数组对象定义特别的Comparator。
     * setAll：          -- 以顺序的方式，使用提供的函数计算每一个元素的值，对指定数组中的所有元素进行设置。该函数接受元素的索引，返回该索引元素对应的值。
     * parallelSetAll：  -- 以并发的方式，其余同上。  PS：由于需要并发执行，所以提供的函数必须没有任何副作用。
     * parallelPrefix：  -- parallelPrefix方法以并发的方式，使用用户提供的二进制操作符对给定数组中的每个元素进行累积计算。
     */
    public static void main(String... args) {

        // 使用setAll方法生成一个值为0, 2, 4, 6, …的数组
        int[] evenNumbers = new int[10];
        Arrays.setAll(evenNumbers, i -> i * 2);

        // 使用parallelPrefix并发地累积数组中的元素
        int[] ones = new int[10];
        Arrays.fill(ones, 1);
        // ones的内容是[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        Arrays.parallelPrefix(ones, (a, b) -> a + b);
    }

}
