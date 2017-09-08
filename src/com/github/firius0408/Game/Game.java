package com.github.firius0408.Game;
import java.util.Scanner;
import java.util.Random;
public class Game {
    public int[] points;
    public int currentHealth;
    public int exp = 0;
    public Scanner inputInt = new Scanner(System.in);
    public Scanner inputString = new Scanner(System.in);
    public Random random = new Random();
    public String userName;

    public static void main(String[] args){
        Game game = new Game();
        game.startGame();
    }

    public void startGame(){
        System.out.println("Welcome to our game.\nLet us begin...\n\n\nWhat is your name?");
        userName = inputString.nextLine();
        System.out.println("Welcome " + userName + "\nTime to delegate your stats.\nYou have 25 points to distribute to the following 5 categories,\n with a maximum of 10 points per category.\nEach category must have at least 1 point");
        int[] array = Points(); // holds all the points in each category
        while (array[0] != 25){
            System.out.println("Your points did not add up to 25 or you ran out of points. Please try again");
            array = Points();
        }
        points = array;
        System.out.println("These are your points:");
        System.out.println("intelligence: " + points[1]);
        System.out.println("agility: " + points[2]);
        System.out.println("power: " + points[3]);
        System.out.println("health: " + points[4]);
        System.out.println("magic: " + points[5]);
        currentHealth = points[4] * 100;
        Beginning();
    }

    public void Beginning() {
        System.out.println("The ground is scorched, but you can’t remember why. There are ashes scattered across the ground.\nSuddenly, the black dust begins to swirl like a small tornado. You begin to see a shape formulating, an animal.\nOnce the ash clears, a phoenix stands tall and roars in your direction.\nYou’re out of its fighting range for now, but it might be able to catch you.");
        Fight("Phoenix", 3, 5, 4, "With one final swing of your sword, you vanquish the phoenix, its body falling onto the ground", "You lose", "You bend over, out of breath. Behind you, the phoenix roars in frustration, scouring the ground for your presence");
        System.out.println("The land of ________ was at peace with the\nland. The weather had been perfect for agriculture for\nmany years now, so a surplus of food caused the \nnation to flourish. Standard of living went up, \npeople were happy, and marijuana was legal.\n\tBut then the fire nation attacked.\nThey used flamethrowers, fireballs, and \nanything else with fire involved. Matches and lighters \ntoo. Every house in ________ was burned to \nthe ground, and the grass was scorched as well. \nMost of _____’s citizens evacuated within a week \nof the attack, but you, " + userName + ", have stayed for \nsome reason. What reason, you cannot \nremember. But surely it’ll come to you sometime.\n \tRight?");
        System.out.println("You are now faced with a difficult choice. You can walk west towards the plains, or you can walk north towards the mountains.\nWhich do you choose?");
        String choice = inputString.nextLine();
        while(!choice.equalsIgnoreCase("plains") && !choice.equalsIgnoreCase("mountains")){
            System.out.println("Plains or Mountains?");
            choice = inputString.nextLine();
        }
        if (choice.equalsIgnoreCase("plains")){
            Plains();
        }
        else {
            Mountains();
        }
    }

    public void Plains(){
        System.out.println("You choose to make the trek across the grueling scorched earth. The sun begins to drop along the horizon.\nEventually, though, you see a faint outline of a ______(building) in the distance. With little water left in your flask,\nyou can either sleep where you are or expend whatever energy it takes to get to _____.\nSleep until the next day or continue?");
        String trekSleep = inputString.nextLine();
        while(!trekSleep.equalsIgnoreCase("sleep") && !trekSleep.equalsIgnoreCase("continue")){
            System.out.println("Sleep or Continue?");
            trekSleep = inputString.nextLine();
        }
        if (trekSleep.equalsIgnoreCase("sleep")){
            System.out.println("You wake up in a cold sweat. Soft padded footsteps are sneaking towards you. You wait until they are close,\nand stand up to face the largest wolf you’ve ever seen.");
            Fight("Wolf", 3, 4, 6, "You win", "You lose", "You ran");
        }
        else {
            if (points[2] + random.nextInt(2) < 6){
                System.out.println("Exhaustion sets in. You feel it in your very core. You collapse to the ground, desperately crawling forwards as you vision begins to wane.\nEverything is blurry,\neverything is dark.\n\nYou pass out.");
                System.out.println("You take 200 damage");
                currentHealth = currentHealth - 200;
                if (currentHealth < 0){
                    System.out.println("You lost too much health");
                    endGame();
                }
            }
        }
        System.out.println("Weak and dehydrated, you finally reach the blissful sanctuary of shade in the ________(building).\nYou can feel relief from the scorching sun, and you settle down to relax, you notice the door is locked to the inside,\nbut that is something to worry about later, you think to yourself.\nAs you relax, you ponder over the past events.");
        
    }

    public void Mountains() {
        System.out.println("After toiling through the troubles of the desert, you find yourself approaching a daunting mountain range.\nBy the time you arrive at the base of the first mountain, you see a storm brewing on the horizon,\nurging you onwards.\nHere, you see a large cave opening leading straight through the mountain,\nfrom which you hear an ominous growl. At this point, you can either go through the cave for shelter\nor start the arduous process of climbing the mountain. What will you do?");
        
    }

    public void endGame(){
        System.out.println("You lost. You must suck at life");
        System.out.println("Would you like to play again: [y/n]");
        String playAgain = inputString.nextLine();
        while(!playAgain.equalsIgnoreCase("y") && !playAgain.equalsIgnoreCase("n")){
            System.out.println("Would you like to play again: [y/n]");
            playAgain = inputString.nextLine();
        }
        if (playAgain.equalsIgnoreCase("y")){
            startGame();
        }
        else {
            System.out.println("Goodbye");
        }
    }

    public void Fight(String name, int power, int health, int agility, String win, String lose, String run){
        System.out.println("Would you like to fight or run?");
        String choice = inputString.nextLine();
        while (!choice.equalsIgnoreCase("fight") && !choice.equalsIgnoreCase("run")) {
            System.out.println("Would you like to fight or run?");
            choice = inputString.nextLine(); 
        }
        if (choice.equals("fight")){
            System.out.println("You have choosen to fight");
            int powerUser = points[3];
            health = health*20;
            if (agility > points[2]){
                System.out.println("The " + name + " moves more swiftly than you do, and it attacks first");
                while(currentHealth > 0 && health > 0){
                    if (health > 0){
                        int damage = random.nextInt(6) + power;
                        currentHealth = currentHealth - damage;
                        System.out.println("The " + name + " hits you with " + damage + " damage, leaving you with " + currentHealth + " health");
                    }
                    if (currentHealth > 0){
                        int damageUser = random.nextInt(6) + powerUser;
                        health = health - damageUser;
                        System.out.println("You hit the " + name + " with " + damageUser + " damage, leaving it with " + health + " health");
                    }
                }
                if (currentHealth <= 0){
                    System.out.println(lose);
                    endGame();
                }
                else if (health <= 0){
                    System.out.println(win);
                    exp = exp + 10;
                    System.out.println("Your xp is now " + exp);
                }
                else {
                    System.out.println("shit");
                }
            }
            else {
                System.out.println("The " + name + " moves slowly, and you attack first");
                while(currentHealth > 0 && health > 0){
                    if (currentHealth > 0){
                        int damageUser = random.nextInt(6) + powerUser;
                        health = health - damageUser;
                        System.out.println("You hit the " + name + " with " + damageUser + " damage, leaving it with " + health + " health");
                    }
                    if (health > 0){
                        int damage = random.nextInt(6) + power;
                        currentHealth = currentHealth - damage;
                        System.out.println("The " + name + " hits you with " + damage + " damage, leaving you with " + currentHealth + " health");
                    }
                }
                if (currentHealth <= 0){
                    System.out.println(lose);
                    endGame();
                }
                else if (health <= 0){
                    System.out.println(win);
                    exp = exp + 10;
                    System.out.println("Your xp is now " + exp);
                }
                else {
                    System.out.println("shit");
                }
            }
        }
        else if(choice.equals("run")){
            if ((random.nextInt(5) + points[2]) > agility) {
                System.out.println("You have successfully escaped\n");
                System.out.println(run);
            }
            else {
                System.out.println("You tried to escape, but you are not fast enough");
            }
        }
        else {
        }

    }
    
    public int[] Points(){
        int points = 0;
        int intelligence1 = 11;
        int agility1 = 11;
        int power1 = 11;
        int health1 = 11;
        int magic1 = 11;
        int[] array = {points, intelligence1, agility1, power1, health1, magic1};
        while (intelligence1 > 10 || intelligence1 < 1){   
            System.out.println("Points into intelligence:");
            intelligence1 = inputInt.nextInt();
        }
        points = points + intelligence1;
        if (points > 25){
            return array;
        }
        System.out.println("You have " + (25 - points) + " points left to use");
        while (agility1 > 10 || agility1 < 1){   
            System.out.println("Points into agility:");
            agility1 = inputInt.nextInt();
        }
        points = points + agility1;
        if (points > 25){
            return array;
        }
        System.out.println("You have " + (25 - points) + " points left to use");
        while (power1 > 10 || power1 < 1){   
            System.out.println("Points into power:");
            power1 = inputInt.nextInt();
        }
        points = points + power1;
        if (points > 25){
            return array;
        }
        System.out.println("You have " + (25 - points) + " points left to use");
        while (health1 > 10 || health1 < 1){   
            System.out.println("Points into health:");
            health1 = inputInt.nextInt();
        }
        points = points + health1;
        if (points > 25){
            return array;
        }
        System.out.println("You have " + (25 - points) + " points left to use and must use them all in this category");
        while (magic1 > 10){   
            System.out.println("Points into magic:");
            magic1 = inputInt.nextInt();
        }
        points = points + magic1;
        int[] array1 = {points, intelligence1, agility1, power1, health1, magic1};
        return array1;
    }
}