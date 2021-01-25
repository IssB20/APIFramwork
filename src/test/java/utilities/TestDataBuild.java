package utilities;

import pojo.Location;
import pojo.MapLocation;

import java.util.Arrays;

public class TestDataBuild {

    public MapLocation addPlacePayload(String name, String language, String address){
        MapLocation p = new MapLocation();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("111-222-3333");
        p.setWebsite("https://jira.com");
        p.setName(name);
        p.setTypes(Arrays.asList("shop","shoe","barber"));

        Location l = new Location();
        l.setLat(-22.221199);
        l.setLng(44.445533);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String place_id){

        return "{\"place_id\": \""+place_id+"\"}";
    }
}
