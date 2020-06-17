package com.ggg.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 检查 链表中是否有环
 */
public class CheckClosure {
    public static void main(String[] args) {
        Node head = new Node(-1);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;
        boolean hasClosure = check(head);
        if (hasClosure) {
            System.out.println("has closure");
        } else {
            System.out.println("no closure");
        }
    }

    private static boolean check(Node head) {
        List<Node> temp = new ArrayList<>();
        Node index = head;
        while (index.next != null) {
            if (temp.contains(index)) {
                return true;
            }
            temp.add(index);
            index = index.next;
        }
        return false;
    }

    private static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
