package com.ggg.leetcode;

/**
 * 有序二维数组查找，
 * 思路：从数组的右上角或左下角开始，
 */
public class DoubleArraySearch {

    public static void main(String[] args) {

        int[][] arrays = {{1, 2, 8, 9}, {3, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        search(arrays, 7);
        System.out.println("========================================");
        search(arrays, 4);
    }

    private static void search(int[][] arrays, int target) {
        if (arrays == null || arrays.length <= 0 || arrays[0] == null || arrays[0].length <= 0) {
            System.out.println("empty array");
        } else {
            boolean hasTarget = false;
            int i = 0;
            int j = arrays[0].length - 1;
            while (i < arrays.length && j >= 0) {
                if (arrays[i][j] == target) {
                    System.out.println("target index is :(" + i + "," + j + ")");
                    hasTarget = true;
                    i++;
                    j--;
                } else if (arrays[i][j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
            if(!hasTarget){
                System.out.println("no target");
            }

        }
    }
}
