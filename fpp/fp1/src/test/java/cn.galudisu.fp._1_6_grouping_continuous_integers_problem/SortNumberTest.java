package cn.galudisu.fp._1_6_grouping_continuous_integers_problem;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/** @author galudisu */
public class SortNumberTest {

  private SortNumber groupThem = new SortNumber();

  @Test
  public void testFourGroup() {
    List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 9, 11, 20, 21, 22);
    List<List<Integer>> groups = groupThem.groupThem(list);
    assertThat(groups.size(), equalTo(4));
    assertThat(groups.get(0), contains(1, 2, 3, 4, 5));
    assertThat(groups.get(1), contains(9));
    assertThat(groups.get(2), contains(11));
    assertThat(groups.get(3), contains(20, 21, 22));
  }

  @Test
  public void testNoGroup() {
    List<Integer> emptyList = Lists.newArrayList();
    List<List<Integer>> groups = groupThem.groupThem(emptyList, new MyPredicate());
    assertThat(groups, emptyIterable());
  }

  @Test
  public void testOnlyOneGroup() {
    List<Integer> list = Lists.newArrayList(1);
    List<List<Integer>> groups = groupThem.groupThem(list, new MyPredicate());
    assertThat(groups.size(), equalTo(1));
    assertThat(groups.get(0), contains(1));
  }
}
