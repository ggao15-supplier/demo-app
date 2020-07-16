package com.ggg.leetcode;

/***
 * 假设你正在爬楼梯。需要 n  阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 使用递归来解决,退出递归的条件是 1层台阶时只有1种，2层时有2种
 使用 动态规划解决，记录 n-1+n-2 的结果，然后参与下次的计算
 */
public class ClimbStairs {
    public static void main(String[] args) {
        int target = 10;
        System.out.println("recursive:" + recursive(target));
        System.out.println("dlsWithArray:" + dlsWithArray(target));
        System.out.println("dlsWithTemp:" + dlsWithTemp(target));
    }

    /**
     * 递归
     *
     * @param n
     * @return
     */
    private static int recursive(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        return recursive(n - 1) + recursive(n - 2);
    }

    /**
     * 动态规划 使用 数组记录每一次的结果
     *
     * @param n
     * @return
     */
    private static int dlsWithArray(int n) {
        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                nums[i] = 1;
            } else if (i == 1) {
                nums[i] = 2;
            } else {

                nums[i] = nums[i - 1] + nums[i - 2];
            }
        }

        return nums[n - 1];
    }

    /**
     * 动态规划，使用变量记录每一次计算的结果，然后 进行交换
     *
     * @param n
     * @return
     */
    private static int dlsWithTemp(int n) {
        int temp = 0;
        int a = 1;
        int b = 2;
        if (n == 0) temp = 0;
        if (n == 1) temp = 1;
        if (n == 2) temp = 2;

        for (int i = 2; i < n; i++) {

            temp = a + b;
            a = b;
            b = temp;

        }

        return temp;
    }
}
