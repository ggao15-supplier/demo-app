package com.ggg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 *  因为左右括号需要匹配、闭合。所以对应“(”和“)”的数量都是n，
 *  当满足这个条件时，一次递归就结束，将对应值放入结果数组中。
 *
 * 这里有一个潜在的限制条件：有效的括号组合。对应逻辑就是在往每个位置去放入“(”或“)”前：
 *
 * 需要判断“(”的数量是否小于 n
 * “)”的数量是否小于“(”
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        int n = 3;
        List<String> result = new ArrayList<>();
        generate(n, 0, 0, "", result);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static void generate(int n, int left, int right, String parenthesis, List<String> result) {
        if (left == n && right == n) {
            result.add(parenthesis);
        }
        if (left < n) {
            generate(n, left + 1, right, parenthesis + "(", result);
        }
        if (right < left) {
            generate(n, left, right + 1, parenthesis + ")", result);
        }
    }
}
