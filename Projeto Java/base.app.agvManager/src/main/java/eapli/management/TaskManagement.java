package eapli.management;

import eapli.base.AGV.Domain.AGVTask;
import eapli.management.Domain.TaskQueue;

public class TaskManagement {

    private TaskQueue<AGVTask> taskQueue;

    public TaskManagement(){
        this.taskQueue = new TaskQueue<>();
    }

    //public boolean assignTasks(){

    //}
}
