package cn.galudisu.fp._1_6_grouping_continuous_integers_problem;

/**
 * @author galudisu
 */
public class MyPredicate implements Predicate {
    public boolean apply(int nextElem, int currElem) {
        return nextElem - currElem == 1;
    }
}