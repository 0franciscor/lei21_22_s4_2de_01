package eapli.base.AGVManagement.Domain;

import eapli.base.warehouse.domain.AGVDock;
import org.junit.jupiter.api.Test;

class AGVBuilderTest {

    private static final String AGV_ID = "12345678"; // 8 chars
    private static final String BRIEF_DESCRIPTION = "abcdefghijabcdefghijabcdefghij"; // 30 max
    private static final String MODEL = "abcdefghijabcdefghijabcdefghijabcdefghijabcdefghij"; // 50 max
    private static final String MAX_WEIGHT_CAPACITY = "50";
    private static final String MAX_VOLUME_CAPACITY = "300";
    private static final String RANGE = "5";
    private static final String POSITION = "warehouse";
    private static final String STATUS = "FREE";


    /*@Test
    void build() {
        AGV agv = new AGV(AGV_ID,BRIEF_DESCRIPTION,MODEL,MAX_WEIGHT_CAPACITY,MAX_VOLUME_CAPACITY,RANGE,POSITION,dock,STATUS);
        return new AGVBuilder(agv);
    }*/

    @Test
    void withId() {
    }

    @Test
    void withAGVDock() {
    }

    @Test
    void withRange() {
    }

    @Test
    void withMaxWeightCapacity() {
    }

    @Test
    void withMaxVolumeCapacity() {
    }

    @Test
    void withModel() {
    }

    @Test
    void withBriefDescription() {
    }

    @Test
    void withPosition() {
    }

    @Test
    void withAGVStatus() {
    }

}