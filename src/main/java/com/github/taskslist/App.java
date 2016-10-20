package com.github.taskslist;

import com.github.taskslist.DAO.TaskDAO;
import com.github.taskslist.controllers.TaskController;
import com.github.taskslist.utility.DateValidator;
import com.github.taskslist.utility.TasksCounterValidator;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by kos on 19-Oct-16.
 */
public class App {

    public static void main(String []args) {

        Scanner reader = new Scanner(System.in);
        TaskController controller = new TaskController();
        DateValidator dateChecker = new DateValidator();
        TasksCounterValidator validator = new TasksCounterValidator();
        boolean appIsRunning = true;
        int chosenOption ;
        while (appIsRunning) {
            try {

                System.out.println("Welcome, to TasksList, user");
                System.out.println("Please choose from the following:");
                System.out.println("Create a new task, press 1");
                System.out.println("Show all tasks, press 2");
                System.out.println("Exit, press 0");
                chosenOption = reader.nextInt();

                if (chosenOption == 1) {
                    System.out.println("To create a new task please type TODO, max 30 symbols :");
                    String name = reader.next();
                    name += reader.nextLine();


                    System.out.println("Now set a deadline, it has to be in a certain date format 'YYYY-MM-DD' :");
                    String deadline = null;
                    while(true) {
                         deadline = reader.next();
                        if (dateChecker.checkDateFormat(deadline)) {

                            break;
                        }else{
                            System.out.println("Text correct data value format 'YYYY-MM-DD' ");

                        }
                    }

                    System.out.println("Please select priority, possible values:" + '\n' +
                            " type 1 - high, type 2 - medium, type 3 - low");

                    int priorityInput = reader.nextInt();
                    String priority = null;
                    switch (priorityInput) {
                        case 1:
                            priority = "high";
                            break;
                        case 2:
                            priority = "medium";
                            break;
                        case 3:
                            priority = "low";
                            break;

                    }
                    controller.addTask(name, deadline, priority);
                } else if (chosenOption == 2) {
                    controller.setOverDue();
                    controller.printTasks();
                    while (true) {
                        System.out.println("To mark finished tasks press 1 ");
                        System.out.println("To print all finished tasks press 2");
                        System.out.println("Come back to the previous menu press 3");
                        try {
                            chosenOption = reader.nextInt();
                        }catch(InputMismatchException e){
                            System.out.println("Please type only digits from suggested values!");
                            reader.next();
                        }

                        if (chosenOption == 1) {
                            System.out.println("Please type number of the task to finish");
                            int finishedTask = 0;
                           try {
                                finishedTask = reader.nextInt();

                           }catch(InputMismatchException e){
                               System.out.println("Please type only digits from suggested values!");
                               reader.next();
                           }
                            System.out.println(finishedTask);
                            if(validator.checkPossibleTaskNuber(finishedTask)){
                                controller.finishTask(finishedTask);
                                System.out.println("Marked successfully");
                                break;
                            }else{
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

                } else if (chosenOption == 0) {
                    reader.close();
                    System.out.println("Thanks, for using TasksList, bye!");
                    break;
                } else {
                    System.out.println("Incorrect value please use from the following: 2-1-0 and try again");

                }


            } catch (InputMismatchException e) {
                System.out.println("Please type only digits from suggested values!");
                reader.next();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}
