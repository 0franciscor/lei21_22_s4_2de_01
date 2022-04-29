package eapli.base.warehouse.domain;

import eapli.base.infrastructure.persistence.PersistenceContext;
import org.json.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonImport {

    public JsonImport(){}

    public boolean importJson(final String fileName){

        Warehouse warehouse;

        try {
            File file = new File(fileName);
            if(!file.exists()){
                System.out.println("The pointed file does not exist.");
                return false;
            }

            Path filePath = Path.of(fileName);
            String content = Files.readString(filePath);
            JSONObject jsonObject = new JSONObject(content);

            warehouse = new Warehouse();
            warehouse.setId((long) 1);

            String description = jsonObject.getString("Warehouse");
            int length = jsonObject.getInt("Length");
            int width = jsonObject.getInt("Width");
            int square = jsonObject.getInt("Square");
            String unit = jsonObject.getString("Unit");

            WarehousePlant warehousePlant = new WarehousePlantBuilder(description, length, width, square, unit).buildWarehouse();
            dataBaseImport(warehousePlant);

            warehouse.setWarehousePlant(warehousePlant); warehouse.setDashboard(new Dashboard());
            dataBaseImport(warehouse);

            importAisles(jsonObject.getJSONArray("Aisles"), warehouse);
            importDocks(jsonObject.getJSONArray("AGVDocks"), warehouse);

        } catch (Exception e){
            //e.printStackTrace();
            System.out.println("There was an error when Importing a .json file data.");
            return false;
        }

        return true;
    }

    private void importAisles(JSONArray array, Warehouse warehouse){

        for(Object object : array) {
            JSONObject arrayObject = (JSONObject) object;

            long id = arrayObject.getLong("Id");
            Begin begin = new Begin(arrayObject.getJSONObject("begin").getInt("lsquare"), arrayObject.getJSONObject("begin").getInt("wsquare"));
            End end = new End(arrayObject.getJSONObject("end").getInt("lsquare"), arrayObject.getJSONObject("end").getInt("wsquare"));
            Depth depth = new Depth(arrayObject.getJSONObject("depth").getInt("lsquare"), arrayObject.getJSONObject("depth").getInt("wsquare"));
            Accessibility accessibility = new Accessibility(arrayObject.getString("accessibility"));

            Aisle aisle = new AisleBuilder(id, warehouse, accessibility, depth, begin, end).build();

            dataBaseImport(aisle);

            JSONArray jsonArray = arrayObject.getJSONArray("rows");

            for(int i = 0; i < jsonArray.length(); i++){
                id = arrayObject.getLong("Id");
                begin = new Begin(jsonArray.getJSONObject(i).getJSONObject("begin").getInt("lsquare"), jsonArray.getJSONObject(i).getJSONObject("begin").getInt("wsquare"));
                end = new End(jsonArray.getJSONObject(i).getJSONObject("end").getInt("lsquare"), jsonArray.getJSONObject(i).getJSONObject("end").getInt("wsquare"));
                int shelves = jsonArray.getJSONObject(i).getInt("shelves");

                dataBaseImport(new LineBuilder(id, aisle, begin, end, shelves).build());
            }
        }
    }

    private void importDocks(JSONArray array, Warehouse warehouse){
        for(int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);

            String id = object.getString("Id");
            Begin begin = new Begin(object.getJSONObject("begin").getInt("lsquare"), object.getJSONObject("begin").getInt("wsquare"));
            End end = new End(object.getJSONObject("end").getInt("lsquare"), object.getJSONObject("end").getInt("wsquare"));
            Depth depth = new Depth(object.getJSONObject("depth").getInt("lsquare"), object.getJSONObject("depth").getInt("wsquare"));
            Accessibility accessibility = new Accessibility(object.getString("accessibility"));

            dataBaseImport(new AGVDockBuilder(id, warehouse, depth, accessibility, begin, end).build());
        }
    }

    private void dataBaseImport(Object object){
        if(object.getClass().equals(Aisle.class)) {
            PersistenceContext.repositories().aisle().save((Aisle) object);
        } else if(object.getClass().equals(Line.class)) {
            PersistenceContext.repositories().line().save((Line) object);
        } else if(object.getClass().equals(WarehousePlant.class)) {
            PersistenceContext.repositories().plant().save((WarehousePlant) object);
        } else if(object.getClass().equals(Warehouse.class)) {
            PersistenceContext.repositories().warehouse().save((Warehouse) object);
        } else if(object.getClass().equals(AGVDock.class)){
            PersistenceContext.repositories().dock().save((AGVDock) object);
        }
    }
}
