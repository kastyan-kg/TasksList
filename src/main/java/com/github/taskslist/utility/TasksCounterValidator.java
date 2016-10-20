package com.github.taskslist.utility;

import com.github.taskslist.DAO.TaskDAO;

/**
 * Created by kos on 20-Oct-16.
 */
public class TasksCounterValidator {

    public boolean checkPossibleTaskNuber(int finishedTask){
        TaskDAO dao = new TaskDAO();
        int numberOfTasks = dao.countTasks();
        if(0 < finishedTask && finishedTask <= numberOfTasks){
            return true;
        }else{
            return false;
        }

    }
}
