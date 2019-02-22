package com.sun.java8.apple;

/**
 * 函数式接口测试1
 * @author Sun
 * @date 2019-02-22
 */
@FunctionalInterface
public interface FunctionalInterfaceDemo<T, R> {

    /**
     *  函数式接口唯一方法
     * @param var1 参数1
     * @return 返回1
     */
    R run(T var1);

}
