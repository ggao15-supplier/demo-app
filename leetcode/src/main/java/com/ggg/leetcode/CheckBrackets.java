package com.ggg.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * 检查括号是否匹配
 * 给定一个只包括'('，')'，'{'，'}'，'['，']'  的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 1、左括号必须用相同类型的右括号闭合。 2、左括号必须以正确的顺序闭合。
 *
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例  2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例  3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例  4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例  5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 * 使用 栈 + HashMap
 */
public class CheckBrackets {
    public static void main(String[] args) {
        String brackets1 = "{[[[]]]()(])";
        System.out.println(brackets1 + "::" + check(brackets1));
        String brackets2 = "{[[[]]]()([])";
        System.out.println(brackets2 + "::" + check(brackets2));

    }

    private static boolean check(String brackets) {
        Map<String, String> map = new HashMap<>();
        Stack<String> stack = new Stack<>();
        map.put("(", ")");
        map.put("[", "]");
        map.put("{", "}");
        for (char c : brackets.toCharArray()) {
            String bracket = String.valueOf(c);
            if (map.containsKey(bracket)) {
                stack.push(bracket);
            } else {
                if (!map.get(stack.pop()).equals(bracket)) {
                    return false;
                }
            }
        }
        return true;
    }
}
