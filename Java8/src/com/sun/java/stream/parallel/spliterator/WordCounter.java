package stream.parallel.spliterator;

/**
 * 单词计数器  -- 函数式风格基础类
 *
 * @Author Sun
 * @date 2019-03-11
 */
public class WordCounter {

    /**
     * 用来计算到目前为止数过的字数
     */
    private final int counter;
    /**
     * 用来记得上一个遇到的Character是不是空格
     */
    private final boolean lastSpace;

    /**
     * 用哪个状态来建立新的WordCounter，因为这个类是不可变的
     *
     * @param counter
     * @param lastSpace
     */
    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    /**
     * 每次遍历到Stream中的一个新的Character时，就会调用accumulate方法
     */
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            // 当上一个字符是空格，新字符不是空格时，计数器就加一
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    /**
     * 对作用于Character流的两个不同子部分的两个WordCounter的部分结果进行汇总，也就是把两个WordCounter内部的计数器加起来
     */
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter,
                wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}