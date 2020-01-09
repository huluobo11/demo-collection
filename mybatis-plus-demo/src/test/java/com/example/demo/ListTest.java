package com.example.demo;

import org.junit.Test;

public class ListTest {

    @Test
    public void adList1() {
        int a =21000000, b=7200,c=0;
        while (a >= 0) {
            a -=b;
            b /=2;
            c++;
        }
        System.out.println(c);
    }
}
