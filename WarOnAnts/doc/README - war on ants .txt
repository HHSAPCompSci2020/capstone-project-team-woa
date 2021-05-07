War on Ants
Authors: William Hyun and Dhruv Masurekar
Revision: 4-25-2021


Introduction: 


Our program is a tower defense game in which the user will be given a starting and an ending point and will have to build a maze. The player will have a limited amount of materials to build with, and once they complete, the maze ants will begin entering from the starting point 
The player will now have to place towers to prevent the ants from reaching the endpoint. If a certain number of ants reach the end, the game ends. This program is created to be a game that one plays in one’s free time. The story for the game revolves around an army of ants 
and other insects trying to reach the player’s fruit and the player who is placing blockades and plants to stop the ants from eating his fruits. As for the rules, the player will be given a set number of materials (walls) to create a maze. When done placing the walls, 
the player will have to place plants on top of the walls using coins, which he will get by killing ants. Once the plants are placed on all the blockades, the player will no longer be able to place plants. The player will be able to remove a plant from on top of a blockade 
and get back some coins as a refund. The player can also upgrade the plants as many times as he wants, but the cost will increase. The objective of the game is to survive for as long as possible. The primary features of the program are: creating a maze for the ants, 
placing plants on top of the maze, and leveling up the plants as necessary to keep the ants from getting to the fruit.   
 
Instructions:


When the program runs, the player will see a grid and how many blockade materials one has. 
The player will prepare their grid by placing walls and plants. 
A regular plant will cost 10 coins, and there will be a set number of walls.
By selecting the type of plant by clicking on a button in a sidebar, the player can then clicking on a grid location that is a wall to place the plant.
Plants are only able to be placed on walls. Once the player presses the begin button, a wave of ants will begin to appear. 
When an ant dies, it gives a certain number of coins, which the player can use to place more plants.


Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
* Ants will be able to efficiently path find through the maze to reach the fruit. 
* Maze walls can be placed and removed any time to create paths for the ants to travel through.
* Plants can be placed on top of maze walls in order to defend the fruit from the ants.
* Plants will be able to damage ants if they fall in a certain range of the plant 
* Plants can be leveled up or uprooted whenever necessary. Leveling up uses coins while uprooting gives coins depending on what level the plant is.
* A fruit must be created that is at the end of the maze and it must have a health bar.
* There will be multiple stages with predetermined difficulty levels, and one infinite level where the difficulty increases as one plays.
Want-to-have Features:
*  A variety of ant types that have different abilities could be added. Such abilities could be extra speed, higher defense, flight, or destroying walls/plants
*  Different types of plants that have different abilities could also be added. Some abilities could have varying ranges, instant killing ants, area of effect attacks, and healing the health of the fruit.
*  Extra aids like a shield for the fruit, revive the fruit, or a bug spray or bug zap weapon that the player can use to fend off ants if they get too close.
* Option to choose levels, which will get increasingly more difficult, or infinite where the game lasts forever, also getting progressively more difficult. The major difference being saving progress, which can only be done with levels.
* Choose difficulty level, ex. Easy, Medium, Hard, Impossible. Each increase in difficulty will not only increase the speed of the ants but also their health. 


Stretch Features:
*  Animations for all ants, plants, and weapons so that they aren’t simple and sprite on a grid. 
*  Saving progress even when the game exists, the program is closed. This would only apply to levels cleared as infinite mode always starts from the beginning.
*  A tutorial that shows how the game should be played and the rules of playing the game. 




Class List:


* Insect class: A superclass that can be used to make more classes, one such class is the Ant.
* Ant class: A type of insect; inherits the Insect class.
* Plant class: A superclass that can be used to make more classes, one such class is the EnergyPlant
* EnergyPlant class: A type of plant; inherits Plant.
* Fruit class: The “health bar” of the player. Once the fruit reaches 0 durability (eaten away) the game ends.
* Wall class: A blockade that the ants cannot pass through and must go around to get to the fruit.
* Grid class: The playing field for the game.
* Main class: Used to run the game. 
* DrawingSurface: Handles user interactions and graphic elements.


Credits:


* William: Ant, Insect, Grid, and DrawingSurface elements. Added javadoc comments and created the uml.png and the javadoc .html
* Dhruv: Plant, EnergyPlant, Fruit, and Wall classes. Created all the classes and added into the project