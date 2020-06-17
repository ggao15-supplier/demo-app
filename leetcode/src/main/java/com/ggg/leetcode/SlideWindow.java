package com.ggg.leetcode;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/***
 * 给定一个数组 nums，有一个大小为  k  的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k  个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class SlideWindow {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("most values:" + windowMostValue(nums, 6));
    }

    private static List<Integer> windowMostValue(int[] nums, int k) {
        List<Integer> values = new ArrayList<>();
        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>(k);
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() == k) {
                int max = queue.peek();
                for (Integer integer : queue) {
                    if (max < integer) {
                        max = integer;
                    }
                }
                values.add(max);
                queue.pop();
            }

        }
        return values;
    }
}
