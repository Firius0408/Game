package com.github.firius0408.Game;

import java.util.Scanner;
import java.util.Random;

public class Game {
	public int[] points; // intelligence -> 1, agility -> 2, power -> 3, health -> 4, magic -> 5
	public int currentHealth; // maintains health after fights
	public int exp = 0;

	public Scanner inputString = new Scanner(System.in); // use for strings
	public Random random = new Random(); // use for random integers ex: random.nextInt(highest_number_in_range_here)
	public String userName;

	public static void main(String[] args) {
		Game game = new Game(); // creates new instance of game and calls startGame method
		game.startGame();
	}

	// TODO add save file? Inventory? Armor? Weapons? Food? What to do with exp?
	// Scrolling text or waiting for user input
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
		Beginning(); // calls Beginning method
		String choice = CheckString(); // choice of plains or mountains
		while (!choice.equalsIgnoreCase("plains") && !choice.equalsIgnoreCase("mountains")) {
			System.out.println("Plains or Mountains?");
			choice = CheckString();
		}
		if (choice.equalsIgnoreCase("plains")) {
			Plains();
		} else {
			Mountains();
		}
		System.out.println("Which path do you chose? Hills or Forest?");
		String choice2 = CheckString(); // choice of hills or forest
		while (!choice2.equalsIgnoreCase("hills") && !choice2.equalsIgnoreCase("forest")) {
			System.out.println("Hills or Forest?");
			choice2 = CheckString();
		}
		if (choice2.equalsIgnoreCase("hills")) {
			Hills();
		} else {
			Forest();
		}
	}

	public void Beginning() {
		System.out.println( // plot stuff
				"\n\n\tThe ground is scorched, but you can't remember why. There are ashes scattered across the ground.\nSuddenly, the black dust begins to swirl like a small tornado. You begin to see a shape formulating, an\nanimal. Once the ash clears, a phoenix stands tall and roars in your direction. You're out of its fighting\nrange for now, but it might be able to catch you.\n");
		Fight("Phoenix", 3, 5, 4, 10, // fight Phoenix
				"With one final swing of your sword, you vanquish the phoenix, its body falling onto the ground.",
				"You become overwhelmed by the wrath of the Phoenix. After a grueling battle, you submit and become engulfed by its flames.",
				"You bend over, out of breath. Behind you, the phoenix roars in frustration, scouring the ground for your presence.",
				false);
		System.out.println(
				"\nYou look back at the wasteland of what used to be your home. Seeing this image, you try to recall how it ended up this way");
		System.out.println(
				"\n\tThe land of Skoll was at peace with the world. The weather had been perfect\nfor agriculture for many years now, the nation flourishing with a surplus of food and\nwealth. The standard of living went up, people were happy, and marijuana was legal.\n\tBut then the fire nation attacked.\n\tThey used flamethrowers, fireballs, and anything else with fire involved.\nMatches and lighters too. Every house in Skoll was burned to the ground, and the\ngrass was scorched as well. Most of Skoll's citizens evacuated within a week of the\nattack, but you, "
						+ userName
						+ ", have stayed behind. For what reason, you cannot remember.\nBut surely it'll come to you sometime.\n \tRight?");
		System.out.println(
				"\nYou are now faced with a difficult choice. You can walk west towards the plains, or you can walk north towards the mountains.\nWhich do you choose?");
	}

	public void Plains() { // plains path
		System.out.println(
				"You choose to make the trek across the grueling scorched earth. The sun begins to drop along the horizon.\nEventually, though, you see a faint outline of a ______(building) in the distance. With little water left in your flask,and the sun high in the sky, \nyou can either rest where you are or expend whatever energy it takes to get to _____.\nRest until it's cooler or continue?");
		String trekRest = CheckString();
		while (!trekRest.equalsIgnoreCase("rest") && !trekRest.equalsIgnoreCase("continue")) { // sleep or continue
			System.out.println("Rest or Continue?");
			trekRest = CheckString();
		}
		if (trekRest.equalsIgnoreCase("rest")) { // sleep leads to fight with Wolf
			System.out.println(
					"You wake up in a cold sweat. Soft padded footsteps are sneaking towards you. You wait until they are close,\nand stand up to face the largest wolf you've ever seen.");
			Fight("Wolf", 3, 4, 6, 10,
					"You stand tall, arms outstretched above your head and sword gleaming in the moonlight. You let the sword’s weight and momentum carry it downwards onto the wolf,\nand you strike a deadly blow onto the wolf’s neck. It is defeated.",
					"You lie sprawled out along the ground, your strength waning. The wolf strolls up, looking downwards, gazing into your face.\nThen it pounces.",
					"You ran", false); // TODO add better run line
		} else {
			if (points[2] + random.nextInt(2) < 6) { // continue, check agility
				System.out.println( // too low, take damage
						"Exhaustion sets in. You feel it in your very core. You collapse to the ground, desperately crawling forwards as you vision begins to wane.\nEverything is blurry,\neverything is dark.\n\nYou pass out.");
				System.out.println("You take 50 damage");
				currentHealth = currentHealth - 50;
				System.out.println("Your health is now " + currentHealth);
				if (currentHealth < 0) {
					System.out.println("You lost too much health");
					endGame();
				}
				System.out
						.println("\nA long time later, you finally are awakened by the lessening of the intense heat");
			}
		}
		System.out.println( // both paths converge here
				"Weak and dehydrated, you finally reach the blissful sanctuary of shade in the ________(building).\nYou can feel relief from the scorching sun, and you settle down to relax, you notice the door is locked to the inside,\nbut that is something to worry about later, you think to yourself.\nAs you relax, you ponder over the past events.");
		System.out.println(
				"\nBefore long, the sky begins to redden. The sun slowly sinks beneath the horizon,\npainting the sky with reds and oranges, reds and oranges that remind you yet again of the trouble you now face.\nWhy does everything remind you of fire? Nevermind that, now you need shelter. You turn to the house.\nLocked, just like you remember. You search around the ______(building), and find a piece of rusty wire.\nYou attempt to pick the lock.");
		if (points[1] + random.nextInt(3) >= 7) {
			System.out.println("You successfully pick the lock.");
			exp = exp + 5;
			System.out.println("Your xp is now " + exp + ".");
			System.out.println(
					"You begin to drift off into a deep, restorative sleep. As you lay there, you can feel your body aching,\nyet you feel sufficient strength, unlike that of what you felt recently. The recent events of the day flood your memories.\nWhy do you ponder things with unknown reasons at night? The night begins to take hold; everything is darkened, basked in a pale light of the full moon…");

		} else {
			System.out.println("Unsuccessful with the lock, you attempt to kick down the door.");
			if (points[3] + random.nextInt(3) >= 6) {
				System.out.println("You successfully kick down the door.");
				System.out.println(
						"You begin to drift off into a deep, restorative sleep. As you lay there, you can feel your body aching,\nyet you feel sufficient strength, unlike that of what you felt recently. The recent events of the day flood your memories.\nWhy do you ponder things with unknown reasons at night? The night begins to take hold; everything is darkened, basked in a pale light of the full moon…");
			} else {
				System.out.println(
						"You have failed to pick the lock and knock down the door. You lack the strength to drag your exhausted body away from the _______(building), and collapse at the foot of the door.\nSleep overcomes you immediately. As night sets in and the temperature drops, the frigid air wakes you. Without a jacket or blankets, your fingertips fall victim to hypothermia.\nIt’s only a matter of time until your toes meet the same fate.");
				System.out.println("You take 100 damage");
				currentHealth = currentHealth - 100;
			}
		}
		System.out.println(
				"A shape shifts restlessly in the shadows. The nebulous blob of black draws closer. You squint out at the consuming darkness, \nbarely able make out the form. The shape moves on in the darkness.\n\nYou lie back down to rest, telling yourself your exhausted brain is playing tricks on your vision.\n\nSuddenly, a shape comes crashing into you. Sand and dirt fly as you roll on the ground.\nYou spring up from the earth to face the biggest, blackest horse you have ever seen. But it is not just a horse, as you can see shadows pouring off its form, like a mist of black.\nFight or run?");
		int tempHealth = currentHealth;
		Fight("Horse", 100, 50, 25, 10, "Win", "Lose", "Run", true);
		System.out.println(
				"You collapse to the earth, feeling the cold sand and dirt supporting your useless body. You can barely move.\nYou lift your head to see the spirit horse a few feet away, looking victorious. It looks over. You hope it did not see you move. It did.\nAs you lay there helplessly, it charges.");
		System.out.println(
				"You wake up on the ground of the ________(building). You must have fallen off your bed during your dream.\n\nA nightmare, really\n\nA nightmare in a nightmare.\n");
		System.out.println(
				"You chuckle softly, with nervous relief. It is time to keep moving on in your journey. As you continue walking through the plains, you summit a small hill.\nThe view before you you takes your breath away: rollings hills expanding out west, and a vast lakeside forest on the east.");
		currentHealth = tempHealth;
	}

	public void Mountains() { // mountains path
		System.out.println(
				"After toiling through the troubles of the desert, you find yourself approaching a daunting mountain range.\nBy the time you arrive at the base of the first mountain, you see a storm brewing on the horizon,\nurging you onwards.\nHere, you see a large cave opening leading straight through the mountain,\nfrom which you hear an ominous growl. At this point, you can either go through the cave for shelter\nor start the arduous process of climbing the mountain. Which path will you chose, shelter or climb?");
		String shelterClimb = CheckString();
		while (!shelterClimb.equalsIgnoreCase("shelter") && !shelterClimb.equalsIgnoreCase("climb")) { // shelter or
																										// climb
			System.out.println("Shelter or Climb?");
			shelterClimb = CheckString();
		}
		if (shelterClimb.equalsIgnoreCase("climb") && points[2] + random.nextInt(3) > 6) { // climb and succeed
			System.out.println(
					"Hesitantly, you start the trek up the mountain. About halfway up your ascent, you stumble briefly, and almost fall off the cliff.\nYou catch yourself and continue up the mountain, only to hear the booming claps of thunder on your back. The storm is upon you now,\nand the winds are swirling around you, leaving no room for doubt.\nYou realize that you must make it to the other side of the mountain to take shelter.");
			System.out.println(
					"After skillfully climbing the treacherous slope, you find an alcove on the other side and take shelter. The journey down the mountain is relatively tame,\nand you are met with rolling hills expanding out west, and a vast lakeside forest on the east.");
		} else { // shelter or climb and fail
			if (shelterClimb.equalsIgnoreCase("climb")) {
				System.out.println(
						"Hesitantly, you start the trek up the mountain. About halfway up your ascent, you stumble briefly, and almost fall off the cliff.\nYou catch yourself and continue up the mountain, only to hear the booming claps of thunder on your back. The storm is upon you now,\nand the winds are swirling around you, leaving no room for doubt.\nYou realize that you must make it to the other side of the mountain to take shelter.");
				System.out.println(
						"Despite giving it your best effort, you are unable to persevere through the storm.\nAs you are climbing up the face of the mountain, a gust of wind hits you, causing you to lose your grip. Trying to get a grasp of the rock,\nyou claw furiously at the air in front of you. You begin to fall, tumbling down the rough slope.\nAfter finally coming to a stop, you lay at the base of the mountain.");
				System.out.println("You take 150 damage.");
				currentHealth = currentHealth - 150;
				System.out.println("Your health is now " + currentHealth);
				if (currentHealth < 0) {
					System.out.println("You lost too much health");
					endGame();
				} else {
					System.out.println(
							"\nMiraculously, you survived the fall and managed to avoid any serious injuries. Bruised and cut up, you struggle to your feet,\nonly to find yourself where you started, in front of the large cave that cuts through the mountain.\nRealizing that climbing is no longer a valid option, you start towards the entrance of the cave.\n");
				}
			}
			System.out.println(
					"You enter the cave cautiously, and upon being greeted by silence, you forge onwards.\nThe moonlight filtering in illuminates your path. After finding an alcove suitable for resting, you set up and prepare to sleep.\nHowever, you hear the same menacing screech once again, and this time, its right behind you.\nAn enormous bat, ready to sink its log-sized teeth into you, is flying at you.");
			Fight("Bat", 3, 3, 8, 10,
					"You slash your sword across the beast’s wings, leaving it helpless. Hardened by your past battles,\nyou finish the job with a stab to the bat’s chest.\nSafe for the moment, you rest in your alcove for the night.",
					"Try as you might, you are unable to defeat the bat. It strikes at your arm and in your panic,\nyou use your sword to parry, which clatters out of your hands.\nThe bat flaps its wings once, and its teeth impale you.",
					"Run line here", false);
			System.out.println(
					"\nThe next day, you continue through the cave, and come out unscathed.\nThe view before you you takes your breath away: rolling hills expanding out west, and a vast lakeside forest on the east.");
		}
	}

	public void Hills() {

	}

	public void Forest() {

	}

	public void endGame() { // end game method
		System.out.println("You lost. You must suck at life");
		String playAgain;
		do {
			System.out.println("Would you like to play again: [y/n]");
			playAgain = CheckString();
		} while (!playAgain.equalsIgnoreCase("y") && !playAgain.equalsIgnoreCase("n"));
		if (playAgain.equalsIgnoreCase("y")) {
			startGame(); // restarts game if yes
		} else {
			System.out.println("Goodbye");
			System.exit(0);
		}
	}

	public void Fight(String name, int power, int health, int agility, int xp, String win, String lose, String run,
			boolean dream) { // fight method could be cleaned up a bit and by a bit I mean a lot damn it
								// messy in here
		String[] enemyHits = { "hits", "strikes", "scratches", "swipes at", "smacks", "screeches at", "shakes",
				"batters", "pounds", "snarls at" };
		String[] userHits = { "hit", "strike", "scratch", "swipe at", "smack", "punch", "shake", "batter", "pound",
				"kick" };
		String choice;
		do {
			System.out.println("Would you like to fight or run?");
			choice = CheckString();
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
						System.out.println("The " + name + " " + enemyHits[random.nextInt(10)] + " you with " + damage
								+ " damage, leaving you with " + currentHealth + " health.");
					}
					if (currentHealth > 0) {
						if (((agility - points[2]) * random.nextInt(11)) > 20) { // dodge
							if (random.nextInt(101) > 75) {
								int damageUser = random.nextInt(6) + powerUser;
								health = health - damageUser;
								System.out.println("You " + userHits[random.nextInt(10)] + " the " + name + " with "
										+ damageUser + " damage, leaving it with " + health + " health.");
							} else {
								System.out.println("The " + name + " dodged your attack.");
							}
						} else {
							int damageUser = random.nextInt(6) + powerUser;
							health = health - damageUser;
							System.out.println("You " + userHits[random.nextInt(10)] + " the " + name + " with "
									+ damageUser + " damage, leaving it with " + health + " health.");
						}
					}
				}
				if (currentHealth <= 0) {
					System.out.println(lose);
					if (!dream) {
						endGame();
					}
				} else if (health <= 0) {
					System.out.println(win);
					exp = exp + xp;
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
						System.out.println("You " + userHits[random.nextInt(10)] + " the " + name + " with "
								+ damageUser + " damage, leaving it with " + health + " health.");
					}
					if (health > 0) {
						if (((points[2] - agility) * random.nextInt(11)) > 20) { // dodge
							if (random.nextInt(101) > 75) {
								int damage = random.nextInt(6) + power;
								currentHealth = currentHealth - damage;
								System.out
										.println("The " + name + " " + enemyHits[random.nextInt(10)] + " you, dealing "
												+ damage + " damage, leaving you with " + currentHealth + " health.");
							} else {
								System.out.println("You dodged the " + name + "'s attack.");
							}
						} else {
							int damage = random.nextInt(6) + power;
							currentHealth = currentHealth - damage;
							System.out.println("The " + name + " " + enemyHits[random.nextInt(10)] + " you, dealing "
									+ damage + " damage, leaving you with " + currentHealth + " health.");
						}
					}
				}
				if (currentHealth <= 0) {
					System.out.println(lose);
					if (!dream) {
						endGame();
					}
				} else if (health <= 0) {
					System.out.println("\n" + win);
					exp = exp + xp;
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
						System.out.println("The " + name + " " + enemyHits[random.nextInt(10)] + " you with " + damage
								+ " damage, leaving you with " + currentHealth + " health.");
					}
					if (currentHealth > 0) {
						if (((agility - points[2]) * random.nextInt(11)) > 20) {
							if (random.nextInt(101) > 75) {
								int damageUser = random.nextInt(6) + powerUser;
								health = health - damageUser;
								System.out.println("You " + userHits[random.nextInt(10)] + " the " + name + " with "
										+ damageUser + " damage, leaving it with " + health + " health.");
							} else {
								System.out.println("The " + name + " dodged your attack.");
							}
						} else {
							int damageUser = random.nextInt(6) + powerUser;
							health = health - damageUser;
							System.out.println("You " + userHits[random.nextInt(10)] + " the " + name + " with "
									+ damageUser + " damage, leaving it with " + health + " health.");
						}
					}
				}
				if (currentHealth <= 0) {
					System.out.println(lose);
					if (!dream) {
						endGame();
					}
				} else if (health <= 0) {
					System.out.println(win);
					exp = exp + xp;
					System.out.println("Your xp is now " + exp + ".");
				} else {
					System.out.println("shit");
				}
			}
		} else {
			System.out.println("shit");
		}
	}

	public String CheckString() { // prompts user for String TODO add more options
		String temp = inputString.nextLine();
		if (temp.equalsIgnoreCase("xp")) {
			System.out.println("Your xp is now at " + exp);
			return "";
		} else if (temp.equalsIgnoreCase("health")) {
			System.out.println("Your health is now at " + currentHealth);
			return "";
		} else if (temp.equalsIgnoreCase("stats")) {
			System.out.println("Your current stats are :");
			System.out.println("Intelligence: " + points[1]);
			System.out.println("Agility: " + points[2]);
			System.out.println("Power: " + points[3]);
			System.out.println("Health: " + points[4]);
			System.out.println("Magic: " + points[5]);
			return "";
		} else if (temp.equalsIgnoreCase("quit")) {
			endGame();
			return "";
		} else {
			return temp;
		}
	}

	public int CheckInt() { // prompts user for integer
		String temp = inputString.nextLine();
		while (!isInteger(temp)) {
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
			while (true) { // loop for break
				System.out.println("How many points would you like to assign to intelligence?");
				intelligence = CheckInt();
				while (intelligence > 10 || intelligence < 1) {
					System.out.println("That's not between 1 and 10. Please input a different value.");
					System.out.println("How many points would you like to assign to intelligence?");
					intelligence = CheckInt();
				}
				points = points + intelligence;
				if (points > 25) {
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
				if (points > 25) {
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
				if (points > 25) {
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
				if (points > 25) {
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
			if (points != 25) {
				System.out.println("Your points did not add up to 25. Please try again.");
			}
		} while (points != 25);
		int[] arrayOfPoints = { points, intelligence, agility, power, health, magic };
		return arrayOfPoints;
	}
}