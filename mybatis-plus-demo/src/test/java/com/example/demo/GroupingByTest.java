package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByTest {
    public static void main(String[] args) {

        // students的初始化
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 23, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("红红", 40, 170));
        students.add(new Student("白胡子", 50, 185));

        //  根据年龄分组
        Map<Integer, List<Student>> listMap = students.stream().collect(
                Collectors.groupingBy(Student::getAge));
        System.out.println(JSON.toJSONString(listMap));
    }
}
