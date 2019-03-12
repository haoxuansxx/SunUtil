package apple;

/**
 * 函数式接口测试2
 *
 * @Author Sun
 * @date 2019-02-22
 */
public interface FunctionalInterfaceDemo1<T, U, V, R> {

    /**
     * 函数式接口唯一方法
     * @param var1 参数1
     * @param var2 参数2
     * @param var3 参数3
     * @return 返回1
     */
    R run(T var1, U var2, V var3);

}
