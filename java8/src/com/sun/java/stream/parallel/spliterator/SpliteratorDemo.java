package stream.parallel.spliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Spliterator  -- 可分迭代器Api
 * <p>
 * Spliterator是Java 8中加入的另一个新接口；这个名字代表“可分迭代器”（splitable iterator）。
 * 和Iterator一样，Spliterator也用于遍历数据源中的元素，但它是为了并行执行而设计的。
 *
 * @Author Sun
 * @date 2019-03-11
 */
public class SpliteratorDemo {

    /**
     * public interface Spliterator<T> {
     * boolean tryAdvance(Consumer<? super T> action);
     * Spliterator<T> trySplit();
     * long estimateSize();
     * int characteristics();
     * }
     * T：          -- Spliterator遍历的元素的类型。
     * tryAdvance：   -- 方法的行为类似于普通的Iterator，因为它会按顺序一个一个使用Spliterator中的元素，并且如果还有其他元素要遍历就返回true。
     * trySplit：     -- 是专为Spliterator接口设计的，因为它可以把一些元素划出去分给第二个Spliterator（由该方法返回），让它们两个并行处理。
     * estimateSize： -- Spliterator还可通过方法估计还剩下多少元素要遍历，因为即使不那么确切，能快速算出来一个值。
     */

    public static void main(String... args) {
        // 但丁的《神曲》的《地狱篇》第一句话
        final String SENTENCE = " Nel   mezzo del cammin  di nostra  vita mi  ritrovai in una  selva oscura ché la  dritta via era   smarrita ";

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 输出迭代器版本单词计数器结果
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 输出函数式风格单词计数器结果
        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(stream) + " words");

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 输出函数式风格并行处理单词计数器结果
        // 使用了自定义的Spliterator，因为并行时字符串拆分位置不对，有可能会对一个单词统计两次
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream1 = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream1.parallel()) + " words");

    }

    /**
     * 数一数给定String中的单词数    -- 函数式风格单词计数器
     */
    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    /**
     * 数一数给定String中的单词数    -- 迭代器版本单词计数器
     */
    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;

        // 逐个遍历String中的所有字符
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                // 上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加一
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

}
