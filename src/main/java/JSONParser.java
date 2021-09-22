import java.util.*;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONParser {
    public ArrayList<Location> getLocationsFromLocation(JSONObject responseJson) {
        ArrayList<Location> locationList = new ArrayList<Location>();

        try {
            JSONArray locations = responseJson.getJSONArray("locations");
            for(int i=0; i<locations.length(); i++) {
                JSONObject data = locations.getJSONObject(i);
                Location location = new Location( data.getInt("id")
                        , data.getInt("located_bikes_count"));
                locationList.add(location);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locationList;
    }

    public ArrayList<Truck> getTrucksFromTruck(JSONObject responseJson) {
        ArrayList<Truck> truckList = new ArrayList<Truck>();
        try {
            JSONArray trucks = responseJson.getJSONArray("trucks");
            for(int i=0; i<trucks.length(); i++) {
                JSONObject data = trucks.getJSONObject(i);
                Truck truck = new Truck(data.getInt("id")
                        , data.getInt("location_id"),
                        data.getInt("loaded_bikes_count"));
                truckList.add(truck);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return truckList;
    }

}
