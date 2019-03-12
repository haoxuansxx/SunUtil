package stream.number;

import stream.data.StreamsData;
import transaction.Transaction;

/**
 * DoubleStream  -- 数值流
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class DoubleStream {

    public static void main(String... args) {
        System.out.println("--------------------- 我是分割线 ------------------------");
        System.out.println("方法：DoubleStream.sum");
        // 获取所有交易额总和  -- PS：建议使用这种方案
        Double transactionsSum = StreamsData.transactions.stream().mapToDouble(Transaction::getValue).sum();
        System.out.println("所有交易额总和：" + transactionsSum);
        System.out.println();
    }

}
