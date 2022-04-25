package eapli.base.warehouse.domain;

import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonImport {

    public static void main(String[] args) throws IOException {

        Path filePath = Path.of("warehouse1.json");
        String content = Files.readString(filePath);
        JSONObject jsonObject = new JSONObject(content);

        String description = jsonObject.getString("Warehouse");
        int length = jsonObject.getInt("Length");
        int width = jsonObject.getInt("Width");
        int square = jsonObject.getInt("Square");
        String unit = jsonObject.getString("Unit");

        JSONArray array = jsonObject.getJSONArray("Aisles");

        List<Aisle> aisleList = new ArrayList<>();
        for(Object object : array) {
            JSONObject arrayObject = (JSONObject) object;

            Long id = arrayObject.getLong("Id");
            Begin begin = new Begin(arrayObject.getJSONObject("begin").getInt("lsquare"), arrayObject.getJSONObject("begin").getInt("wsquare"));
            End end = new End(arrayObject.getJSONObject("end").getInt("lsquare"), arrayObject.getJSONObject("end").getInt("wsquare"));
            Depth depth = new Depth(arrayObject.getJSONObject("depth").getInt("lsquare"), arrayObject.getJSONObject("depth").getInt("wsquare"));
            Accessibility accessibility = new Accessibility(arrayObject.getString("accessibility"));

            JSONArray jsonArray = arrayObject.getJSONArray("rows");

            List<Row> rowList = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                id = arrayObject.getLong("Id");
                begin = new Begin(jsonArray.getJSONObject(i).getJSONObject("begin").getInt("lsquare"), jsonArray.getJSONObject(i).getJSONObject("begin").getInt("wsquare"));
                end = new End(jsonArray.getJSONObject(i).getJSONObject("end").getInt("lsquare"), jsonArray.getJSONObject(i).getJSONObject("end").getInt("wsquare"));
                int shelves = jsonArray.getJSONObject(i).getInt("shelves");

                rowList.add(new Row(id, begin, end, shelves));
            }
            aisleList.add(new Aisle(id, accessibility, depth, begin, end, rowList));
        }

        JSONArray jsonArray = jsonObject.getJSONArray("AGVDocks");

        List<AGVDock> dockList = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);

            String id = object.getString("Id");
            Begin begin = new Begin(object.getJSONObject("begin").getInt("lsquare"), object.getJSONObject("begin").getInt("wsquare"));
            End end = new End(object.getJSONObject("end").getInt("lsquare"), object.getJSONObject("end").getInt("wsquare"));
            Depth depth = new Depth(object.getJSONObject("depth").getInt("lsquare"), object.getJSONObject("depth").getInt("wsquare"));
            Accessibility accessibility = new Accessibility(object.getString("Id"));

            dockList.add(new AGVDock(id, depth, accessibility, begin, end));

        }

        WarehousePlant warehousePlant = new WarehousePlant(description, length, width, square, unit, dockList, aisleList);
    }
}
