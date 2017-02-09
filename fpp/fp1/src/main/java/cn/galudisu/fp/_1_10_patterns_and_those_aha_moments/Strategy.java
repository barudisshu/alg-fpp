package cn.galudisu.fp._1_10_patterns_and_those_aha_moments;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author galudisu
 */
public class Strategy {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4};
        Comparator<Integer> comparator = (o1, o2) -> {
            return Integer.compare(o2, o1);// the strategy algorithm –
            // for reverse sorting
        };
        Arrays.sort(arr, comparator);
        System.out.println(Arrays.toString(arr));
    }
}
