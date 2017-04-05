package cn.galudisu.fp._5_2_interfaces_as_types;

/**
 * 马
 *
 * @author Galudisu
 */
public class Horse implements Walks, NameIt {
    public void walk() {
        ProcessIt.walk(this);
    }

    public String name() {
        return "Horse";
    }
}