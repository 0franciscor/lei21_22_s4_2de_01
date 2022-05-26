package eapli.base.AGV.Domain;

import eapli.base.warehouse.domain.AGVDock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AGVTaskTest {

    @Test
    void getAgvTask() {
        AGVId id = new AGVId("12345678");
        BriefDescription description = new BriefDescription("abcdefg");
        MaxWeightCapacity weight = new MaxWeightCapacity(200.0);
        MaxVolumeCapacity volume = new MaxVolumeCapacity(200);
        Model model = new Model("2.1.1.1");
        ChangeAGVStatus status = new ChangeAGVStatus("FREE");
        Range a = new Range(5.0);
        AGVTask task = new AGVTask("x");
        AGVPosition pos = new AGVPosition("s");
        AGVDock dock = new AGVDock();

        AGV a1 = new AGV(id,description,model,weight,volume,a,pos,dock,status,task);
        AGV a2 = new AGV(id,description,model,weight,volume,a,pos,dock,status,task);
        Assertions.assertEquals(a1.getAgvTask(), a2.getAgvTask());
    }

   /* @Test
    void setAgvTask() {
        AGVId id = new AGVId("12345678");
        BriefDescription description = new BriefDescription("abcdefg");
        MaxWeightCapacity weight = new MaxWeightCapacity(200.0);
        MaxVolumeCapacity volume = new MaxVolumeCapacity(200);
        Model model = new Model("2.1.1.1");
        ChangeAGVStatus status = new ChangeAGVStatus("FREE");
        Range a = new Range(5.0);
        AGVTask task = new AGVTask("x");
        AGVPosition pos = new AGVPosition("s");
        AGVDock dock = new AGVDock();

        AGV a1 = new AGV(id,description,model,weight,volume,a,pos,dock,status,task);
        task.setDescription("x");
        AGV a2 = new AGV(id,description,model,weight,volume,a,pos,dock,status,task);
        Assertions.assertEquals(a1.getAgvTask(), a2.getAgvTask());
    }

    @Test
    void setAgvTask1() {
        AGVId id = new AGVId("12345678");
        BriefDescription description = new BriefDescription("abcdefg");
        MaxWeightCapacity weight = new MaxWeightCapacity(200.0);
        MaxVolumeCapacity volume = new MaxVolumeCapacity(200);
        Model model = new Model("2.1.1.1");
        ChangeAGVStatus status = new ChangeAGVStatus("FREE");
        Range a = new Range(5.0);
        AGVTask task = new AGVTask("x");
        AGVTask t1 = new AGVTask("x");
        AGVPosition pos = new AGVPosition("s");
        AGVDock dock = new AGVDock();

        AGV a1 = new AGV(id,description,model,weight,volume,a,pos,dock,status,task);
        task.setDescription("y");
        AGV a2 = new AGV(id,description,model,weight,volume,a,pos,dock,status,t1);
        Assertions.assertNotEquals(a1.getAgvTask(), a2.getAgvTask());
    } */

    @Test
    void testEquals1() {
        AGVTask task1 = new AGVTask("x");
        AGVTask task2 = new AGVTask("x");
        assertNotEquals(task1,task2);
    }

    @Test
    void testToString() {
        AGVTask task1 = new AGVTask("x");
        String expected = "AGVTask{id='x'}";
        Assertions.assertEquals(task1.toString(),expected);
    }

    @Test
    void testToString1() {
        AGVTask task1 = new AGVTask("x");
        String expected = "AGVTask";
        Assertions.assertNotEquals(task1.toString(),expected);
    }

}