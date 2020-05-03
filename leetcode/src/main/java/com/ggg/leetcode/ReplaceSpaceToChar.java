package com.ggg.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ReplaceSpaceToChar {

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("hello world");
        System.out.println(replaceSpace(stringBuffer));
    }

    public static String replaceSpace(StringBuffer str) {
        char[] temp = str.toString().toCharArray();
        char[] inserts = "%20".toCharArray();
        char[] results = new char[2 * str.length()];
        int index = 0;
        for (char item : temp
        ) {

            if (item == 0x20) {
                for (char insert : inserts) {
                    results[index] = (insert);
                    index++;
                }
            } else {
                results[index] = (item);
                index++;
            }
        }
        StringBuffer b = new StringBuffer();
        for (Character character : results) {
            b.append(character);
        }
        return b.toString();

    }

    private static int insert(int index, char[] inserts, List<Character> result) {
        for (char insert : inserts) {
            result.add(insert);
            index++;
        }
        return index;
    }
}
