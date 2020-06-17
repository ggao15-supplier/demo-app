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
 */
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println("step:" + recursive(3));
    }

    private static int recursive(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        return recursive(n - 1) + recursive(n - 2);
    }
}
