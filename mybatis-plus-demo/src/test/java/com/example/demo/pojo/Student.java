package com.example.demo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private String name;

    private int age;

    private int stature;

    private List<SpecialityEnum> Specialities;

    public Student(String name, int age, int stature) {
        this.age = age;
        this.name = name;
        this.stature = stature;
    }
}
