package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Java 8 Map集合新添加方法示例
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class CollectionMap {

    /**
     * getOrDefault         -- 检测Map中是否包含给定键映射的方法。如果Map中不存在这样的键映射，你可以提供一个默认值，方法会返回该默认值。
     * forEach              -- 对Map集合每个键值对进行特定的操作
     * compute              -- 设置与key相关联的值，如果计算出的值为null时则删除相应的key。
     * computeIfAbsent      -- 如果指定的key不存在或相关的value为null时，设置key与关联一个计算出的非null值。
     * computeIfPresent     -- 如果指定的key存在并且相关联的value不为null时根据旧的key和value计算newValue替换旧值，newValue为null则从map中删除该key;
     * merge                -- 如果指定的key不存在，或相应的值为null时，则设置value为相关联的值。否则根据key对应的旧值和value计算出新的值newValue，
     *                         newValue为null时，删除该key, 否则设置key对应的值为newValue。
     * putIfAbsent          -- 如果key不存在或相关联的值为null, 则设置新的 key/value 值。
     * remove(key,value)    -- key与value都匹配时才删除。
     * replace              -- 只要key存在，不管对应值是否为null则用传入的value替代原来的值。
     * replaceAll           -- 对于Map中的每一个元素应用函数function, 输入为key和value。
     */
    public static void main(String... args) {

        Map<String, Integer> carInventory = new HashMap<>(16);

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Map.getOrDefault");
        // 方法：getOrDefault  检测Map中是否包含给定键映射的方法
        Integer count = carInventory.getOrDefault("Aston Martin", 0);
        System.out.println("检测Map中是否包含给定键的映射：" + count);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        /* 计算出的值为null时直接删除key而不是设置对应key的值为null, 这能照顾到值不能为null的Map实现，如Hashtable和ConcurrentMap */
        System.out.println("方法：Map.computeIfPresent");
        Map<String, String> map = new HashMap<>(16);
        //ret null, map 为 {}
        String ret = map.computeIfPresent("a", (key, value) -> key + value);
        //map 为 ["a":null]
        map.put("a", null);
        //ret null, map 为 {"a":null}
        ret = map.computeIfPresent("a", (key, value) -> key + value);
        map.put("a", "+aaa");
        //ret "a+aaa", map 为 {"a":"a+aaa"}
        ret = map.computeIfPresent("a", (key, value) -> key + value);
        //ret 为 null, map 为 {}，计算出的 null 把 key 删除了

        ret = map.computeIfPresent("a", (key, value) -> null);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        /* 如果key不存在或相关联的值为null,则设置新的key/value值。 */
        System.out.println("方法：Map.putIfAbsent");
        //ret 为"aaa", map 为 {"a":"aaa"}
        ret = map.putIfAbsent("a", "aaa");
        //ret 为 "aaa", map 还是 {"a":"aaa"}
        ret = map.putIfAbsent("a", "bbb");
        map.put("b", null);
        //ret 为 "bbb", map 为 {"a":"aaa","b":"bbb"}
        ret = map.putIfAbsent("b", "bbb");
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        /*
           只要key存在，不管对应值是否为null，则用传入的value替代原来的值。即使传入的value是null也会用来替代原来的值，
           而不是删除，注意这对于value不能为null值的Map实现将会造成NullPointerException。key不存在不会修改 Map 的内容，
           返回值总是原始的map.get(key) 值。
        */
        System.out.println("方法：Map.replace");
        //ret 为 null，map 为 {}
        ret = map.replace("a", "abc");
        map.put("a", "ddd");
        //ret 为 "ddd", map 为 {"a":"abc"}
        ret = map.replace("a", "abc");
        //ret 为 "abc", map 为 {"a":null}
        ret = map.replace("a", null);
        //ret 为 null, map 为 {"a":"ddd"}
        ret = map.replace("a", "ddd");
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Map.replace（重载）");
        /* 当且仅当key存在，并且对应值与oldValue不相等，才用newValue作为key的新相关联值，返回值为是否进行了替换。 */
        //bool 为 false, map 为 {}
        Boolean bool = map.replace("a", null, "aaa");
        map.put("a", null);
        //bool 为 true, map 为 {"a":"aaa"}
        bool = map.replace("a", null, "aaa");
        //bool 为 true, map 为 {"a":null}
        bool = map.replace("a", "aaa", null);
        //ret 为 false, map 为 {"a":null}
        bool = map.replace("a", "aaa", "bbb");
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Map.compute");
        /*
            方法原型 V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction), 它是computeIfAbsent
            与computeIfPresent的结合体。也就是既不管key存不存在，也不管key对应的值是否为null, compute死活都要设置与key
            相关联的值，或者计算出的值为null时删除相应的key, 返回值为最终的map.get(key)。
        */
        // ret="anull", map={"a":"anull"}
        ret = map.compute("a", (key, value) -> "a" + value);
        // ret="aanull", map={"a":"aanull"}
        ret = map.compute("a", (key, value) -> "a" + value);
        // ret=null, map={}
        ret = map.compute("a", (key, value) -> null);
        System.out.println();

        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：Map.merge");
        /*
            方法原型 V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFucntion)，如果指定
            的key不存在，或相应的值为null时，则设置value为相关联的值。否则根据 key对应的旧值和value计算出新的值newValue，
            newValue为null时，删除该key, 否则设置key对应的值为newValue。方法的返回值也是最终的map.get(key)值。
        */
        //ret="aa", map={"a":"aa"}
        ret = map.merge("a", "aa", (oldValue, value) -> oldValue + "-" + value);
        //ret="aa-bb", map={"a":"aa-bb"}
        ret = map.merge("a", "bb", (oldValue, value) -> oldValue + "-" + value);
        //ret=null, map={}
        ret = map.merge("a", "bb", (oldValue, value) -> null);
        map.put("a", null);
        //ret="aa", map={"a":"aa"}
        ret = map.merge("a", "aa", (oldValue, value) -> oldValue + "-" + value);
        map.put("a", null);
        //ret="bb", map={"a":"bb"}
        ret = map.merge("a", "bb", (oldValue, value) -> null);
        //NullPointerException, value 不能为 null
        ret = map.merge("a", null, (oldValue, value) -> oldValue + "-" + value);
        System.out.println();


    }

}
