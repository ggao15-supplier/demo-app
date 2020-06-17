package com.ggg.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class PhoneKeyBoardMap {
    private static Map<String, String> mapping = new HashMap<>();

    public static void main(String[] args) {
        mapping.put("0", "");
        mapping.put("1", "");
        mapping.put("2", "abc");
        mapping.put("3", "def");
        mapping.put("4", "ghi");
        mapping.put("5", "jkl");
        mapping.put("6", "mno");
        mapping.put("7", "pqrs");
        mapping.put("8", "tuv");
        mapping.put("9", "wxyz");

        doubleErgodic(new String[]{"2", "3"});

        System.out.println("-----------递归-------");
        List<String> result = new ArrayList<String>();
        recursiveForWidth(new String[]{"2", "3"}, 0, "", result);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
        System.out.println("-------------------");

    }

    private static void doubleErgodic(String[] nums) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            String temp = mapping.get(nums[i]);
            if (list.isEmpty()) {
                for (char c : temp.toCharArray()) {
                    list.add(String.valueOf(c));
                }

            } else {
                List<String> tempList = new ArrayList<>();
                for (String s : list) {
                    for (char c : temp.toCharArray()) {
                        tempList.add(s + c);
                    }
                }
                list = tempList;
            }
        }
        System.out.println("------双层遍历----------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("-------------------------------");

    }

    private static void recursiveForWidth(String[] nums, int index, String chars, List<String> result) {
        if (index == nums.length) {
            result.add(chars);
            return;
        }
        String temp = mapping.get(nums[index]);
        for (char c : temp.toCharArray()) {
            recursiveForWidth(nums, index + 1, chars + c, result);
        }
    }

}
