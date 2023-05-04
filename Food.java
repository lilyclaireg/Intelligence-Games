import java.util.ArrayList;

// Class that defines food objects
    public class Food { 
        String food;
        String description;

        public Food(String food, String description) {
            this.food = food;
            this.description = description;
            }

        public String getFood() {
            return food;
            }

        public String toString() {
            return description;
            }


    }