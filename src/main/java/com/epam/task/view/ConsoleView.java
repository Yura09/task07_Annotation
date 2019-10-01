package com.epam.task.view;

import com.epam.task.controller.Controller;
import com.epam.task.model.classes.Unknown;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleView {
    private static Logger logger = LogManager.getLogger(ConsoleView.class);
    private Controller controller;
    private Map<String, String> menu;
    private Map<String, Printable> methodsMenu;
    private Input input;

    public ConsoleView() {
        menu = new LinkedHashMap<>();
        controller = new Controller();
        input = new Input();
        menu.put("1", " 1 - Print fields in the class that were annotated");
        menu.put("2", " 2 - Print annotation value");
        menu.put("3", " 3 - print invoked methods with different parameters and types");
        menu.put("4", " 4 - print value that was set into field not knowing its type");
        menu.put("5", " 5 - show all information about that Class");
        menu.put("Q", " Q - exit");
        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("1", this::printValueOfFields);
        methodsMenu.put("2", this::printAnnotationValue);
        methodsMenu.put("3", this::printThreeDifferentMethods);
        methodsMenu.put("4", this::printObjectAfterSettingNewValue);
        methodsMenu.put("5", this::printAllInformationAboutClass);
    }

    private void printValueOfFields() {
        try {
            logger.info("\n" + controller.valueOfFields());
        } catch (IllegalAccessException e) {
            logger.fatal(e.getMessage());
        }
    }

    private void printAnnotationValue() {
        logger.info(controller.valueOfAnnotations());
    }

    private void printThreeDifferentMethods() {
        logger.info(controller.getSum(1, 3));
        logger.info(controller.someMessage());
        logger.info(controller.getNewAnimal("Barsik", 4, "labrador"));
    }

    private void printObjectAfterSettingNewValue() {
        logger.info("\nenter name age and breed");
        logger.info(controller.setInstanceValue(controller.getNewAnimal(input.enterString(), input.enterInt(), input.enterString()), input.enterFieldName(), input.enterNewValue()));
    }

    private void printAllInformationAboutClass() {
        logger.info("\nenter name age and breed");
        logger.info(Unknown.showAllInformationAboutClass(controller.getNewAnimal(input.enterString(), input.enterInt(), input.enterString())));
    }

    private void showMenu() {

        logger.info("MENU:");
        for (String str : menu.values()) {
            logger.info(str);
        }
    }

    public void show() {
        String keyMenu;
        do {
            showMenu();
            logger.info("Please, select menu point.");
            keyMenu = input.enterString().toUpperCase();
            if (keyMenu.equals("Q")) {
                logger.info("bye-bye");
                break;
            }
            try {
                methodsMenu.get(keyMenu).print();
            } catch (NullPointerException e) {
                logger.error("incorrect inputs");
                break;
            }
        } while (true);
    }
}
