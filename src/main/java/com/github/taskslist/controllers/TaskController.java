package com.github.taskslist.controllers;

import com.github.taskslist.DAO.TaskDAO;
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
public class TaskController {

    private TaskDAO dao = new TaskDAO();

    public void printTasks() throws SQLException, ClassNotFoundException {
        List<Task> tasksFromDB = dao.showTasks();
        int counter = 1;
        System.out.println("LIST OF UNFINISHED TASKS:");
        System.out.printf("%50s%20s%20s%20s", "name", "deadline", "priority", "actualOverdue");
        System.out.println();
        System.out.println("===========================================================================================================================");
        for(Task element: tasksFromDB){
            System.out.printf("%3d|",counter);
            element.printTask();
            System.out.println();
            counter++;
        }
    }
    public void addTask(String name, String deadline, String priority) throws SQLException, ClassNotFoundException {
        Task addedTask = new Task(name, deadline, priority, false, false);
        dao.createTask(addedTask);

    }
    public  void printFifnishedTasks(){
        List<Task> tasksFromDB = dao.showFinishedTasks();
        int counter = 1;
        System.out.println("FINISHED TASKS");
        System.out.printf("%50s%20s%20s%20s", "name", "deadline", "priority", "actualOverdue");
        System.out.println();
        System.out.println("===========================================================================================================================");
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
    public void setOverDue() throws ParseException {
        List<Task> tasksToCheck = null;
        List<Task>  tasksToSetOverDue = new ArrayList<Task>();
        Date today = new Date();
        tasksToCheck = dao.withdrawDueTasks();
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        Date dateFromDB = null;
        for (Task element: tasksToCheck)
        {

            dateFromDB = dateFormat.parse(element.getDeadline());
            System.out.println(today + " " + dateFromDB);
            if( today.after(dateFromDB)){
                tasksToSetOverDue.add(element);

            }
        }
        dao.setOverDueTasks(tasksToSetOverDue);


    }
}
