package number;

/**
 * Java 8 数字基础类型新添加方法示例
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class NumberDemo {

    /**
     * 1. Short、Integer、Long、Float和Double类提供了静态方法sum、min和max。
     * 2. Integer和Long类提供了compareUnsigned、divideUnsigned、remainderUnsigned和toUnsignedLong方法来处理无符号数。
     * 3. Integer和Long类也分别提供了静态方法parseUnsignedInt和parseUnsignedLong将字符解析为无符号int或者long类型。
     * 4. Byte和Short类提供了toUnsignedInt和toUnsignedLong方法通过无符号转换将参数转化为int或者long类型。
     * 5. 类似地，Integer类现在也提供了静态方法toUnsignedLong。
     * 6. Double和Float类提供了静态方法isFinite，可以检查参数是否为有限浮点数。
     * 7. Boolean类现在提供了静态方法logicalAnd、logicalOr和logicalXor，可以在两个boolean之间执行and、or和xor操作。
     * 8. BigInteger类提供了byteValueExact、shortValueExact、intValueExact和longValueExact，可以将BigInteger类型的值转换为对应的基础类型。
     */
    public static void main(String... args){
        System.out.println("Java 8 Number数字基础类型新添加方法示例！");
    }

}
