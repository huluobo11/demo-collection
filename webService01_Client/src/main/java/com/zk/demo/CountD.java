package com.zk.demo;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

public class CountD {
    private CountDownLatch countDownLatch;


    public static void main(String[] args) {
       String a = "a";
       String b = new String("a");
       String c = new String("a");
     /*   System.out.println(a.equals(b));
        System.out.println(a == b);
        System.out.println(a.toString());
        System.out.println(b.toString());*/
      /*  System.out.println("---------------------");
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(a.hashCode());*/
        String s = "h";
        try {
            System.out.println(new String (s.getBytes(),"GBK"));
            System.out.println(new String (s.getBytes("UTF-8"),"GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        test(s);
        System.out.println(s);
    }

    private static void test(String v) {
        //v="h";
        v = "AA";
    }

}
