War on Ants
Authors: William Hyun and Dhruv Masurekar
Revision: 5-23-2021


Introduction: 

Our program is a tower defense game in which the user will be given a starting and an ending point and will have to build a maze. 
The player will have a limited amount of materials to build with, and once they complete, the maze ants will begin entering from the starting point (hole). 
The player will now have to place towers to prevent the ants from reaching the fruit. 
If a certain number of ants reach the end, the game ends. This program is created to be a game that one plays in one’s free time. 
The story for the game revolves around an army of ants and other insects trying to reach the player’s fruit and 
the player who is placing blockades and plants to stop the ants from eating their fruit. 
As for the rules, the player will be given a set number of materials (walls) to create a maze. Plants can only be placed on walls and doing so costs coins, 
which will be obtained by killing ants. The player can place and remove walls or plants anytime. 
The player will be able to remove a plant from on top of a wall and get back some coins as a refund. The objective of the game is to survive for as long as possible. 
The primary features of the program are: creating a maze for the ants, placing plants on top of the maze, and protecting the fruit from the ants.   


Instructions:

When the program runs, the player will be asked to choose a map. 
Then the player will see a grid and how many wall materials he has. 
The player will prepare their grid by placing walls and plants. 
A plant will cost 10 coins and a wall will cost 1 material. 
By selecting the location, the player will be able to place a plant or a wall. 
Plants are only able to be placed on walls and to place plants, the player must right-click on the wall. 
The player can only place walls on empty spots and must left-click on that spot. 
The player cannot remove the walls around the edge of the grid but can remove other walls. 
The player can be refunded materials by removing walls if the material count is less than 5. 
The player can be refunded coins by uprooting plants.
Once the player chooses the map, the map will be loaded, and the ants will spawn out of the hole. 
When an ant dies, it gives 1 coin, which the player can use to place more plants. 
If the player loses, they will be given the choice to play again and choose between the map selections. 



Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):

Must-have Features:
* Ants will be able to efficiently path find through the maze to reach the fruit. 
* Maze walls can be placed and removed at any time to create paths for the ants to travel through.
* Plants can be placed on top of maze walls in order to defend the fruit from the ants.
* Plants will be able to damage ants if they fall in a certain range of the plant.
* Plants can be leveled up or uprooted whenever necessary. Leveling up uses coins while uprooting gives coins depending on what level the plant is.
* A fruit must be created that is at the end of the maze and it must have a health bar.
* There will be multiple stages with predetermined difficulty levels, and one infinite level where the difficulty increases as one plays.

Want-to-have Features:
* A variety of ant types that have different abilities could be added. Such abilities could be extra speed, higher defense, flight, or destroying walls/plants
* Different types of plants that have different abilities could also be added. 
* Some abilities could have varying ranges, instant killing ants, area of effect attacks, and healing the health of the fruit.
* Extra aids like a shield for the fruit, revive the fruit, or a bug spray or bug zap weapon that the player can use to fend off ants if they get too close.
* Option to choose levels, or infinite where the game lasts forever, also getting progressively more difficult. The major difference being saving progress, which can only be done with levels.
* Choose difficulty level, ex. Easy, Medium, Hard, Impossible. Each increase in difficulty will not only increase the speed of the ants but also their health. 


Stretch Features:
* Animations for all ants, plants, and weapons so that they aren’t simple and sprite on a grid. 
* Saving progress even when the game exists, the program is closed. This would only apply to levels cleared as infinite mode always starts from the beginning.
* A tutorial that shows how the game should be played and the rules of playing the game. 




Class List:


* Insect class: An insect that can roam around the maze and is attracted to the Fruit. The insect is able to find the shortest path from its starting point to the location of the fruit
* Plant class: A plant that can shoot lasers at insects to protect the fruit from them
* Fruit class: The “health bar” of the player. Once the fruit reaches 0 durability (eaten away) the game ends.
* Wall class: A blockade that the ants cannot pass through and must go around to get to the fruit.
* Grid class: The playing field for the game.
* Main class: Used to run the game. 
* DrawingSurface: Handles user interactions and graphic elements.


Credits:


William:
Insect, Grid, and DrawingSurface classes. 
Made pathfinding methods for the Insect class, act method for Insect and Grid, and a tester that tests the pathfinding method. 
Pathfinding works for all insects. Added images for ant hole and fruit. Improved mechanics for shooting ants.  
Improved coin and material system. Made buttons with the help of Dhruv. Made multiple stages. 
Made the stages get increasingly difficult as time passes by increasing the health of the Insects that are being spawned. 
Added javadoc comments, UML, and executable jar file. 

Dhruv: Plant, Fruit, and Wall classes. Created all the classes and added them to the project. 
Added more methods to my assigned classes and a method to the Grid class. 
Added javadoc comments for my new methods. Added the ability to add plants to the grid. 
Added coin and material system for placing plants and walls. Enlarged the grid to make it easier for the eye. 
Added images for ant, plant, and wall and added buttons to play and restart the game. 
Added instructions for the user to understand the game. Updated README and UML and added them to project.

G4P: Allowed us to add buttons and text to the screen