package com.ggg.leetcode;

public class RebuildTree {
    static class TreeNode {
        private int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] pre = {7, 10, 4, 3, 1, 2, 8, 11};
        int[] in = {4, 10, 3, 1, 7, 11, 8, 2};
        TreeNode treeNode = new TreeNode(pre[0]);
        for (int i : pre) {
            rebuild(in, i, treeNode);
        }

        System.out.println("sss");
    }

    private static void rebuild(int[] array, int root, TreeNode node) {
        if (array.length <= 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == root) {
                if (i == 0 || i == array.length - 1) {
                    return;
                }
                int[] leftTree = new int[i];
                int[] rightTree = new int[array.length - i];
                TreeNode left = new TreeNode(array[i - 1]);
                node.left = left;
                rebuild(leftTree, array[i - 1], left);
                TreeNode right = new TreeNode(array[i + 1]);
                node.right = right;
                rebuild(rightTree, array[i + 1], right);
                break;
            }
        }
    }
}
