package ru.stepup.edu;

import lombok.Getter;
import lombok.Setter;

public class Employee {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private Integer age;
    @Getter @Setter
    private String position;

    public Employee(String name, Integer age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }
}
