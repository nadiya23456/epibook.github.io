package com.epi;

import com.epi.BinaryTreeWithParentPrototype.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversalWithParent {
  private static List<Integer> results = new ArrayList<>();

  // @include
  public static void inOrderTraversal(BinaryTree<Integer> tree) {
    BinaryTree<Integer> prev = null, curr = tree;
    while (curr != null) {
      BinaryTree<Integer> next;
      if (prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
        // We came down to curr from prev.
        if (curr.getLeft() != null) { // Keep going left.
          next = curr.getLeft();
        } else {
          System.out.println(curr.getData());
          // @exclude
          results.add(curr.getData());
          // @include
          // Done with left, so go right if right is not empty.
          // Otherwise, go up.
          next = (curr.getRight() != null) ? curr.getRight() : curr.getParent();
        }
      } else if (curr.getLeft() == prev) {
        System.out.println(curr.getData());
        // @exclude
        results.add(curr.getData());
        // @include
        // Done with left, so go right if right is not empty. Otherwise, go up.
        next = (curr.getRight() != null) ? curr.getRight() : curr.getParent();
      } else { // Done with both children, so move up.
        next = curr.getParent();
      }

      prev = curr;
      curr = next;
    }
  }
  // @exclude

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
    root.setLeft(new BinaryTree<>(2, null, null));
    root.getLeft().setParent(root);
    root.getLeft().setLeft(new BinaryTree<>(1, null, null));
    root.getLeft().getLeft().setParent(root.getLeft());
    root.setRight(new BinaryTree<>(5, null, null));
    root.getRight().setParent(root);
    root.getRight().setLeft(new BinaryTree<>(4, null, null));
    root.getRight().getLeft().setParent(root.getRight());
    root.getRight().setRight(new BinaryTree<>(6, null, null));
    root.getRight().getRight().setParent(root.getRight());

    // Should output 1 2 3 4 5 6.
    inOrderTraversal(root);
    List<Integer> goldenRes = new ArrayList<Integer>() {{
      add(1);
      add(2);
      add(3);
      add(4);
      add(5);
      add(6);
    }};
    assert (goldenRes.equals(results));
  }
}
