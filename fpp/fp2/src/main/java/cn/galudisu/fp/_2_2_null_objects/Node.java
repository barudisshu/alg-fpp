package cn.galudisu.fp._2_2_null_objects;

import java.util.List;

/**
 * 定义一个二叉搜索树
 *
 * @author galudisu
 */
public class Node {

    private static final Node nullNode = new NullNode();
    private final int val;
    protected Node left;
    protected Node right;

    public Node(final int val) {
        this.val = val;
        this.left = nullNode;
        this.right = nullNode;
    }

    public void insert(final int i) {
        final Node node = new Node(i);
        insert(node);
    }

    public void insert(final Node n) {
        if (n.val < val)
            left.insertLeft(n, this);
        else if (n.val > val)
            right.insertRight(n, this);
        // else we found the value - do nothing, we
        // already have it
    }

    protected void insertLeft(final Node n, final Node parent) {
        insert(n);
    }

    protected void insertRight(final Node n, final Node parent) {
        insert(n);
    }

    public void traverseInOrder(final List<Integer> list) {
        left.traverseInOrder(list);
        list.add(val);
        right.traverseInOrder(list);
    }

    public static Node createTreeWithRoot(final int val) {
        return new Node(val);
    }

    private static class NullNode extends Node {
        public NullNode() {
            super(Integer.MAX_VALUE);
            this.left = this.right = this;
        }

        @Override
        public void traverseInOrder(List<Integer> list) {
            // do nothing
        }

        @Override
        protected void insertLeft(Node n, Node parent) {
            parent.left = n;
        }

        @Override
        protected void insertRight(Node n, Node parent) {
            parent.right = n;
        }

    }
}
