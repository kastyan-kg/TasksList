package com.github.taskslist.controllers;

import com.github.taskslist.DAO.TaskDAO;
import com.github.taskslist.classes.PriorityType;
import com.github.taskslist.classes.Task;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kos on 19-Oct-16.
 */
public class TaskService {

    private TaskDAO dao;
    public TaskService(TaskDAO dao)
    {
        this.dao = dao;
    }


    public void printTasks() throws SQLException, ClassNotFoundException {
        List<Task> tasksFromDB = dao.showTasks();
        int counter = 1;
        System.out.println("LIST OF UNFINISHED TASKS:");
        System.out.printf("%40s%40s%15s%10s", "name", "deadline", "priority","isOverdue");
        System.out.println();
        System.out.println("===========================================================================================================");
        for(Task element: tasksFromDB){
            System.out.printf("%3d|",counter);
            element.printTask();
            System.out.println();
            counter++;
        }
    }
    public void addTask(String name, Date deadline, PriorityType priority) throws SQLException, ClassNotFoundException {
        Task addedTask = new Task(name, deadline, priority, false);
        dao.createTask(addedTask);

    }
    public  void printFifnishedTasks(){
        List<Task> tasksFromDB = dao.showFinishedTasks();
        int counter = 1;
        System.out.println("FINISHED TASKS");
        System.out.printf("%40s%40s%20s", "name", "deadline", "priority","isOverdue");
        System.out.println();
        System.out.println("======================================================================================================");
        for(Task element: tasksFromDB){
            System.out.printf("%3d|",counter);
            element.printTask();
            System.out.println();
            counter++;
        }

    }
    public void finishTask(int taskID)
    {
        dao.finishTask(taskID);

    }

}
