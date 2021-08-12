package stream;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * @author lei
 * @version 1.0
 * @date 2021/7/31 16:30
 * @desc
 */
public class TransactionMain {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = new ArrayList<Transaction>() {{
            add(new Transaction(brian, 2011, 300));
            add(new Transaction(raoul, 2012, 1000));
            add(new Transaction(raoul, 2011, 400));
            add(new Transaction(mario, 2012, 710));
            add(new Transaction(mario, 2012, 700));
            add(new Transaction(alan, 2012, 950));
        }};

        //(1) 找出2011年发生的所有交易，并按交易额排序（从高到低）。
        System.out.println("(1) 找出2011年发生的所有交易，并按交易额排序（从高到低）");
        System.out.println(transactions.stream()
                .filter(tran -> tran.getYear() == 2011)
                .sorted(comparing(Transaction::getValue).reversed())
                .collect(toList()));

        //(2) 交易员都在哪些不同的城市工作过？
        System.out.println("(2)  交易员都在哪些不同的城市工作过？");
        System.out.println(transactions.stream().map(tran -> tran.getTrader().getCity()).collect(toSet()));

        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        System.out.println("(3) 查找所有来自于剑桥的交易员，并按姓名排序。");
        System.out.println(transactions.stream().filter(tran -> "Cambridge".equals(tran.getTrader().getCity()))
                .sorted(comparing(tran -> tran.getTrader().getName()))
                .collect(toList()));

        //(4) 查找所有交易员，并将名字正序排列，名字相同的交易员根据交易倒序排列
        System.out.println("(4) 查找所有交易员，并将名字正序排列，名字相同的交易员根据交易倒序排列");
        System.out.println(transactions.stream()
                .sorted(comparing((Transaction tran) -> tran.getTrader().getName())
                        .thenComparing(Transaction::getValue, reverseOrder()))
                .collect(toList()));

        //(5) 有没有交易员是在米兰工作的？
        System.out.println("(5) 有没有交易员是在米兰工作的？");
        System.out.println(transactions.stream().filter(tran -> "Milan".equals(tran.getTrader().getCity())).collect(toList()));

        //(6) 打印生活在剑桥的交易员的所有交易额。
        System.out.println("(6) 打印生活在剑桥的交易员的所有交易额。");
        System.out.println(transactions.stream().filter(tran -> "Cambridge".equals(tran.getTrader().getCity()))
                .map(Transaction::getValue).reduce(0, Integer::sum));

        System.out.println(transactions.stream().filter(tran -> "Cambridge".equals(tran.getTrader().getCity()))
                .reduce(0, (total, t) -> total += t.getValue(), Integer::sum));

        //(7) 所有交易中，最高的交易额是多少？
        System.out.println("(7) 所有交易中，最高的交易额是多少？");
        System.out.println(transactions.stream().map(Transaction::getValue).max(Integer::compareTo));

        //(8) 找到交易额最小的交易信息。
        System.out.println("(8) 找到交易额最小的交易信息。");
        System.out.println(transactions.stream().min(comparing(Transaction::getValue)));

        //(9) 根据交易额倒序排列,获取前两个交易信息
        System.out.println("(9) 根据交易额倒序排列,获取前两个交易信息");
        System.out.println(transactions.stream().sorted(comparing(Transaction::getValue, reverseOrder())).limit(2).collect(toList()));

        //(10) 求当前所有交易总额
        System.out.println("(10) 求当前所有交易总额");
        System.out.println(transactions.stream().map(Transaction::getValue).mapToInt(Integer::intValue).sum());

        //(11) 一次性求出最大 最小 平均 总和交易额
        System.out.println("(11) 一次性求出最大 最小 平均 总和交易额");
        System.out.println(transactions.stream().collect(summarizingInt(Transaction::getValue)));

        //(12) 获取所有操作员的名字列表
        System.out.println("(12) 获取所有操作员的名字列表");
        System.out.println(transactions.stream().map(tran -> tran.getTrader().getName()).collect(toSet()));

        //(13) 求出每个城市交易数量
        System.out.println("(13) 求出每个城市交易数量");
        System.out.println(transactions.stream()
                .collect(groupingBy((tran -> tran.getTrader().getCity()), counting())));

        //(14) 求出每个城市最大交易
        System.out.println("(14) 求出每个城市最大交易");
        System.out.println(transactions.stream()
                .collect(groupingBy((Transaction tran) -> tran.getTrader().getCity(),
                        maxBy(comparing(Transaction::getValue)))));

        //(15) 求出每个城市最小交易
        System.out.println("(15) 求出每个城市最小交易");
        System.out.println(transactions.stream()
                .collect(groupingBy((Transaction tran) -> tran.getTrader().getCity(),
                        minBy(comparing(Transaction::getValue)))));

        //(16) 求出每个城市总交易额
        System.out.println("(16) 求出每个城市总交易额");
        System.out.println(transactions.stream()
                .collect(groupingBy(tran -> tran.getTrader().getCity(),
                        summingInt(Transaction::getValue))));

        //(17) 按每个城市每个用户分组
        System.out.println("(17) 按每个城市每个用户分组");
        System.out.println(transactions.stream().collect(groupingBy(tran -> tran.getTrader().getCity(),
                groupingBy(t -> t.getTrader().getName()))));

        //(18) 求出每个城市完成了最大交易额操作员的名字
        System.out.println("(18) 求出每个城市完成了最大交易额操作员的名字");
        System.out.println(transactions.stream().collect(groupingBy(tran -> tran.getTrader().getCity(),
                collectingAndThen(maxBy(comparing(Transaction::getValue)),
                        tr -> tr.map(Transaction::getTrader).map(Trader::getName).orElse("kk")))));
    }
}
