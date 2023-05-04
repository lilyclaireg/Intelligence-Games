import java.util.HashMap;

// Map to store the places on the arenaMap

public class ArenaMap {
   HashMap<String, Place> map;


   public ArenaMap() {
       map = new HashMap<>();
   }


   public void addPlace(Place place) {
       map.put(place.getName(), place);
   }


   public Place getPlace(String placeName) {
       return map.get(placeName);
   }
}
