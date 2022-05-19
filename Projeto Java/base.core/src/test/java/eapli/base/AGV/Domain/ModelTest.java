package eapli.base.AGV.Domain;

import eapli.base.warehouse.domain.AGVDock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ModelTest {

    @Test
    void getModel() {
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
        Assertions.assertEquals(a1.getModel(), a2.getModel());
    }

    @Test
    void setModel() {
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
        model.setModel("2.1.1.1.");
        AGV a2 = new AGV(id,description,model,weight,volume,a,pos,dock,status,task);
        Assertions.assertEquals(a1.getModel(), a2.getModel());
    }

    @Test
    void testToString() {
        Model m2 = new Model("a");
        String expected = "Model{model='a'}";
        Assertions.assertEquals( m2.toString(),expected);
    }

    @Test
    void testToString2() {
        Model m = new Model();
        Model m2 = new Model("a");
        Assertions.assertNotEquals(m.toString(), m2.toString());
    }
}