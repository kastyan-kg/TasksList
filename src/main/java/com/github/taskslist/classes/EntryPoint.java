package com.github.taskslist.classes;

import com.github.taskslist.controllers.TaskService;
import com.github.taskslist.utility.DateExtractor;
import com.github.taskslist.utility.TasksCounterValidator;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Developer on 21.10.2016.
 */
public class EntryPoint {

    public EntryPoint(TaskService controller, TasksCounterValidator validator)
    {
        this.controller = controller;
        this.validator = validator;
    }

    private TaskService controller;
    private TasksCounterValidator validator;
    private DateExtractor dateChecker = new DateExtractor();

    public void doWork()
    {
        try (Scanner reader = new Scanner(System.in)) {
            boolean appIsRunning = true;
            int chosenOption;
            while (appIsRunning) {
                try {

                    showMainMenuItems();
                    chosenOption = reader.nextInt();

                    if (chosenOption == 1) {
                        firstOptionHandler(reader);
                    } else if (chosenOption == 2) {
                        secondOptionHandler(reader);

                    } else if (chosenOption == 0) {
                        System.out.println("Thanks, for using TasksList, bye!");
                        break;
                    } else {
                        System.out.println("Incorrect value please use from the following: 2-1-0 and try again");

                    }

                } catch (InputMismatchException e) {
                    System.out.println("Please type only digits from suggested values!");
                    reader.next();
                } catch (SQLException | ClassNotFoundException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void secondOptionHandler(Scanner reader) throws ParseException, SQLException, ClassNotFoundException {
        controller.printTasks();
        int chosenOption;
        while (true) {
            System.out.println("To mark finished tasks press 1 ");
            System.out.println("To print all finished tasks press 2");
            System.out.println("Come back to the previous menu press 3");
            try {
                chosenOption = reader.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please type only digits from suggested values!");
                reader.next();
                continue;
            }

            if (chosenOption == 1) {
                System.out.println("Please type number of the task to finish");
                int finishedTask = 0;
                try {
                    finishedTask = reader.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("Please type only digits from suggested values!");
                    reader.next();
                }

                if (validator.checkPossibleTaskNuber(finishedTask)) {
                    controller.finishTask(finishedTask);
                    System.out.println("Marked successfully");
                    break;
                } else {
                    System.out.println("There is no task with that number");
                }


            } else if (chosenOption == 2) {

                controller.printFifnishedTasks();

            } else if (chosenOption == 3) {

                break;
            } else {
                System.out.println("Incorrect value, Please choose only from the following options!");

            }

        }
    }

    private void firstOptionHandler(Scanner reader) throws SQLException, ClassNotFoundException {
        System.out.println("To create a new task please type TODO, max 30 symbols :");
        String name = reader.next();
        name += reader.nextLine();

        System.out.println("Now set a deadline, it has to be in a certain date format 'YYYY-MM-DD' :");
        Date deadline;
        while (true) {
            String deadlineStr = reader.next();
            deadline = dateChecker.getDateFromString(deadlineStr);
            if (deadline != null) {
                break;
            } else {
                System.out.println("Text correct data value format 'YYYY-MM-DD' ");
            }
        }

        PriorityType priority;
        while (true) {
            System.out.println("Please select priority, possible values:" + '\n' +
                " type 1 - high, type 2 - medium, type 3 - low");
            int priorityInput = reader.nextInt();
            priority = PriorityType.getPriorityTypeByValue(priorityInput);
            if (priority != null) {
                break;
            } else {
                System.out.println("Provide correct value for priority");
            }
        }
        controller.addTask(name, deadline, priority);
    }

    private void showMainMenuItems() {
        System.out.println("Welcome, to TasksList, user");
        System.out.println("Please choose from the following:");
        System.out.println("Create a new task, press 1");
        System.out.println("Show all tasks, press 2");
        System.out.println("Exit, press 0");
    }
}
