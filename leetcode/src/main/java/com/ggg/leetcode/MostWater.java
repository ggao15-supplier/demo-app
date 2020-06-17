package com.ggg.leetcode;

/***
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点  (i, ai) 。在坐标内画 n 条垂直线，垂直线 i  的两个端点分别为  (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与  x  轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且  n  的值至少为 2。
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * 类似双指针的滑动窗口，左指针指向下标 0，右指针指向length-1。然后分别从左右两侧向中间移动，每次取小的那个值（因为水的高度肯定是以小的那个为准）。
 */
public class MostWater {
    public static void main(String[] args) {
        mostV();
    }

    private static void mostV() {
        int nums[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int headerIndex = 0;
        int footerIndex = nums.length - 1;
        int mostLeftIndex = headerIndex;
        int mostRightIndex = footerIndex;
        int most = 0;
        while (headerIndex < footerIndex) {
            if (nums[headerIndex] < nums[footerIndex]) {
                int min = nums[headerIndex];
                int temp = min * (footerIndex - headerIndex);
                if (temp > most) {
                    most = temp;
                    mostLeftIndex = headerIndex;
                    mostRightIndex = footerIndex;
                }
                headerIndex++;
            } else {
                int min = nums[footerIndex];
                int temp = min * (footerIndex - headerIndex);
                if (temp > most) {
                    most = temp;
                    mostLeftIndex = headerIndex;
                    mostRightIndex = footerIndex;
                }
                footerIndex--;
            }

        }
        System.out.println("most:" + most + ";left:" + mostLeftIndex + ";right:" + mostRightIndex);
    }
}
