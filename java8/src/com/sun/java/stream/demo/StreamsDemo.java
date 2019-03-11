package stream.demo;

import stream.bean.Trader;
import stream.bean.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Streams流   -- 测试类
 *
 * @Author Sun
 * @date 2019-02-28
 */
public class StreamsDemo {

    protected static List<Transaction> transactions;

    static {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        StreamsDemo.transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    public static void main(String... args) {

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 1. 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> list = transactions.stream().filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue)).collect(toList());
        list.stream().forEach(System.out::println);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 2. 交易员都在哪些不同的城市工作过？
        List<String> traderCitys = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
        traderCitys.stream().forEach(System.out::println);
        Set<String> traderCitys1 = transactions.stream().map(t -> t.getTrader().getCity()).collect(toSet());
        traderCitys1.stream().forEach(System.out::println);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 3. 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> list2 = transactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge")).distinct()
                .sorted(comparing(Trader::getName)).collect(toList());
        list2.stream().forEach(System.out::println);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 4. 返回所有交易员的姓名字符串，按字母顺序排序。
        String traderStr = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("", (n1, n2) -> n1 + "\t" + n2);
        System.out.println(traderStr);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 5. 有没有交易员是在米兰工作的？
        boolean milanBased = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println("有没有交易员是在米兰工作的：" + milanBased);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 6. 打印生活在剑桥的交易员的所有交易额。
        transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity())).map(Transaction::getValue).forEach(System.out::println);

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 7. 所有交易中，最高的交易额是多少？
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println("最大的交易额是：" + max.orElse(0));

        System.out.println("--------------------- 我是分割线 ------------------------");
        // 8. 找到交易额最小的交易。
        Optional<Transaction> min = transactions.stream().min(comparing(Transaction::getValue));
        System.out.println("最小的交易额是：" + min.orElse(new Transaction(new Trader("Raoul", "Cambridge"), 1900, 0)).getValue());

    }

}
