AP Computer Science Final Project - README Template


Arcoir
Authors: Nicole Spaulding, Emily Zhang, Lindsay Qin
Revision: 5/23/21


Introduction: 


Long ago, in the Kingdom of Arcoir, a decree was issued: Only the family of Arc-en, the rulers of the land, were allowed to use colors. The royal family stole the colors and scattered them across the country, locked away from the people of Arcoir. Since then, the kingdom has lived in a gray and colorless world. You, a rising star in the world of artists, are determined to get them back. Do YOU have what it takes to bring color back to Arcoir?


In this unique level-based game, players can create and design their own characters (if they want, standard characters will be provided), collect artifacts, and restore colors! Users have the ability to custom their own character with a graphics editor app. This digital painting graphic provides twenty four different colors with more that can be used by entering the rgb numbers into a color palette generator. Uses can also use the pen tool to draw their design and easily reset both the color palette and their drawing to their defaults. Players have the ability to animate their design by drawing their character in four different modes: idle and in three other movements. Not only can they customize their own character (the hero), they can also design the enemies. 


After customizing their characters, users can save their design and use them in an interactive game. The character attempts to kill off dragon enemies as it begins its travel along the Kingdom of Arcoir. However, if the dragons come into contact with the player for too long, the character will lose a life. Once the user loses all three lives, the game will be over. When the character kills off the same number of enemies as the level it is on, the game will level up. At the end of each level, the character can collect a color artifact in order to return all the colors back into the Kingdom of Arcoir. However, during this journey, the player may encounter mushrooms along the way, which it would need to jump over.  


Gamers and artists who would like a combination of both types of programs would want to use this program. They have no limit to test their creativity with their drawing skills, and they also have the ability to showcase their artwork as their own character in a game.  


Instructions:
Left click START to begin.


Left click through the storyline to get to the Main menu!


Main Menu:
Left click CUSTOMIZE to customize characters and enemies.
Left click PLAY to begin playing the game.


Help:
Left click the question mark at the bottom right of the screen to get help.


Back:
Left click the arrow at the bottom left of the screen to go back to the previous screen.


Drawing Screen:
Left click the colors to select a color. Click the grid to customize your character. 
Left click the paint bucket icon to fill in a larger area with a single click.
Left click the save icon to save your customized character.
Left click the palette with the plus icon to enter a rgb value to choose a specific color. 
Left click the refresh arrow icon to clear the drawing page. 
Left click the palette with the refresh icon to reset the color back to default. 
Left click the pencil to change the stroke width.
Left click HERO to switch to customizing enemies. Left click ENEMY to switch to customizing the character you will be playing as. 
Left click DOWNLOAD IMAGE to download your customized character as a jpg or png. This can only be done if the current frame is saved first.


Gameplay: 
Description: A game similar to Mario in which the player remains in the center of the screen and the camera pans to keep it that way. Unlike Mario though, leveling up happens by defeating enemies, but if enemies get too close, the character will lose a life! Everytime a level is reached, a new color will be collected. 


Controls:
Press the spacebar to attack.
Press the up arrow to jump.
Press the right arrow to move right.
Press the left arrow to move left.
To avoid monsters, simply jump over them. To kill monsters, press space once the monster comes into range. 


Left click RETURN TO MENU to return to the menu.


This is a side-view game. The game scrolls as the player moves. 


Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):


Must-have Features:
*  Drawing interface
   * The drawing interface allows players to create their own custom characters before playing the game. The characters are drawn using pixel art and have three different motions. If the player does not wish to customize their character, they do not have to and can use a default character instead.
*  Game becomes more colorful as player progresses
   * The game levels start off in black and white. As the player collects color artifacts, that color is gradually returned to the in-game world.
* defeatable enemies and player has lives
   * Throughout levels, enemies pop up and the player has to either avoid them (if they don’t have weapons) or defeat them. Enemies die in one hit, and the player loses a life every time they get hit. Enemies drop an amount of coins when they die.
* 7 total levels
   * The player has to complete seven total levels in order to beat the game. Each level progressively gets more difficult. The levels consist of running through a map while avoiding obstacles and defeating enemies to get to the artifact at the end.
*  Collectable artifacts to unlock colors
   * At the end of each level, the player collects a different artifact. As each artifact is collected, the color that artifact corresponds to returns to the in-game world.
*  Help window
   * A help icon is always present in the game window. At any point, the player can click on the icon and get help on how to play the game and game features. 


Want-to-have Features:


* Shops at end of levels (towns, outposts, etc)
   * After completing each level, the player can enter towns/outposts to buy power ups and lives that assist the player with completing levels. The player can also buy weapons to better attack enemies with. 
* Collect coins on levels
   * The player collects coins in levels. The coins can be used in the shops to buy power ups and lives to assist the player on levels and weapons to defeat enemies.
* Customizable enemies (same way as character)
   * Enemies can be customized at the beginning of levels in the same way as a character. This means that they are created using pixel art and have two-frame movements.
*  Attack system with weapons (at least 2)
   * Players use weapons to defeat enemies. Each different weapon deals the same damage, but the amount of money the enemy drops depends on the kind of weapon used.
* Unique levels (underwater, in the air, etc)
   * Each level has a different format from the others (rather than simple running and jumping). For example, one level can be fully underwater, and the player has to use different controls from the normal to move. Another can be in the air, and the entire level consists of jumping from platform to platform.


Stretch Features:


*  Chests that contain the artifact as well as loot (powerups/money) at end of levels
   * At the ends of each level, chests can be opened giving a color artifact and a possibility of power-ups
*  Saved highscores and drawings even after closing the eclipse window 
   * Players can save highscores and their customized characters/enemies even after closing the game. They can see the highscores when they open the program and use the same characters when playing again. Players can also save their customized characters/enemies as pngs or jpegs. 
* Endless level that can be unlocked at the end
   * At the end of the game, a player can play an endless level in full color to challenge their limits and get a highscore


Class List:
* Animation 
   * The Animation class contains an array of images. It changes and updates the frame for the given amount of milliseconds passed in the parameter. 
* ColorPalette
   * The ColorPalette class creates twenty-four default colors. It displays each color in a rectangle, so that users can pick and choose the one that they want to use when designing. 
* Dashboard
   * The Dashboard class displays the help button and back button on the screen.
* DrawingScreen
   * The DrawingScreen class encompasses all of the functions the user needs to draw and customize Entities. 
* DrawingSurface
   * The DrawingSurface class is a PApplet that displays the program and the four different screens (DrawingScreen, MenuScreen, GameScreen, and GameOverScreen) used within the game. 
* Entity
   * The Entity class contains the descriptions and functions that make up each character in the game, which includes both the hero and the enemies. It measures the velocity and position of these characters. 
* GameOverScreen
   * The GameOverScreen class presents the display that appears when the character loses all three of its lives. It prompts the user to exit back to the main menu in order to customize or play again.   
* GameScreen
   * The GameScreen class displays the user-active interface that allows the player to control the character. It keeps track of the number of levels, character lives, and number of defeated enemies. This class is also in charge of spawning more enemies once they have all been killed. 
* Ground
   * The Ground class enables the characters to remain along the same x-axis with a rectangle shape.
* Help 
   * The Help class creates a help window that instructs the user on how to use the program.
* Main
   * The Main class runs and executes the program.
* MenuScreen        
   * The MenuScreen class displays the initial screen when the user first runs the program. It prompts the player to start the game and either begin the customization of their character or the game. 
* Screen
   * The Screen class initializes the window size to 800 by 500, and any changes to the window after will follow the same scale. 
* ScreenSwitcher
   * The ScreenSwitcher interface initialized each of the four screens to a number between 0 and 3, so that other classes can easily call the switchScreen() method in order to change the display. 
* Scrollable
   * The Scrollable class shifts the background image either left or right, depending on which direction the character moves in the game. 


Credits:


Lindsay: ColorPalette, Map, Dashboard, DrawingScreen, Help, MenuScreen, Entity, GameScreen, icons in DrawingScreen, starting screen drawings, starting screen story images
Nicole: Animation, Character, GameScreen, DrawingScreen, default hero/enemy animations, backgrounds, artifacts, mushroom drawing, artifact drawing, default character drawing
Emily: Ground, Levels, GameScreen, GameOverScreen, Entity


External Library: Processing
Code from:
        GameOfLife lab (Main, Grid methods, filereader)
        Processing ScreenSwitcher demo, popup demo
        Processing Animation demo