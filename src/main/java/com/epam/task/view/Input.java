package com.epam.task.view;

import java.util.Scanner;

 class Input {
    private Scanner scanner = new Scanner(System.in);

     String enterString() {
        return scanner.next();
    }

     int enterInt() {
        return scanner.nextInt();
    }
     String enterFieldName(){
        System.out.println("enter field name: ");
        return scanner.next();
    }
     Object enterNewValue(){
        System.out.println("enter new value: ");
        return scanner.next();
    }

}
