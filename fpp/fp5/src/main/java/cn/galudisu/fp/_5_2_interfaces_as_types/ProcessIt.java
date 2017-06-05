package cn.galudisu.fp._5_2_interfaces_as_types;

/**
 * @author galudisu
 */
public class ProcessIt {

    public static void moveGoods(GoodsMover goodsMover){
        System.out.println(goodsMover.name() + " busy move heavy stuff");
    }

    public static void walk(Walks walks) {
        System.out.println(walks.name() + " is having a stroll now");
    }
}
