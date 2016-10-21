package com.github.taskslist.classes;

import java.util.Date;

/**
 * Created by kos on 19-Oct-16.
 */
public class Task {

    private Integer id;
    private String name;
    private Date deadline;
    private PriorityType priority;
    private boolean finished;

    public Task(Integer id, String name, Date deadline, PriorityType priority, boolean finished) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.finished = finished;
    }
    public Task(String name, Date deadline, PriorityType priority, boolean finished) {
        this(null, name, deadline, priority, finished);
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isOverdue() {
        return (!finished) && (deadline.compareTo(new Date()) < 0);
    }

    public Date getDeadline() {
        return deadline;
    }

    public void printTask(){
        String actualOverdue = "Due";
        if(isOverdue()){
            actualOverdue = "Overdue";
        }
        System.out.printf("%40s%40s%10s%10s", name, deadline, priority,actualOverdue);

    }
}
