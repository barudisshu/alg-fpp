package cn.galudisu.fp._5_2_interfaces_as_types;

public class ProcessIt {
    public static void moveGoods(GoodsMover goodsMover) {// 1
        System.out.println(goodsMover.name() + " busy moving heavy stuff");
    }

    public static void walk(Walks walker) { // 2
        System.out.println(walker.name() + " is having a stroll now");
    }
}