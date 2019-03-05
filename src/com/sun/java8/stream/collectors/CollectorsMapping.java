package com.sun.java8.stream.collectors;

import com.sun.java8.data.StreamsData;
import com.sun.java8.stream.bean.CaloricLevel;
import com.sun.java8.stream.bean.Dish;
import com.sun.java8.stream.bean.Type;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * Collectors.mapping  -- 分类
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class CollectorsMapping {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Collectors.mapping");
        // 以类型分组并对每一组里面的菜肴以卡路里进行分类
        Map<Type, Set<CaloricLevel>> caloricLevelsByType = StreamsData.menu.stream().collect(groupingBy(Dish::getType,mapping(
                dish -> {
                    if(dish.getCalories() <= 402){
                        return CaloricLevel.DIET;
                    }else if(dish.getCalories() <= 700){
                        return CaloricLevel.NORMAL;
                    }else{
                        return CaloricLevel.FAT;
                    }
                }
                , toCollection(HashSet::new))));
        System.out.println(caloricLevelsByType);
        System.out.println();
    }

}
