package com.sun.java8.stream.bean;

/**
 * 菜肴基础类
 *
 * @Author Sun
 * @date 2019-02-27
 */
public class Dish {

    /**
     * 菜肴名称
     */
    private final String name;
    /**
     * 是否素菜
     */
    private final boolean vegetarian;
    /**
     * 热量
     */
    private final int calories;
    /**
     * 菜肴类型
     */
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

}
