package com.github.taskslist.DAO;

import com.github.taskslist.classes.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TaskDAO {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/taskslist?characterEncoding=UTF-8&useSSL=false";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "1812";

    public void createTask(Task newTask) {

        String name = newTask.getName();
        String deadline = newTask.getDeadline();
        String priority = newTask.getPriority();
        int finished = (newTask.isFinished()) ? 1 : 0;
        int overdue = (newTask.isOverdue()) ? 1 : 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "INSERT INTO tasks(name, deadline, priority, finished, overdue) "
                    + "VALUES" + "('" + name + "','" + deadline + "','" +
                    priority + "','" + finished + "'," + overdue + ");";
            System.out.println(deadline);
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {

                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public List<Task> showTasks() {
        List<Task> tasks = new ArrayList<Task>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tasks WHERE finished = 0;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String deadline = rs.getString("deadline");
                String priority = rs.getString("priority");
                boolean finished = rs.getBoolean("finished");
                boolean overdue = rs.getBoolean("overdue");
                tasks.add(new Task(id, name, deadline, priority, finished, overdue));


            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return tasks;
    }

    public List<Task> showFinishedTasks() {
        List<Task> tasks = new ArrayList<Task>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tasks WHERE finished = 1;";
            ResultSet rs = stmt.executeQuery(sql);
            int counter = 1;
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String deadline = rs.getString("deadline");
                String priority = rs.getString("priority");
                boolean finished = rs.getBoolean("finished");
                boolean overdue = rs.getBoolean("overdue");
                tasks.add(new Task(id, name, deadline, priority, finished, overdue));
                counter++;

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return tasks;

    }

    public void finishTask(int id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "UPDATE tasks SET finished = 1 WHERE id = " + id + ";";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {

                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


    }

    public int countTasks() {

        Connection conn = null;
        Statement stmt = null;
        int counter = 0;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM tasks WHERE finished = 0;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                counter = rs.getInt("count(*)");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return counter;

    }
    public List<Task> withdrawDueTasks(){
        List<Task> tasks = new ArrayList<Task>();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tasks WHERE finished = 0 AND overdue = 0 ;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                System.out.println("=======================" + id);
                String name = rs.getString("name");
                String deadline = rs.getString("deadline");
                String priority = rs.getString("priority");
                boolean finished = rs.getBoolean("finished");
                boolean overdue = rs.getBoolean("overdue");
                tasks.add(new Task(id, name, deadline, priority, finished, overdue));


            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return tasks;
    }
    public void setOverDueTasks(List<Task> overDueTasks){
        Connection conn = null;
        Statement stmt = null;
        String ids = "0";
        for(Task element : overDueTasks){
                ids += "," + element.getId()  ;
        }
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "UPDATE tasks SET overdue = 1 WHERE id IN (" + ids + ");";
            System.out.println(sql);

            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {

                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


    }
}