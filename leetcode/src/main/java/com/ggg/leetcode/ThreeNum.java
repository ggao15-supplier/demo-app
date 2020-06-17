package com.ggg.leetcode;

import java.util.Arrays;

/***
 * 给你一个包含n个整数的数组nums，判断nums中是否存在三个元素a，b，c ，使得a + b + c = 0。请你找出所有满足条件且不重复的三元组。
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 将3数之和变为target 与3数之差
 * 设temp=target-num1，则temp=num2+num3
 * 可以将3数转换成2数的求法
 * 还有可以 使用双指针的方式，双指针的局限是需要数据是有序排列的
 */
public class ThreeNum {
    public static void main(String[] args) {
        threeNum();
    }

    private static void threeNum() {
        int target = 0;
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Arrays.sort(nums);
        int headerIndex = 0;
        int footerIndex = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            headerIndex = i + 1;
            while (headerIndex < footerIndex) {
                if (temp == nums[headerIndex] + nums[footerIndex]) {
                    System.out.println(nums[i] + "," + nums[headerIndex] + "," + nums[footerIndex]);
                    break;
                } else if (nums[headerIndex] < temp - nums[footerIndex]) {
                    headerIndex++;
                } else {
                    footerIndex--;
                }
            }
        }
    }
}
