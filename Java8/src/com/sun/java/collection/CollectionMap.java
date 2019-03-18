package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Java 8 Map集合新添加方法示例
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class CollectionMap {

    /**
     * getOrDefault         -- 检测Map中是否包含给定键映射的方法。如果Map中不存在这样的键映射，你可以提供一个默认值，方法会返回该默认值。
     * forEach              --
     * compute              --
     * computeIfAbsent      --
     * computeIfPresent     --
     * merge                --
     * putIfAbsent          --
     * remove(key,value)    --
     * replace              --
     * replaceAll           --
     */
    public static void main(String... args) {

        Map<String, Integer> carInventory = new HashMap<>();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Map.getOrDefault");
        // 方法：getOrDefault  检测Map中是否包含给定键映射的方法
        Integer count = carInventory.getOrDefault("Aston Martin", 0);
        System.out.println("检测Map中是否包含给定键的映射：" + count);
        System.out.println();

    }

}
