package com.example.demo;

import com.example.demo.pojo.SpecialityEnum;
import com.example.demo.pojo.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningByTest {
    public static void main(String[] args) {
        // students的初始化
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 23, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));

        Map<Boolean, List<Student>> listMap = students.stream().
                collect(Collectors.partitioningBy(student -> student.getSpecialities().contains(SpecialityEnum.SING)));
    }
}

