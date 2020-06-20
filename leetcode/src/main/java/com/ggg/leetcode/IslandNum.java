package com.ggg.leetcode;

/***
 * 给你一个由  '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 示例  2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 * 这道题目一个经典的做法是沉岛，大致思路是：采用DFS（深度优先搜索），遇到 1 的就将当前的 1 变为 0，并将当前坐标的上下左右都执行 dfs，并计数。
 *
 * 终止条件是：超出二维数组的边界或者是遇到 0 ，直接返回。
 */

public class IslandNum {
    public static void main(String[] args) {
        int[][] nums = {{1, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}};
        int res = 0;
        int row = nums.length;
        int col = nums[0].length;
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col; j++) {
                if (nums[i][j] == 1) {
                    isLandNum(nums, i, j, row, col);
                    res++;
                }
            }
        }

        System.out.println(res);
    }

    private static void isLandNum(int[][] num, int i, int j, int row, int col) {

        if (i < 0 || i > row - 1 || j < 0 || j > col - 1 || num[i][j] == 0) {
            return;
        }

        num[i][j] = 0;

        isLandNum(num, i - 1, j, row, col);
        isLandNum(num, i + 1, j, row, col);
        isLandNum(num, i, j - 1 + 1, row, col);
        isLandNum(num, i, j, row, col);
    }
}
