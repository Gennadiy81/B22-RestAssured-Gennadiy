package com.cybertek.homework;

import java.util.*;

public class Practice {
    public static void main(String[] args) {
       //ArrayDeque<Integer> a = new ArrayDeque<>();
        //HashSet<Integer> a = new HashSet<>();
        //Stack<Integer> a = new Stack<Integer>();
        //LinkedHashSet<Integer>  a = new LinkedHashSet<>();
        LinkedList<Integer> a = new LinkedList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);

        System.out.println(a.iterator().hashCode());

    }
}
