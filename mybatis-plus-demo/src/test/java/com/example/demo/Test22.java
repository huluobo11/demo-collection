package com.example.demo;

import org.junit.Test;

import java.util.*;

public class Test22 {

    @Test
    public void s() {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
    }


    @Test
    public void b() {
       int [] a = {0,0,1,1,1,1,2,3,3};
       int i =2;
       int temp;
       while(i<a.length) {
           if (a[i] == a[i-2]) {
               a[i] = a[i+1];
           }else {
               i++;
           }
       }
        System.out.println(Arrays.toString(a));
    }
}
