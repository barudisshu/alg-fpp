package cn.galudisu.fp._5_2_interfaces_as_types;

/**
 * @author galudisu
 */
public class Horse implements Walks,NameIt {
    @Override
    public String name() {
        return "Horse";
    }

    @Override
    public void walk() {
        ProcessIt.walk(this);
    }
}
