package optional;

import java.util.Optional;

/**
 * Java Optional示例类
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class JavaOptional {
    public static void main(String args[]) {

        /**
         * empty：----------- 返回一个空的 Optional 实例
         * flatMap：--------- 如果值存在，就对该值执行提供的 mapping 函数调用，返回一个 Optional 类型的值，否则就返回一个空的 Optional 对象
         * get：------------- 会在值存在时返回值，否则抛出一个NoSuchElement异常。
         * ifPresent：------- 会在值存在的时候执行给定的代码块。
         * isPresent：------- 将在Optional包含值的时候返回true, 否则返回false。
         * map：------------- 如果值存在，就对该值执行提供的 mapping 函数调用。
         * of：-------------- 将指定值用 Optional 封装之后返回，如果该值为 null，则抛出一个 NullPointerException异常
         * ofNullable：------ 将指定值用 Optional 封装之后返回，如果该值为 null，则返回一个空的 Optional 对象
         * orElse：---------- 如果有值则将其返回，否则返回一个默认值
         * orElseGet：------- 如果有值则将其返回，否则返回一个由指定的 Supplier 接口生成的值
         * orElseThrow：----- 如果有值则将其返回，否则抛出一个由指定的 Supplier 接口生成的异常
         */

        Integer value1 = null;
        Integer value2 = new Integer(10);

        System.out.println("-------------------- Optional.ofNullable - 允许传递为 null 参数 --------------------");
        Optional<Integer> a = Optional.ofNullable(value1);

        System.out.println("-------------------- Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException --------------------");
        Optional<Integer> b = Optional.of(value2);

        System.out.println("-------------------- Optional.isPresent - 判断值是否存在 --------------------");
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        System.out.println("-------------------- Optional.orElse - 如果值存在，返回它，否则返回默认值 --------------------");
        value1 = a.orElse(new Integer(0));

        System.out.println("-------------------- Optional.get - 获取值，值需要存在 --------------------");
        value2 = b.get();


    }

}

