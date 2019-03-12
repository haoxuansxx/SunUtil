package stream.parallel.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 分支/合并框架  -- 并行流使用的基础架构
 *
 * @Author Sun
 * @date 2019-03-08
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    /**
     * 方法的行为类似于普通的Iterator，它会按顺序一个一个使用Spliterator中的元素，并且如果还有其他元素就返回true。
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    /**
     * 是专为Spliterator接口设计的，因为它可以把一些元素划出去分给第二个Spliterator（由该方法返回），让它们两个并行处理。
     */
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));

                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    /**
     * Spliterator还可通过方法估计还剩下多少元素要遍历，因为即使不那么确切，能快速算出来是一个值。
     *
     * @return 剩余需要遍历的元素
     */
    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    /**
     * ORDERED      -- 元素有既定的顺序（例如List），因此Spliterator在遍历和划分时也会遵循这一顺序
     * DISTINCT     -- 对于任意一对遍历过的元素x和y，x.equals(y)返回false
     * SORTED       -- 遍历的元素按照一个预定义的顺序排序
     * SIZED        -- 该Spliterator由一个已知大小的源建立（例如Set），因此estimatedSize()返回的是准确值
     * NONNULL      -- 保证遍历的元素不会为null
     * IMMUTABLE    -- Spliterator的数据源不能修改。这意味着在遍历时不能添加、删除或修改任何元素
     * CONCURRENT   -- 该Spliterator的数据源可以被其他线程同时修改而无需同步
     * SUBSIZED     -- 该Spliterator和所有从它拆分出来的Spliterator都是SIZED
     *
     * @return 它将返回一个int，代表Spliterator本身特性集的编码。
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

}
