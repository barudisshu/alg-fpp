package cn.galudisu.fp._5_1_teractor_design_pattern;

import java.util.Iterator;

public class MyDataStruct {

    public Iterable<Integer> values() {
        return new Iterable<Integer>() {
            private final int[] array = new int[]{5, 11, 22};

            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    private int i = 0;

                    @Override
                    public boolean hasNext() { // 2
                        return i != array.length;
                    }

                    @Override
                    public Integer next() {
                        return array[i++]; // 3
                    }
                    // remove method not shown
                };
            }
        };
    }
}