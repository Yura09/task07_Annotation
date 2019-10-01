package com.epam.task.controller;

import com.epam.task.model.annotations.MethodAnnotation;
import com.epam.task.model.annotations.Show;
import com.epam.task.model.classes.Animal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class Controller {
    private Animal animal;
    private StringBuilder stringBuilder;

    public Controller() {
        animal = new Animal();
        stringBuilder = new StringBuilder();
    }

    public StringBuilder valueOfFields() throws IllegalAccessException {
        Field[] fields = animal.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Show.class)) {
                    stringBuilder.append(field.getName()).append(": ").append(field.get(animal));
                }
                stringBuilder.append('\n');
            }
        }
        return stringBuilder;
    }

    public StringBuilder valueOfAnnotations() {
        Field[] fields = animal.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Show.class)) {
                Show s = f.getAnnotation(Show.class);
                stringBuilder.append("value of annotation's name: ")
                        .append(s.name())
                        .append(", value of annotation's age: ")
                        .append(s.age())
                        .append('\n');
            }
        }
        return stringBuilder;
    }

    @MethodAnnotation
    public String someMessage() {
        return "Hello World";
    }

    @MethodAnnotation
    public int getSum(int a, int b) {
        return a + b;
    }

    @MethodAnnotation
    public Animal getNewAnimal(String name, int age, String breed) {
        return new Animal(name, age, breed);
    }

    public StringBuilder setInstanceValue(final Object classInstance, final String fieldName, final Object newValue) {
        stringBuilder.append("object until setting new value: ").append(classInstance).append('\n');
        final Field field;
        try {
            field = classInstance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Type type = field.getType();
            if (((Class) type).isPrimitive()) {
                field.set(classInstance, Integer.valueOf((String) newValue));
            } else {
                field.set(classInstance, newValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        stringBuilder.append("object after setting new value: ").append(classInstance);
        return stringBuilder;
    }
}

