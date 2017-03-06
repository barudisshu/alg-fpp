package cn.galudisu.fp._5_1_iteractor_design_pattern;

/**
 * @author galudisu
 */
public class PrintElems {

    public void print(Iterable<Integer> iterator) {
        System.out.println("---Start---");
        for (Integer i : iterator) {
            System.out.println(i);
        }
        System.out.println("---End---");
    }
}
