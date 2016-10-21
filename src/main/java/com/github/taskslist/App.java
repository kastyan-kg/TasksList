package com.github.taskslist;

import com.github.taskslist.DAO.TaskDAO;
import com.github.taskslist.classes.EntryPoint;
import com.github.taskslist.controllers.TaskService;
import com.github.taskslist.utility.TasksCounterValidator;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;


/**
 * Created by kos on 19-Oct-16.
 */
public class App {


  static final String DB_URL = "jdbc:mysql://localhost/taskslist?characterEncoding=UTF-8&useSSL=false";
   //  Database credentials
  static final String USER = "root";
  static final String PASS = "1812";


    private static DataSource getDataSource()
    {
        //TO DO read settings
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(DB_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);
        return dataSource;
    }

    private static TaskDAO getTaskDAO(DataSource dataSource)
    {
        return new TaskDAO(dataSource);
    }


    public static void main(String []args) {
        TaskDAO taskDAO = getTaskDAO(getDataSource());
        new EntryPoint(
                new TaskService(taskDAO),
                new TasksCounterValidator(taskDAO)).doWork();
    }
}
