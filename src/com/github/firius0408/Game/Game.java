package com.github.firius0408.Game;

import java.util.Scanner;
import java.util.Random;

public class Game {
    public int[] points; // intelligence -> 1, agility -> 2, power -> 3, health -> 4, magic -> 5
    public int currentHealth; // maintains health after fights
    public int exp = 0;
    public Scanner inputString = new Scanner(System.in); // use for strings
    public Random random = new Random(); //use for random integers ex: random.nextInt(highest_number_in_range_here)
    public String userName;

    public static void main(String[] args) {
        Game game = new Game(); //creates new instance of game and calls startGame method
        game.startGame();
    }
    //TODO add save file? Inventory? Armor? Food? What to do with exp? Scrolling text or waiting for user input
    public void startGame() {
        System.out.println("Welcome to our game.\nLet us begin...\n\n\nWhat is your name?");
        userName = inputString.nextLine();
        System.out.println("Welcome, " + userName
            + ". It's time to delegate your stats.\nYou have 25 points to distribute to the following 5 categories:\nIntelligence, Agility, Power, Health, and Magic.\nEach category must have at least 1 point, and at most 10 points.");
        points = Points();
        System.out.println("You have distributed your points accordingly:");
        System.out.println("Intelligence: " + points[1]);
        System.out.println("Agility: " + points[2]);
        System.out.println("Power: " + points[3]);
        System.out.println("Health: " + points[4]);
        System.out.println("Magic: " + points[5]);
        currentHealth = points[4] * 100;
        Beginning(); //calls Beginning method
    }

    public void Beginning() {
        System.out.println( // plot stuff
            "\n\n\tThe ground is scorched, but you can't remember why. There are ashes scattered across the ground.\nSuddenly, the black dust begins to swirl like a small tornado. You begin to see a shape formulating, an\nanimal. Once the ash clears, a phoenix stands tall and roars in your direction. You're out of its fighting\nrange for now, but it might be able to catch you.\n");
        Fight("Phoenix", 3, 5, 4, 10, //fight Phoenix
            "With one final swing of your sword, you vanquish the phoenix, its body falling onto the ground.",
            "You become overwhelmed by the wrath of the Phoenix. After a grueling battle, you submit and become engulfed by its flames.",
            "You bend over, out of breath. Behind you, the phoenix roars in frustration, scouring the ground for your presence.");
        System.out.println(
            "\n\tThe land of Skoll was at peace with the world. The weather had been perfect\nfor agriculture for many years now, the nation flourishing with a surplus of food and\nwealth. The standard of living went up, people were happy, and marijuana was legal.\n\tBut then the fire nation attacked.\n\tThey used flamethrowers, fireballs, and anything else with fire involved.\nMatches and lighters too. Every house in Skoll was burned to the ground, and the\ngrass was scorched as well. Most of Skoll's citizens evacuated within a week of the\nattack, but you, "
            + userName
            + ", have stayed behind. For what reason, you cannot remember.\nBut surely it'll come to you sometime.\n \tRight?");
        System.out.println(
            "\nYou are now faced with a difficult choice. You can walk west towards the plains, or you can walk north towards the mountains.\nWhich do you choose?");
        String choice = inputString.nextLine(); // choice of plains or mountains
        while (!choice.equalsIgnoreCase("plains") && !choice.equalsIgnoreCase("mountains")) {
            System.out.println("Plains or Mountains?");
            choice = inputString.nextLine();
        }
        if (choice.equalsIgnoreCase("plains")) {
            Plains();
        } else {
            Mountains();
        }
    }

    public void Plains() { // plains path
        System.out.println(
            "You choose to make the trek across the grueling scorched earth. The sun begins to drop along the horizon.\nEventually, though, you see a faint outline of a ______(building) in the distance. With little water left in your flask,and the sun high in the sky, \nyou can either rest where you are or expend whatever energy it takes to get to _____.\nRest until it's cooler or continue?");
        String trekRest = inputString.nextLine();
        while (!trekRest.equalsIgnoreCase("rest") && !trekRest.equalsIgnoreCase("continue")) { // sleep or continue
            System.out.println("Rest or Continue?");
            trekRest = inputString.nextLine();
        }
        if (trekRest.equalsIgnoreCase("rest")) { //sleep leads to fight with Wolf
            System.out.println(
                "You wake up in a cold sweat. Soft padded footsteps are sneaking towards you. You wait until they are close,\nand stand up to face the largest wolf you've ever seen.");
            Fight("Wolf", 3, 4, 6, 10,
                "You stand tall, arms outstretched above your head and sword gleaming in the moonlight. You let the sword’s weight and momentum carry it downwards onto the wolf,\nand you strike a deadly blow onto the wolf’s neck. It is defeated.", 
                "You lie sprawled out along the ground, your strength waning. The wolf strolls up, looking downwards, gazing into your face.\nThen it pounces.", 
                "You ran"); //TODO add better run lines
        } else {
            if (points[2] + random.nextInt(2) < 6) { // continue, check agility 
                System.out.println( // too low, take damage
                    "Exhaustion sets in. You feel it in your very core. You collapse to the ground, desperately crawling forwards as you vision begins to wane.\nEverything is blurry,\neverything is dark.\n\nYou pass out.");
                System.out.println("You take 200 damage");
                currentHealth = currentHealth - 200;
                if (currentHealth < 0) {
                    System.out.println("You lost too much health");
                    endGame();
                }
            }
        }
        System.out.println( // both paths converge here
            "Weak and dehydrated, you finally reach the blissful sanctuary of shade in the ________(building).\nYou can feel relief from the scorching sun, and you settle down to relax, you notice the door is locked to the inside,\nbut that is something to worry about later, you think to yourself.\nAs you relax, you ponder over the past events.");
        System.out.println("\nBefore long, the sky begins to redden. The sun slowly sinks beneath the horizon,\npainting the sky with reds and oranges, reds and oranges that remind you yet again of the trouble you now face.\nWhy does everything remind you of fire? Nevermind that, now you need shelter. You turn to the house.\nLocked, just like you remember. You search around the ______(building), and find a piece of rusty wire.\nYou attempt to pick the lock.");
        if (points[1] + random.nextInt(3) >= 7){
            System.out.println("You successfully pick the lock.");
        }
        else {
            System.out.println("Unsuccessful with the lock, you attempt to kick down the door.");
            if (points[3] + random.nextInt(3) >= 6){
                System.out.println("You successfully kick down the door.");
            }
            else {
                System.out.println("Unable to break into the house, you sleep the outside in the cold night, taking 100 damage from hypothermia");
                currentHealth = currentHealth - 100;
            }
        }

    }

    public void Mountains() {
        System.out.println(
            "After toiling through the troubles of the desert, you find yourself approaching a daunting mountain range.\nBy the time you arrive at the base of the first mountain, you see a storm brewing on the horizon,\nurging you onwards.\nHere, you see a large cave opening leading straight through the mountain,\nfrom which you hear an ominous growl. At this point, you can either go through the cave for shelter\nor start the arduous process of climbing the mountain. What will you do?");

    }

    public void endGame() { // end game method
        System.out.println("You lost. You must suck at life");
        String playAgain;
        do {
            System.out.println("Would you like to play again: [y/n]");
            playAgain = inputString.nextLine();
        } while (!playAgain.equalsIgnoreCase("y") && !playAgain.equalsIgnoreCase("n"));
        if (playAgain.equalsIgnoreCase("y")) {
            startGame(); // restarts game if yes
        } else {
            System.out.println("Goodbye"); //TODO close window?
        }
    }

    public void Fight(String name, int power, int health, int agility, int exp, String win, String lose, String run) { // fight method, could be cleaned up a bit?
        String[] enemyHits = { "hits", "strikes", "scratches", "swipes at", "smacks", "screeches at", "shakes",
                "batters", "pounds", "snarls at" };
        String[] userHits = { "hit", "strike", "scratch", "swipe at", "smack", "punch", "shake", "batter", "pound",
                "kick" };
        String choice;
        do {
            System.out.println("Would you like to fight or run?");
            choice = inputString.nextLine();
        } while (!choice.equalsIgnoreCase("fight") && !choice.equalsIgnoreCase("run"));
        if (choice.equalsIgnoreCase("fight")) { // fight is chosen
            System.out.println("You have choosen to fight!");
            int powerUser = points[3];
            health = health * 20;
            if (agility > points[2]) { // checks to see who attacks first
                System.out.println("The " + name + " moves more swiftly than you do, and it attacks first.");
                while (currentHealth > 0 && health > 0) {
                    if (health > 0) {
                        int damage = random.nextInt(6) + power;
                        currentHealth = currentHealth - damage;
                        System.out.println("The " + name + " " + enemyHits[(int) (Math.random() * 10)] + " you with "
                            + damage + " damage, leaving you with " + currentHealth + " health.");
                    }
                    if (currentHealth > 0) {
                        int damageUser = random.nextInt(6) + powerUser;
                        health = health - damageUser;
                        System.out.println("You " + userHits[(int) (Math.random() * 10)] + " the " + name + " with "
                            + damageUser + " damage, leaving it with " + health + " health.");
                    }
                }
                if (currentHealth <= 0) {
                    System.out.println(lose);
                    endGame();
                } else if (health <= 0) {
                    System.out.println(win);
                    exp = exp + this.exp;
                    System.out.println("Your xp is now " + exp + ".");
                } else {
                    System.out.println("shit");
                }
            } else {
                System.out.println("The " + name + " moves slowly, and you attack first.");
                while (currentHealth > 0 && health > 0) {
                    if (currentHealth > 0) {
                        int damageUser = random.nextInt(6) + powerUser;
                        health = health - damageUser;
                        System.out.println("You " + userHits[(int) (Math.random() * 10)] + " the " + name + " with "
                            + damageUser + " damage, leaving it with " + health + " health.");
                    }
                    if (health > 0) {
                        int damage = random.nextInt(6) + power;
                        currentHealth = currentHealth - damage;
                        System.out
                        .println("The " + name + " " + enemyHits[(int) (Math.random() * 10)] + " you, dealing "
                            + damage + " damage, leaving you with " + currentHealth + " health.");
                    }
                }
                if (currentHealth <= 0) {
                    System.out.println(lose);
                    endGame();
                } else if (health <= 0) {
                    System.out.println("\n" + win);
                    exp = exp + this.exp;
                    System.out.println("Your xp is now " + exp + ".");
                } else {
                    System.out.println("shit");
                }
            }
        } else if (choice.equalsIgnoreCase("run")) { // run is chosen
            if ((random.nextInt(5) + points[2]) > agility) {
                System.out.println("You have successfully escaped!\n");
                System.out.println(run);
            } else {
                System.out.println("You tried to escape, but you are not fast enough."); // goes back to fight
                int powerUser = points[3];
                health = health * 20;
                while (currentHealth > 0 && health > 0) {
                    if (health > 0) {
                        int damage = random.nextInt(6) + power;
                        currentHealth = currentHealth - damage;
                        System.out.println("The " + name + " " + enemyHits[(int) (Math.random() * 10)] + " you with "
                            + damage + " damage, leaving you with " + currentHealth + " health.");
                    }
                    if (currentHealth > 0) {
                        int damageUser = random.nextInt(6) + powerUser;
                        health = health - damageUser;
                        System.out.println("You " + userHits[(int) (Math.random() * 10)] + " the " + name + " with "
                            + damageUser + " damage, leaving it with " + health + " health.");
                    }
                }
                if (currentHealth <= 0) {
                    System.out.println(lose);
                    endGame();
                } else if (health <= 0) {
                    System.out.println(win);
                    exp = exp + this.exp;
                    System.out.println("Your xp is now " + exp + ".");
                } else {
                    System.out.println("shit");
                }
            }
        } else {
            System.out.println("shit");
        }
    }

    public int CheckInt() { // prompts user for integer
        String temp = inputString.nextLine();
        while(!isInteger(temp)){
            System.out.println("Your input was not an integer. Try again");
            temp = inputString.nextLine();
            isInteger(temp);
        }
        return Integer.parseInt(temp);
    }

    public boolean isInteger(String str) { // checks user input to ensure it's an integer
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public int[] Points() { // determines the delegation of points
        int points;
        int intelligence = 0;
        int agility = 0;
        int power = 0;
        int health = 0;
        int magic = 0;
        do { // checks for points add to 25
            points = 0;
            while (true){ //loop for break
                System.out.println("How many points would you like to assign to intelligence?");
                intelligence = CheckInt();
                while (intelligence > 10 || intelligence < 1) {
                    System.out.println("That's not between 1 and 10. Please input a different value.");
                    System.out.println("How many points would you like to assign to intelligence?");
                    intelligence = CheckInt();
                }
                points = points + intelligence;
                if (points > 25){
                    break;
                }
                System.out.println("You have " + (25 - points) + " points left to use.");
                System.out.println("How many points would you like to assign to agility?");
                agility = CheckInt();
                while (agility > 10 || agility < 1) {
                    System.out.println("That's not between 1 and 10. Please input a different value.");
                    System.out.println("How many points would you like to assign to agility?");
                    agility = CheckInt();
                }
                points = points + agility;
                if (points > 25){
                    break;
                }
                System.out.println("You have " + (25 - points) + " points left to use.");
                System.out.println("How many points would you like to assign to power?");
                power = CheckInt();
                while (power > 10 || power < 1) {
                    System.out.println("That's not between 1 and 10. Please input a different value.");
                    System.out.println("How many points would you like to assign to power?");
                    power = CheckInt();
                }
                points = points + power;
                if (points > 25){
                    break;
                }
                System.out.println("You have " + (25 - points) + " points left to use.");
                System.out.println("How many points would you like to assign to health?");
                health = CheckInt();
                while (health > 10 || health < 1) {
                    System.out.println("That's not between 1 and 10. Please input a different value.");
                    System.out.println("How many points would you like to assign to health?");
                    health = CheckInt();
                }
                points = points + health;
                if (points > 25){
                    break;
                }
                System.out.println("You have " + (25 - points)
                    + " points left to use, and all of your remaining points must be assigned to magic.");
                System.out.println("How many points would you like to assign to magic?");
                magic = CheckInt();
                while (magic > 10 || magic < 1) {
                    System.out.println("That's not between 1 and 10. Please input a different value.");
                    System.out.println("How many points would you like to assign to magic?");
                    magic = CheckInt();
                }
                points = points + magic;
                break;
            }
            if (points != 25){ 
                System.out.println("Your points did not add up to 25. Please try again.");
            }
        } while (points != 25);
        int[] arrayOfPoints = { points, intelligence, agility, power, health, magic };
        return arrayOfPoints;
    }
}