package com.epam.task.model.classes;

import com.epam.task.model.annotations.Show;

public class Animal {
    Object exampleObject;
    @Show(name = "dog")
    private String name = "Bella";
    @Show(age = 10)
    private int age = 7;
    private String breed = "Wolf";

    public Animal(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public Animal() {

    }

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Animal getAnimal() {
        return new Animal();
    }

    public int getAge() {
        return age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
