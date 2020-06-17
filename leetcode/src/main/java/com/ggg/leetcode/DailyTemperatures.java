package com.ggg.leetcode;

import java.util.Arrays;
import java.util.Stack;

/***
 * 根据每日气温列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用  0 来代替。
 *
 * 例如，给定一个列表  temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是  [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温列表长度的范围是  [1, 30000]。每个气温的值的均为华氏度，都是在  [30, 100]  范围内的整数
 *
 * 维护一个递减栈。当遍历过的温度，维持的是一个单调递减的态势时，我们就对这些温度的索引下标执行入栈操作；
 * 只要出现了一个数字，它打破了这种单调递减的趋势，也就是说它比前一个温度值高，
 * 这时我们就对前后两个温度的索引下标求差，得出前一个温度距离第一次升温的目标差值。
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("daily:" + Arrays.toString(daily(temperatures)));
    }

    private static int[] daily(int[] temperatures) {
        int[] days = new int[temperatures.length];
        Stack<Integer> decrease = new Stack<>();
        for (int i = 0; i < temperatures.length - 1; i++) {
            //若temperatures[decrease.peek()] > temperatures[i]，则变成了下降的插值
            while (!decrease.isEmpty() && temperatures[decrease.peek()] < temperatures[i]) {
                int temp = decrease.pop();
                days[temp] = i - temp;
            }
            decrease.push(i);
        }
        return days;
    }
}
