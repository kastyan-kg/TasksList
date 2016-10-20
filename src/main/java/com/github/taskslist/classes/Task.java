package com.github.taskslist.classes;

/**
 * Created by kos on 19-Oct-16.
 */
public class Task {

    private int id;
    private String name;
    private String deadline;
    private String priority;
    private boolean finished;
    private boolean overdue;






    public Task(int id,String name, String deadline, String priority, boolean finished, boolean overdue) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.finished = finished;
        this.overdue = overdue;
    }
    public Task(String name, String deadline, String priority, boolean finished, boolean overdue) {
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.finished = finished;
        this.overdue = overdue;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public void printTask(){
        String actualOverdue = "Due";
        if(isOverdue()){
            actualOverdue = "Overdue";
        }
        System.out.printf("%50s%20s%20s%20s", name, deadline, priority,actualOverdue );

    }
}
