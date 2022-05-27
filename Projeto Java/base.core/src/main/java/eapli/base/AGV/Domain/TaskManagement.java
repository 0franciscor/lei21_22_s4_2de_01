package eapli.base.AGV.Domain;

import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.List;

public class TaskManagement {
    private final TaskQueue taskQueue;

    public TaskManagement(){
        this.taskQueue = new TaskQueue();
    }

    public boolean assignTasks(){
        /*
        List<AGV> agvList = (List<AGV>) PersistenceContext.repositories().agv().findAll();
        removeUnfittedAGVs(agvList);

        taskQueue.retrieveTasks();

        AGVTask agvTask;

        for(AGV agv : agvList){
            try {
                agvTask = taskQueue.getHead();
            } catch (Exception e){
                agvTask = null;
            }

            if(agv.getAgvTask() != null) {
                agv.getAgvTask().setStatus(1);
                PersistenceContext.repositories().agvTask().save(agv.getAgvTask());
            }
            agv.getStatus().status = AGVStatus.OCCUPIED_SERVING.toString();
            agvTask.setStatus(1);
            agv.updateTask(agvTask);
            PersistenceContext.repositories().agv().save(agv);
            taskQueue.removeTask(agvTask);
        }*/
        return true;
    }

    /*private void removeUnfittedAGVs(List<AGV> agvList){
        agvList.removeIf(agv -> !agv.getStatus().status.equals("FREE"));
    }*/
}
