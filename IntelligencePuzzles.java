import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

// array could be the things they collect on this mission 
// the answer is somewhere in the world that is unlike any other place'
// beetee code
// answer: some diss about schools, "you found out that the air has no more war toxins and this was created for entertainment of capital"
// classes: main, 
// map could be arena

public class IntelligencePuzzles {

    public static void main(String[] args) {
        Scanner scnr = new Scanner (System.in);
        // Arena map
        ArenaMap arenaMap = new ArenaMap();
        String playerName;
        boolean mountainPuzzleComplete = false;
        boolean forestPuzzleComplete = false;
        boolean riverPuzzleComplete = false;
        boolean allPuzzlesComplete = false;
        boolean realExit = false;
        Place currentPlayerPlace;
        String nextPlace;
        // Array list of exits
        ArrayList<String> realExitList = new ArrayList<String>();

        // Defines each place
        Place cornucopiaPlace = new Place("Cornucopia", "The main part of the arena. The cornucopia is an open field with a giant, metal cornucopia. The cornucopia is where you can access each place.");
        Place mountainPlace = new Place("Mountain of Silence", "A cold, snowy mountain. The sky is streaked with light and dark shades of blue. It's gently snowing, and you can see snowflakes as the sun rises on the horizon.");
        Place forestPlace = new Place ("Forest of Curiosity", "A forest of pine trees over 50 feet tall and 5 feet wide. The floor is covered in pine needles, and birds fly tree to tree. There is a light, cool breeze.");
        Place riverPlace = new Place("River of Mystery", "A wide open meadow with a big, powerful river. The river is strong, almost scary, but beautiful. There is a deep orange sunset that doesn't set, which makes the river peacefully gleam.");  
        Place classroomPlace = new Place("Classroom", "A cold, bright, bare room. There are desks that form linear lines, an empty chalk board, and pale, white lights. There aren't windows, and the walls are formed of pale white bricks. \nThere is a table filled with food: ");

        // Adds exits to each place
        cornucopiaPlace.addExit("Mountain of Silence");
        cornucopiaPlace.addExit("Forest of Curiosity");
        cornucopiaPlace.addExit("River of Mystery");
        mountainPlace.addExit("Cornucopia");
        forestPlace.addExit("Cornucopia");
        riverPlace.addExit("Cornucopia");

        // Adds each place to the arena map
        arenaMap.addPlace(cornucopiaPlace);
        arenaMap.addPlace(forestPlace);
        arenaMap.addPlace(riverPlace);
        arenaMap.addPlace(mountainPlace);
        arenaMap.addPlace(classroomPlace);

        

        
        // Calls method to print details of story.
        backgroundAndSetting(); 
        System.out.println("Prim: \"Hey, don't forget to type your name on the sign-in sheet.\"");
        System.out.println("The computer prompts you, \"Please type in your name.\"");
        playerName = scnr.nextLine();
        System.out.println();
        // Calls method to go to the Reaping.
        theReaping(playerName);


        currentPlayerPlace = cornucopiaPlace;

        // Starts in cornucopia, moves place to place, completing the puzzle at each place.
        while (!allPuzzlesComplete) {
            System.out.print(currentPlayerPlace.getName() + ":");
            System.out.println(" " + currentPlayerPlace.toString());
            System.out.println("Where would you like to go?");
            System.out.println("OPTIONS:");
            currentPlayerPlace.listExits();
            

            for (String e : currentPlayerPlace.getExits()) {
                    realExitList.add(e);
            }

            nextPlace = scnr.nextLine();
            System.out.println();
            
            while(!realExit) {
                if (!(realExitList.contains(nextPlace))) {
                    System.out.println("Invalid exit.");
                    System.out.println();   
                    System.out.println("Please choose an exit");
                    nextPlace = scnr.nextLine();
                    System.out.println();
                } 
                else {
                    realExit = true;
                }
                //compares nextPlace to the possible correct places, if not correct, then it resets the program to the cornucopia.
            }
            realExit = false; 
            // resets real exit so later use of the !realExit while loop can be properly executed
 
            realExitList.clear(); 
            // clears the existing list of good exits so it doesn't recognize a room that exists, but is not tied to the current room

            currentPlayerPlace = arenaMap.getPlace(nextPlace);

            if (currentPlayerPlace == mountainPlace) {
                System.out.print(currentPlayerPlace.getName() + ":");
                System.out.println(" " + currentPlayerPlace.toString());
                mountain();
                mountainPuzzleComplete = true;
                nextPlace = "Cornucopia";
                currentPlayerPlace = arenaMap.getPlace(nextPlace);
            }
            else if (currentPlayerPlace == forestPlace) {
                System.out.print(currentPlayerPlace.getName() + ":");
                System.out.println(" " + currentPlayerPlace.toString());
                forest();
                forestPuzzleComplete = true;
                nextPlace = "Cornucopia";
                currentPlayerPlace = arenaMap.getPlace(nextPlace);
            }
            else if (currentPlayerPlace == riverPlace) {
                System.out.print(currentPlayerPlace.getName() + ":");
                System.out.println(" " + currentPlayerPlace.toString());
                river();
                riverPuzzleComplete = true;
                nextPlace = "Cornucopia";
                currentPlayerPlace = arenaMap.getPlace(nextPlace);
            }

            // Exits loop after all place's puzzles are completed
            if ((mountainPuzzleComplete) && (forestPuzzleComplete) && (riverPuzzleComplete)) {
                allPuzzlesComplete = true;
            }
        }


        // Go to final puzzle with food puzzle
        System.out.println("You hear a voice say: \"" + playerName + ", you must complete one final puzzle before winning the Intelligence Puzzles.");
        System.out.println("You're frustrated by this news- however, you also relate to Katniss, in a way. You must be resilient, you must keep going.");
        boolean userIsReady = false;
        // Ensures player is ready for last place
        while (!userIsReady) {
            System.out.println("The same voice says: \"" + playerName + ", are you ready to enter the final room?\"");
            char readyForFinal = scnr.nextLine().charAt(0);
            System.out.println();
            if ((readyForFinal == 'Y') || (readyForFinal == 'y')) {
                nextPlace = "Classroom";
                currentPlayerPlace = arenaMap.getPlace(nextPlace);
                userIsReady = true;
            }
            else {
                System.out.println("You shall not proceed in winning the games then.");
            }
        }
        System.out.println("You see a fourth door form out of nowhere. It looks uninviting, and cold. It is made of metal, that has a light rust to it from time.");
        System.out.println("Before you enter, you see a little piece of paper, hidden from others, but in your perfect eye sight.");
        System.out.println();
        // Creates a fileName string
        String fileName = String.format("MessageFromKatniss.txt");
        FileOutputStream fos = null;
        // Try catch for filename
        try {
            fos = new FileOutputStream(fileName);
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find " + fileName);
        }
        // Prints out Katniss's note
        PrintWriter outFS = new PrintWriter(fos);
        outFS.println("Dear " + playerName + ", ");
        outFS.println("Goodluck in this last puzzle. I know that you weren't expecting this, but remember who the enemy is.");
        outFS.println("As you complete this puzzle, really think through your answer. The answer is intentional.");
        outFS.println("Remeber to discard this note, as if the Capital finds it, we will be in trouble.");
        outFS.println("Love, Katniss.");
        // Closes print writer
        outFS.close();


        System.out.print(currentPlayerPlace.getName() + ":");
        System.out.println(" " + currentPlayerPlace.toString());
        // Calls final puzzle method
        finalPuzzle();

        

        // Outro statements.
        System.out.println("Congratulations, " + playerName + ", you have won the Intelligence Puzzles!");
        System.out.println("You return to the capital, gaining your riches and new title of a professor. You make your way back to District 12, giving a speech at each district on the way.");
        System.out.println("You return to District 12. You're wealthier than you could ever imagine, and with this wealth, you have time. You spend time thinking, thinking about the Intelligence Puzzles...");
        System.out.println("You solidfy the thought that war toxins were never the issue, it was lack of investing in the Panem Education System.");
        System.out.println("You realize your new role has less to do with bettering the education system, and more to do with convincing the public that it is being bettered.");
        System.out.println("The games were told to be peaceful, but they actually are violent. If you ate the wrong food, you would've died.");
        System.out.println("The leaders of Panem dragged out a remix of the Hunger Games, claiming this is the solution, whereas deconstruction of the Hunger Games is the true solution.");
        System.out.println();
        System.out.println();


        System.out.println("The Intelligence Puzzles are a parallel to the American Education System.");
        System.out.println("Students are lacking a good education in our country, but instead of attributing blame where it should be, we pass the blame.");
        System.out.println("The American education system isn't being better, and it hasn't been. Politicans use band-aid solutions to \"tackle\" issues, but don't even fix them from the root.");
        System.out.println("The goal is to convince the public that our education system is being bettered, not to actually better it. We, in turn, reap the consequences of this.");
        System.out.println("Our education system is outdated. We don't fund our education, and in turn we are lacking when looking at the USA's education globally.");
        System.out.println("We are told that schools are safe, but they're not. School shootings have become a NORM in America- there is absolutely no excuse for this.");
        System.out.println("Band-aid fix after band-aid fix, we as a country refuse to deconstruct then reconstruct our system. As time goes by that we fail to make our public education better, the problem grows, and effects more children each day.");

    }




    // Method that prints background and setting
    public static void backgroundAndSetting() {
        System.out.println("BACKGROUND:");
        System.out.println("Paylor initially ended the Hunger Games after he took over as President of Panem.");
        System.out.println("Panem is thriving, with full peace among each district and the Capital.");
        System.out.println("However, 30 years later Panem has been static; there has been no advancements in technology in 20 years.");
        System.out.println();
        System.out.println("Beety has convinced the people of Panem that the war toxins made the children less smart.");
        System.out.println("Although the war was 30 years ago, and the enviornment has been fully restored, Paylor believes him.");
        System.out.println("Paylor has decided to do a tournament similar to the Hunger Games, but centered around peace, logic, and creativity. GOAL? To make Panem smarter.");
        System.out.println("The tributes are placed in an arena, where they solve varying kinds of puzzles. The first to finish, wins!");
        System.out.println();
        System.out.println("The winner is granted wealth for the rest of their lives, and persue a high-status teaching role for the children of Panem.");
        System.out.println("The loosers are required to go back to and pay for school, which happens to be $50,000 per year.");
        System.out.println();
        System.out.println("The citizens of Panem are VERY upset by doing something similar to the Hunger Games, regardless if they're peaceful.");
        System.out.println("Paylor reassured them that this would help improve Panem's overall intelligence, making them stronger than ever.");
        System.out.println("The citizens ultimately accepted this decision.");
        System.out.println();
        System.out.println();
        System.out.println("SETTING:");
        System.out.println("You are in District 12. You are 18 years old, and this will be the only year you are eligible to be a tribute.");
        System.out.println("You are best friends with Katniss's daughter, Prim. You and Prim love adventuring through the woods.");
        System.out.println("You and have arrived to the reaping, and Effie has just walked on stage.");
        System.out.println();
    }


    // Method that calls the reaping setting of the story.
    public static void theReaping(String userName) {
        Scanner scnr = new Scanner(System.in);
        String enemyName;

        System.out.println("Prim: \"Oh, look there's that guy you hate and fought last week at lunch... What's his name?\"");
        enemyName = scnr.nextLine();
        System.out.println();
        System.out.println("Prim: \"Why do you both hate each other?\"");
        scnr.nextLine();
        System.out.println();
        
        System.out.println("Effie: \"Welcome! Welcome, welcome. Happy Intelligence Puzzles, and may the odds be ever in your favor!\"");
        System.out.println("Effie: \"As usual, ladies first. Ahem, let's see. " + userName + "!\"");
        System.out.println("You walk to the stage, not scared, deeply annoyed. You want to be in the woods, not solving silly puzzles.");
        System.out.println();


        System.out.println("Effie: \"Ah! Congratulations, " + userName + ". Are you just so excited for the puzzles?\"");
        char excited = scnr.nextLine().charAt(0);
        System.out.println();
        if ((excited == 'y') || (excited == 'Y')) {
            System.out.println("Effie: \"OH GOODIE! Me too, me too! We will be so much smarter as a country. Paylor and Beetee are so wise!\"");
            System.out.println(userName + ": * fake smiles to please audience *");
        }
        else if ((excited == 'n') || (excited == 'N')) {
            System.out.println("Effie: \"Now, don't be silly, " + userName + ". You are LUCKY to participate! What an honor.");
            System.out.println(userName + ": * rolls eyes *");
        }
        else {
            System.out.println("Effie: \"Um, what an interesting answer! But I meant yes or no... Better start buying those textbooks!");
        }

        System.out.println("Effie: \"Shall we continue? Now, the boys. One of our local farmers... " + enemyName +"!\"");
        System.out.println();
        System.out.println(enemyName + " walks to the stage. Under his breath, " + enemyName + ": \"Hm, you excited to loose?\"");
        scnr.nextLine();
        System.out.println();
        System.out.println("You arrive at the arena, standing on a platform in the Cornucopia. You are about to go in as you start to hear from the speakers a countdown from 60.");
        System.out.println("The Cornucopia is the hub of the puzzles. There are three total puzzles.");
        System.out.println("\"3, 2, 1 ... And as always, may the odds be EVER in your favor.\"");

    }

    // Method for the mountain place, has user complete the associated puzzle
    public static void mountain() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("You see a pale, white bird fly into a castle made of ice. The bird waits for you as you walk to the castle.");
        System.out.println("As you get closer, the bird flies onto your forearm. You notice how it's feathers glisten like a lake does on a sunny day.");
        System.out.println("The bird sticks by your side, so you decide to name this new friend. What is its name?");
        String birdName = scnr.nextLine();
        System.out.println();
        boolean puzzleComplete = false;
        System.out.println(birdName + " chirps at you, almost as though that was its name the whole time. With " + birdName + ", you enter the castle.");
        while (!puzzleComplete) {
            System.out.println("The castle is gleaming on the inside. You see a glass strucutre in the center of the room, you walk closer to see it is engraved.");
            System.out.println("It reads: \"I am an odd number. Take away a letter and I become even. What number am I? (Please enter in format 4, NOT four)\"");
            int userAnswer = scnr.nextInt();
            System.out.println();
            if (userAnswer == 7) {
                System.out.println("Puzzle completed, congratulations. You will now return to the Cornucopia.");
                puzzleComplete = true;
            }
            else {
                System.out.println("This answer is wrong. Please try again.");
            }
        }
        
    }

    // Method for the forest place, has user complete the associated puzzle
    public static void forest() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("You see a big grizzly bear standing at the entrance of the forest. The bear waits for you as you walk closer, yet it isn't charging at you.");
        System.out.println("The bear goes up to you, but kneals, showing his respect for you. You notice how big and strong he is, yet how gentle he is to you.");
        System.out.println("The bear sticks by your side, so you decide to name this new friend. What is its name?");
        String bearName = scnr.nextLine();
        System.out.println();
        boolean puzzleComplete = false;
        System.out.println(bearName + " looks at you, almost like he is smiling. With " + bearName + ", you enter the forest.");
        while (!puzzleComplete) {
            System.out.println("The forest has greenery everywhere, and a smell so beautiful, like lillies. You see a wood strucutre in the center of the room, you walk closer to see it is engraved.");
            System.out.println("It reads: \"Mary has four daughters, and each of her daughters has a brother. How many children does Mary have? (Please enter in format 4, NOT four)\"");
            int userAnswer = scnr.nextInt();
            System.out.println();
            if (userAnswer == 5) {
                System.out.println("Puzzle completed, congratulations. You will now return to the Cornucopia.");
                puzzleComplete = true;
            }
            else {
                System.out.println("This answer is wrong. Please try again.");
            }
        }
    }

    // Method for the river place, has user complete the associated puzzle
    public static void river() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("You see a doe standing by the river. The doe waits for you as you walk to the river.");
        System.out.println("As you get closer, the doe runs to you. You notice how soft her fur is, and how kind her eyes are.");
        System.out.println("The doe sticks by your side, so you decide to name this new friend. What is its name?");
        String doeName = scnr.nextLine();
        System.out.println();
        boolean puzzleComplete = false;
        System.out.println(doeName + " looks at you, almost like she is asking to be pet. With " + doeName + ", you stand in the river.");
        while (!puzzleComplete) {
            System.out.println("The river is sparkling from the sunset, like diamonds in the sun. You see a message in the water.");
            System.out.println("It reads: \"A little girl goes to the store and buys one dozen eggs. As she is going home, all but three break. How many eggs are left unbroken? (Please enter in format 4, NOT four)\"");
            int userAnswer = scnr.nextInt();
            System.out.println();
            if (userAnswer == 3) {
                System.out.println("Puzzle completed, congratulations. You will now return to the Cornucopia.");
                puzzleComplete = true;
            }
            else {
                System.out.println("This answer is wrong. Please try again.");
            }
        }
    }

    // Method for the final puzzle place, has user complete the associated puzzle
    public static void finalPuzzle() {
        Scanner scnr = new Scanner(System.in);
        Food mushrooms = new Food("Mushrooms", "The mushrooms are cool, and trippy looking. Their coloring is a mix of vivid greens, purples, and oranges.");
        Food candy = new Food("Candy", "You see an assortment of different candies: chocolates, lollipops, gummies, and so much more!");
        Food bark = new Food("Bark", "There is a plate filled with different barks: a chocolate bark, a white chocolate bark, a berry bark, and so on.");
        Food seafood = new Food("Seafood", "A giant platter of seafood stands in front of you. Shrimp, lobster, clams, and calamari fill the plate, and release steam into the air.");
        boolean rightFoodChosen = false;
        
        while (!rightFoodChosen) {
            System.out.print(mushrooms.getFood() + ":");
            System.out.println(" " + mushrooms.toString());
            System.out.print(candy.getFood() + ":");
            System.out.println(" " + candy.toString());
            System.out.print(bark.getFood() + ":");
            System.out.println(" " + bark.toString());
            System.out.print(seafood.getFood() + ":");
            System.out.println(" " + seafood.toString());
            System.out.println();
            System.out.println("The voice prompts you \"Please, choose something to eat.\"");
            char userFood = scnr.nextLine().charAt(0);
            System.out.println();
            if ((userFood == 'M') || (userFood == 'm')) {
                System.out.println("These mushrooms are from Snow's garden, but not the right choice. They end up poisening you, big whoop given it's Snow.");
                System.out.println("Hamitch comes in clutch, per usual, sending you a remedy to cure the poisening. Please choose a different food:");
                System.out.println();
            }
            else if ((userFood == 'C') || (userFood == 'c')) {
                System.out.println("You see a packet of Smarties and think, perhaps this is the answer. Although these are terrible for you, you choose this option.");
                System.out.println("A voice says: \"Congratulations... You have won the Intelligence Games! You will now return home.\"");
                System.out.println();
                rightFoodChosen = true;
            }
            else if ((userFood == 'B') || (userFood == 'b')) {
                System.out.println("The bark is from Johanna's forest, and it's actually real bark, disguised as food. Before you can get pissed off, you realize you're choking.");
                System.out.println("Caesar doesn't want to see this, so he sends you a liquid to stop your choking. Please choose a different food:");
                System.out.println();
            }
            else if ((userFood == 'S') || (userFood == 's')) {
                System.out.println("The seafood is from Finnick's hometown, but spoiled on the trip to the Capitol. You start to get indigestion, and can feel the food coming up...");
                System.out.println("Gale helps you, sending you stomach medicine. Please choose a different food:");
                System.out.println();
            }
            else {
                System.out.println("INVALID FOOD. Please choose a valid food:");
            }
        }

    }

}