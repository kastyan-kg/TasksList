package com.github.taskslist.DAO;

import com.github.taskslist.classes.PriorityType;
import com.github.taskslist.classes.Task;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class TaskDAO {

    private static final String INSERTTASKSQL
            = "INSERT INTO tasks(name, deadline, priority,finished) VALUES(?,?,?,?);";
    private static final String GETTASKSSQL = "SELECT * FROM tasks WHERE finished = 0;";
    private static final String GETFINISHEDTASKSSQL = "SELECT * FROM tasks WHERE finished = 1;";
    private static final String SETFINISHEDSQL = "UPDATE tasks SET finished = 1 WHERE id = ? ;";
    private static final String GETCOUNTSQL = "SELECT COUNT(*) FROM tasks WHERE finished = 0;";

    private DataSource dataSource;
    public TaskDAO(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public void createTask(Task newTask) {

        String name = newTask.getName();
        java.util.Date deadline = newTask.getDeadline();
        PriorityType priority = newTask.getPriority();
        int finished = (newTask.isFinished()) ? 1 : 0;
        ////conn.setAutoCommit(true); by default
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERTTASKSQL);)
        {
            stmt.setString(1, name);
            stmt.setDate(2, new java.sql.Date(deadline.getTime()));

            stmt.setString(3, priority.toString());
            stmt.setInt(4, finished);
            stmt.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public List<Task> showTasks() throws SQLException {
        List<Task> tasks = new ArrayList<Task>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GETTASKSSQL);
        )
        {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(name);
                java.sql.Date deadline = rs.getDate("deadline");
                String priority = rs.getString("priority");
                boolean finished = rs.getBoolean("finished");
                tasks.add(new Task(id, name, new java.util.Date(deadline.getTime()), PriorityType.valueOf(priority), finished));
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return tasks;
    }

    public List<Task> showFinishedTasks() {
        List<Task> tasks = new ArrayList<Task>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GETFINISHEDTASKSSQL);
        )
        {
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                java.sql.Date deadline = rs.getDate("deadline");
                String priority = rs.getString("priority");
                boolean finished = rs.getBoolean("finished");
                tasks.add(new Task(id, name, new java.util.Date(deadline.getTime()), PriorityType.valueOf(priority), finished));

            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return tasks;

    }

    public void finishTask(int id) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SETFINISHEDSQL);)
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException se) {

            se.printStackTrace();
        }
    }

    public int countTasks() {

        int counter = 0;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GETCOUNTSQL);
        )
        {
            while (rs.next()) {
                counter = rs.getInt("count(*)");
            }
        } catch (SQLException se) {

            se.printStackTrace();
        }
        return counter;

    }



}