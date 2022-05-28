package eapli.base.AGV.Application;

import eapli.base.AGV.domain.TaskQueue;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TaskManagement {

    private final TaskQueue taskQueue;

    private InetAddress serverIP;

    private Socket sock;

    private PrintWriter output;

    private BufferedReader input;

    public static void main(String[] args) throws IOException {

        new TaskManagement().connect("localhost", 8890);
    }

    public void connect(final String address, final int port) throws IOException {
        InetAddress serverIP;

        serverIP = InetAddress.getByName(address);

        sock = new Socket(serverIP, port);
        output = new PrintWriter(sock.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        System.out.printf("Connected to {%s}\n", address);


        output.println("1,1,0");

        sock.close();
    }

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
