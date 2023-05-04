import java.util.ArrayList;

// Class that defines place objects

    public class Place { 
        String name;
        String description;
        ArrayList<String> exits;

        public Place(String name, String description) {
            exits = new ArrayList<String>();
            this.name = name;
            this.description = description;
            }

        public String getName() {
            return name;
            }

        public void addExit(String exitName) {
            exits.add(exitName);
            }


        public void listExits() {
            for (int i = 0; i < exits.size(); i++) {
                System.out.println(exits.get(i));
            }
            }

        public ArrayList<String> getExits() {
            return exits;
            }

        public String toString() {
            return description;
            }


    }
