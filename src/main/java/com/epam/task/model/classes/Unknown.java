package com.epam.task.model.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Unknown {
    public static StringBuilder showAllInformationAboutClass(Object classInstance) {
        StringBuilder stringBuilder = new StringBuilder();
        Class c = classInstance.getClass();
        Field[] fields = c.getDeclaredFields();

        stringBuilder.append("name of class: ")
                .append(c.getName())
                .append('\n')
                .append("\nfields:\n");
        for (Field field : fields) {
            stringBuilder.append("field's name: ").append(field.getName()).append(", field's type: ").append(field.getType()).append('\n');
        }
        stringBuilder.append("\nparameters of constructor's:\n");
        Constructor[] constructors = c.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                stringBuilder.append("constructor without parameters\n");
                continue;
            }
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class paramType : paramTypes) {
                stringBuilder.append(paramType.getName()).append("  ");

            }
            stringBuilder.append('\n');
        }
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            stringBuilder.append("method's name: ").append(method.getName()).append(", method's type: ").append(method.getGenericReturnType()).append('\n');
        }
        return stringBuilder;
    }
}
