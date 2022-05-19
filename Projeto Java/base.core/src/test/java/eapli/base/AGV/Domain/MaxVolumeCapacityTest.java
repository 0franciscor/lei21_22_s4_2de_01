package eapli.base.AGV.Domain;

import eapli.base.warehouse.domain.AGVDock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaxVolumeCapacityTest {

    @Test
    void getMaxVolumeCapacity() {
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
        Assertions.assertEquals(a1.getMaxVolumeCapacity(), a2.getMaxVolumeCapacity());
    }

    @Test
    void getMaxVolumeCapacity1() {
        AGVId id = new AGVId("12345678");
        BriefDescription description = new BriefDescription("abcdefg");
        MaxWeightCapacity weight = new MaxWeightCapacity(200.0);
        MaxVolumeCapacity volume = new MaxVolumeCapacity(200);
        MaxVolumeCapacity volume1 = new MaxVolumeCapacity(199);
        Model model = new Model("2.1.1.1");
        ChangeAGVStatus status = new ChangeAGVStatus("FREE");
        Range a = new Range(5.0);
        AGVTask task = new AGVTask("x");
        AGVPosition pos = new AGVPosition("s");
        AGVDock dock = new AGVDock();

        AGV a1 = new AGV(id,description,model,weight,volume,a,pos,dock,status,task);
        AGV a2 = new AGV(id,description,model,weight,volume1,a,pos,dock,status,task);
        Assertions.assertNotEquals(a1.getMaxVolumeCapacity(), a2.getMaxVolumeCapacity());
    }

    @Test
    void setMaxVolumeCapacity() {
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
        volume.setMaxVolumeCapacity(56.0);
        AGV a2 = new AGV(id,description,model,weight,volume,a,pos,dock,status,task);
        Assertions.assertEquals(a1.getMaxVolumeCapacity(), a2.getMaxVolumeCapacity());
    }

    @Test
    void testToString() {
        MaxVolumeCapacity m2 = new MaxVolumeCapacity(10.0);
        String expected = "MaxVolumeCapacity{volume=10.0}";
        Assertions.assertEquals( m2.toString(),expected);
    }

    @Test
    void testToString2() {
        MaxVolumeCapacity m = new MaxVolumeCapacity();
        MaxVolumeCapacity m2 = new MaxVolumeCapacity(10.0);
        Assertions.assertNotEquals(m.toString(), m2.toString());
    }
}