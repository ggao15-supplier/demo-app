package com.ggg.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/***
 * ��������Ƿ�ƥ��
 * ����һ��ֻ����'('��')'��'{'��'}'��'['��']'  ���ַ������ж��ַ����Ƿ���Ч��
 *
 * ��Ч�ַ��������㣺
 *
 * 1�������ű�������ͬ���͵������űպϡ� 2�������ű�������ȷ��˳��պϡ�
 *
 * ע����ַ����ɱ���Ϊ����Ч�ַ�����
 *
 * ʾ�� 1:
 *
 * ����: "()"
 * ���: true
 * ʾ��  2:
 *
 * ����: "()[]{}"
 * ���: true
 * ʾ��  3:
 *
 * ����: "(]"
 * ���: false
 * ʾ��  4:
 *
 * ����: "([)]"
 * ���: false
 * ʾ��  5:
 *
 * ����: "{[]}"
 * ���: true
 *
 * ʹ�� ջ + HashMap
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
