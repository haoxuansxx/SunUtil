package optional;

import java.util.Optional;

/**
 * Java Optional工具类
 *
 * @Author Sun
 * @date 2019-02-20
 */
public class JavaOptional {
    public static void main(String args[]) {

        /**
         * isPresent()：---------------------将在Optional包含值的时候返回true, 否则返回false。
         * ifPresent(Consumer<T> block) ：---会在值存在的时候执行给定的代码块。
         * T get()：-------------------------会在值存在时返回值，否则抛出一个NoSuchElement异常。
         * T orElse：(T other)---------------会在值存在时返回值，否则返回一个默认值。
         */

        JavaOptional java8Tester = new JavaOptional();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(java8Tester.sum(a, b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {

        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }
}

