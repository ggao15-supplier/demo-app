package com.ggg.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 给定一个整数数组 nums?和一个目标值 target，请你在该数组中找出和为目标值的那?两个?整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] array = {1, 2, 8, 4, 9};
        int target = 12;
        System.out.println("普通方法");
        int[] normals = normal(array, target);
        System.out.println("index is " + normals[0] + ";;" + normals[1]);

        System.out.println("=========================");
        System.out.println("使用 哈希表的特性实现");
        int[] hashMapArray = hashMap(array, target);
        System.out.println("index is " + hashMapArray[0] + ";;" + hashMapArray[1]);
    }

    private static int[] normal(int[] arrays, int target) {
        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[i] + arrays[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static int[] hashMap(int[] arrays, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arrays.length; i++) {
            map.put(arrays[i], i);
            int other = target - arrays[i];
            if (map.containsKey(other)) {
                return new int[]{i, map.get(other)};
            }
        }
        return null;
    }
}
