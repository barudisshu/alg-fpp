package cn.galudisu.fp._5_1_teractor_design_pattern;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;

public class Driver {
    public static void main(String[] args) {
        Map<String, Integer> lhmap = Maps.newLinkedHashMap();
        lhmap.put("one", 1);
        lhmap.put("two", 2);
        lhmap.put("three", 3);

        PrintElems pe = new PrintElems();
        pe.print(lhmap.values());

        pe.print(Maps.newHashMap(lhmap).values()); // 4
        pe.print(Sets.newHashSet((lhmap).values())); // 5
        pe.print(Lists.newArrayList((lhmap).values())); // 6
        MyDataStruct p = new MyDataStruct();
        pe.print(p.values()); // 7
    }
}