package com.sun.test;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 金额的计算BigDecimal  -- 测试类
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class BigDecimalTest {

    public static void main(String... args) {

        double d = 9.84;
        double d2 = 1.22;
        //注意需要使用BigDecimal(String val)构造方法
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(d2));

        //加法
        BigDecimal bigDecimalAdd = bigDecimal.add(bigDecimal2);
        double add = bigDecimalAdd.doubleValue();

        //减法
        BigDecimal bigDecimalSubtract = bigDecimal.subtract(bigDecimal2);
        double subtract = bigDecimalSubtract.doubleValue();

        //乘法
        BigDecimal bigDecimalMultiply = bigDecimal.multiply(bigDecimal2);
        double multiply = bigDecimalMultiply.doubleValue();

        //除法
        //保留2位小数
        int scale = 2;
        BigDecimal bigDecimalDivide = bigDecimal.divide(bigDecimal2, scale, BigDecimal.ROUND_HALF_UP);
        double divide = bigDecimalDivide.doubleValue();

        //格式化
        double format = 12343171.6;

        //获取常规数值格式
        NumberFormat number = NumberFormat.getNumberInstance();
        //12,343,171.6
        String str = number.format(format);

        //获取整数数值格式
        NumberFormat integer = NumberFormat.getIntegerInstance();
        //如果带小数会四舍五入到整数12,343,172
        str = integer.format(format);

        //获取货币数值格式
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        //设置数的小数部分所允许的最小位数(如果不足后面补0)
        currency.setMinimumFractionDigits(2);
        //设置数的小数部分所允许的最大位数(如果超过会四舍五入)
        currency.setMaximumFractionDigits(4);
        //￥12,343,171.60
        str = currency.format(format);

        //获取显示百分比的格式
        NumberFormat percent = NumberFormat.getPercentInstance();
        //设置数的小数部分所允许的最小位数(如果不足后面补0)
        percent.setMinimumFractionDigits(2);
        //设置数的小数部分所允许的最大位数(如果超过会四舍五入)
        percent.setMaximumFractionDigits(3);
        //1,234,317,160.00%
        str = percent.format(format);
    }

    /**
     * 1.如果你希望BigDecimal能够精确地表示你希望的数值，那么一定要使用字符串来表示小数，并传递给BigDecimal的构造函数。
     * 2.如果你使用Double.toString来把double转化字符串，然后调用BigDecimal(String)，这个也是不靠谱的，它不一定按你的想法工作。
     * 3.如果你不是很在乎是否完全精确地表示，并且使用了BigDecimal(double)，那么要注意double本身的特例，
     *   double的规范本身定义了几个特殊的double值(Infinite，-Infinite，NaN)，不要把这些值传给BigDecimal，否则会抛出异常。
     */
    public static void bigDecimal() {

        /*
         * 事实上，由于二进制无法精确地表示十进制小数0.1，但是编译器读到字符串"0.1"之后，必须把它转成8个字节的double值，
         * 因 此，编译器只能用一个最接近的值来代替0.1了，即 0.1000000000000000055511151231257827021181583404541015625。
         * 因此，在运行时，传给 BigDecimal构造函数的真正的数值是 0.1000000000000000055511151231257827021181583404541015625。
         *
         * 输出：0.1000000000000000055511151231257827021181583404541015625
         */
        System.out.println(new BigDecimal(0.1).toString());

        /*
         * BigDecimal能够正确地把字符串转化成真正精确的浮点数。
         *
         * 输出：0.1
         */
        System.out.println(new BigDecimal("0.1").toString());

        /*
         * 问题在于Double.toString会使用一定的精度来四舍五入double，然后再输出。
         * Double.toString(0.1000000000000000055511151231257827021181583404541015625) 输出的事实上是"0.1"，
         * 因此生成的BigDecimal表示的数也是0.1。
         *
         * 输出：0.1
         */
        System.out.println(new BigDecimal(Double.toString(0.1000000000000000055511151231257827021181583404541015625)).toString());

        /*
         * 基于前面的分析，事实上这一行代码等价于第三行
         *
         * 输出：0.1
         */
        System.out.println(new BigDecimal(Double.toString(0.1)).toString());

    }

}
