package cn.galudisu.fp._1_6_grouping_continuous_integers_problem;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.List;

/**
 * @author galudisu
 */
public class SortNumber {

    public List<List<Integer>> groupThem(final List<Integer> list) {
        final List<Integer> inputList = Collections.unmodifiableList(list);
        final List<List<Integer>> result = Lists.newArrayList();
        int i = 0;
        while (i < inputList.size()) {
            i = pickUpNextGroup(i, inputList, result); // i must progress
        }
        return result;
    }

    public List<List<Integer>> groupThem(List<Integer> list, Predicate myPredicate) {
        final List<Integer> inputList = Collections.unmodifiableList(list);
        final List<List<Integer>> result = Lists.newArrayList();
        int i = 0;
        while (i < inputList.size()) {
            i = pickUpNextGroup(i, inputList, myPredicate, result); // i must
        }
        return result;
    }

    /**
     * 该算法表示为，对于一个给定顺序的List，如果当前index 的值和下一个 index + 1 的值是递增的，则把它们分到一个group中。例如：
     * (1,3,4,5,7,11,12) 分组后为{(1), (3,4,5), (7), (11,12)}
     *
     * @param start     指针
     * @param inputList 要遍历的集合
     * @param result    分组结果
     * @return
     */
    private int pickUpNextGroup(final int start, final List<Integer> inputList, final List<List<Integer>> result) {
        Validate.isTrue(!inputList.isEmpty(), "Input list should have at least one element");
        Validate.isTrue(start <= inputList.size(), "Invalid start index");
        final List<Integer> group = Lists.newArrayList();
        int currElem = inputList.get(start);
        group.add(currElem); // We will have at least one element in the group
        int next = start + 1; // next index may be out of range
        while (next < inputList.size()) {
            final int nextElem = inputList.get(next); // next is in range
            if (nextElem - currElem == 1) { // grouping condition
                group.add(nextElem);
                currElem = nextElem; // setup for next iteration
            } else {
                break; // this group is done
            }
            ++next;
        }
        result.add(group); // add the group to result list
        Validate.isTrue(next > start); // make sure we keep moving
        return next; // next index to start iterating from
        // could be past the last valid index
    }

    private int pickUpNextGroup(int start, List<Integer> inputList, Predicate myPredicate, List<List<Integer>> result) {
        Validate.isTrue(!inputList.isEmpty(), "Input list should have at least one element");
        Validate.isTrue(start <= inputList.size(), "Invalid start index");
        final List<Integer> group = Lists.newArrayList();
        int currElem = inputList.get(start);
        group.add(currElem);
        int next = start + 1;
        while (next < inputList.size()) {
            final int nextElem = inputList.get(next);
            //if (nextElem - currElem == 1) { // grouping condition
            if (myPredicate.apply(nextElem, currElem)) { // grouping condition
                group.add(nextElem);
                currElem = nextElem;
            } else {
                break;
            }
            ++next;
        }
        result.add(group);
        Validate.isTrue(next > start);
        return next;
    }
}

