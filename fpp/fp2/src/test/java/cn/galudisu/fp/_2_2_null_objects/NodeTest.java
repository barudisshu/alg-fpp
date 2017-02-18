package cn.galudisu.fp._2_2_null_objects;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * @author galudisu
 */
public class NodeTest {

    @Test
    public void testTraversal() {
        Node tree = Node.createTreeWithRoot(72);
        tree.insert(50);
        tree.insert(25);
        tree.insert(65);
        tree.insert(95);
        tree.insert(88);
        tree.insert(112);

        List<Integer> list = Lists.newArrayList();
        tree.traverseInOrder(list);
        assertThat(list, contains(25, 50, 65, 72, 88, 95, 112));
    }
}
