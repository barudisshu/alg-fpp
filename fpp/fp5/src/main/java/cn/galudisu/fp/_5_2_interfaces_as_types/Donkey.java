package cn.galudisu.fp._5_2_interfaces_as_types;

/**
 * @author galudisu
 */
public class Donkey implements Walks, GoodsMover, NameIt {
    @Override
    public String name() {
        return "Donkey";
    }

    @Override
    public void moveGoods() {
        ProcessIt.moveGoods(this);
    }

    @Override
    public void walk() {
        ProcessIt.walk(this);
    }
}
