package eapli.management.Domain;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue<AGVTask> {

    private Queue<AGVTask> queue;

    public TaskQueue(){
        this.queue = new LinkedList<>();
    }

    public void addTask(AGVTask agvTask){
        queue.add(agvTask);
    }

    public boolean removeTask(int index){
        boolean result = queue.remove(index);
        return result;
    }

    public void displayTasks(){
        for(AGVTask task : queue)
            System.out.println(task);
    }

    public AGVTask removeHead(){
        return queue.peek();
    }

    public Integer queueSize(){
        return queue.size();
    }
}
