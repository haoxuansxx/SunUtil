package collection;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class ConcurrentHashMapDemo {

    /**
     * ConcurrentHashMap类的引入极大地提升了HashMap现代化的程度，新引入的ConcurrentHashMap对并发的支持非常友好。
     * ConcurrentHashMap允许并发地进行新增和更新操作，因为它仅对内部数据结构的某些部分上锁。
     * 因此，和另一种选择，即同步式的Hashtable比较起来，它具有更高的读写性能。
     *
     * forEach：         -- 对每个键值对进行特定的操作
     * reduce：          -- 使用给定的精简函数（reduction function），将所有的键值对整合出一个结果
     * search：          -- 对每一个键值对执行一个函数，直到函数的返回值为一个非空值
     *
     * 以上每一种操作都支持四种形式，接受使用键、值、Map.Entry以及键值对的函数：
     *      1. 使用键和值的操作（forEach、reduce、search）
     *      2. 使用键的操作（forEachKey、reduceKeys、searchKeys）
     *      3. 使用值的操作 （forEachValue、reduceValues、searchValues）
     *      4. 使用Map.Entry对象的操作（forEachEntry、reduceEntries、searchEntries）
     *
     * mappingCount：    -- 它以长整型long返回map中映射的数目。
     * KeySet：          -- 该方法以Set的形式返回ConcurrentHashMap的一个视图（对map的修改会反映在该Set中，反之亦然）。
     * newKeySet：       -- 由ConcurrentHashMap创建一个Set。
     *
     * 注意，对int、long和double，它们的reduce操作各有不同（比如reduceValuesToInt、reduceKeysToLong等）。
     */
    public static void main(String... args) {
        // 使用reduceValues试图找出map中的最大值
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        // 使用值1开启基于通用线程池的最大并行
        Optional<Integer> maxValue = Optional.of(map.reduceValues(1, Integer::max));
        System.out.println("使用reduceValues试图找出map中的最大值：" + maxValue);

    }

}
