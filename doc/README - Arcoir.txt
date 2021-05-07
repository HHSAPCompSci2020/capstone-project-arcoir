AP Computer Science Final Project - README Template


Instructions:
The first step in creating an excellent APCS final project is to write up a README. At this stage, this README file acts as your project proposal. Once you’ve filled in all components, Shelby will read through it and suggest edits. Ultimately, you need a document that adequately describes your project idea and we must agree on this plan.


Have one member of your group make a copy of this Google Doc. Then, they should share it with all other members and with Mr. Shelby so that every group member has edit permissions, and Shelby can add comments on your ideas.


There’s a lot of parts of this document that you might not have full answers for yet. Because you haven’t written the program yet, it’s difficult to think about the instructions or which group members will do which parts. Even though this is hard to think about, you must have something in these sections that acts as your current plan. However, during the course of the project, you’ll continuously update this document. This means that you will not be held to exactly what you put here - components of this document can change (and it’s pretty common!).


There is one exception: the Features List section. Once Shelby OKs your README, the Features List section cannot be modified. For this reason, it is most important that you get a solid idea of what you want to make and the primary features it will have now.


Talk with your group. Consider drawing some pictures of what you think your project might look like. Be precise. When you’re ready, fill this out together. Each component in brackets below ( [these things] ) should be replaced with your ideas. Note that there are several sample READMEs posted on this assignment for you to use as guidance.


-------------------When README is finalized, remove everything above this line--------------------


Arcoir
Authors: Nicole Spaulding, Emily Zhang, Lindsay Qin
Revision: 5/7/21


Introduction: 
[In a few paragraphs totaling about ½ page, introduce the high-level concept of your program. What this looks like depends a lot on what type of thing you are making. An introduction for an application will look different than one for a game. In general, your introduction should address questions like these:




What does your program do?
What problem does it solve? Why did you write it?
What is the story?
What are the rules? What is the goal?
Who would want to use your program?
What are the primary features of your program?]


-----


Long ago, in the kingdom of Arcoir, a decree was issued: Only the family of Arc-en, the rulers of the land, were allowed to use colors. They scattered the colors in strongholds across the country, locked away from the people or Arcoir. Since then, the kingdom has lived in a gray and colorless world. You, a rising star in the world of artists, are determined to get them back. Do YOU have what it takes to bring color back to Arcoir?


In this unique level-based game, players can create and design their own characters (if they want, standard characters will be provided), collect artifacts, and restore colors!


Instructions:
[Explain how to use the program. This needs to be specific: 
Which keyboard keys will do what? 
Where will you need to click? 
Will you have menus that need to be navigated? What will they look like? 
Do actions need to be taken in a certain order?]


Starting Screen
Right click START to begin the game.


Main Menu
Right click CUSTOMIZE to customize characters and enemies.
Right click PLAY to begin playing the game.


Help
Right click the question mark at the bottom of the screen to get help.


Drawing Screen
Right click the colors to select a color. Click the grid to customize your character. [a]
Right click CHARACTER to customize the character you will be playing as. 
Right click ENEMY to customize enemies. These can only be customized at the beginning of the game.
Right click DOWNLOAD to download your customized character as a jpg or png.
Right click SAVE to save your customized character.


Gameplay -
Description: A platformer similar to Mario in which the player remains in the center of the screen and the camera pans to keep it that way.


Controls:
Press the spacebar to jump.
Press the right arrow to move right.
Press the left arrow to move left.
Go through coins to collect coins. 
To avoid monsters, simply jump over them. To kill monsters, right-click anywhere on the screen once the monster comes into range.[b] 
Right click the treasure chest to collect the artifact and gain loot. 


Shop
Right click POWERUPS to browse available powerups and lives. 
Right click CUSTOMIZE to customize your character.
Right click WEAPONS to browse available weapons.
Right click BUY to buy the selected product. 


Available lives, powerups, and the help button are always available to be accessed/viewed at the bottom of the screen.


This is a side-view platformer game. The game will scroll as the player moves. 
Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
*  Drawing interface
   * The drawing interface allows players to create their own custom characters before playing the game. The characters are drawn using pixel art and have two different motions: one where the left foot is forward, and one where the right foot is forward. If the player does not wish to customize their character, they do not have to and can use a default character instead.
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
[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.]
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
[These are features that we agree a fully complete version of this program would have, but that you probably will not have time to implement. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]
*  Chests that contain the artifact as well as loot (powerups/money) at end of levels
   * At the ends of each level, chests can be opened giving a color artifact and a possibility of power-ups
*  Saved highscores and drawings even after closing the eclipse window 
   * Players can save highscores and their customized characters/enemies even after closing the game. They can see the highscores when they open the program and use the same characters when playing again. Players can also save their customized characters/enemies as pngs or jpegs. 
* Endless level that can be unlocked at the end
   * At the end of the game, a player can play an endless level in full color to challenge their limits and get a highscore


Class List:
[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]


UML Link


* Main
   * Fields
      * Menu
   * Methods
      * main 
* Menu
   * Fields
      * GameRunner
      * DrawingSurface
      * dashboard
   * Methods
      * runGame


* DrawingSurface
   * Fields
      * ColorPalette
      * Character
      * dashboard
   * Methods
      * clickToFill
      * saveImage


* ColorPalette
   * Fields
      * Array of currently created colors
   * Methods
      * selectColor
      * changeColor


* GameRunner
   * Fields
      * MainCharacter
      * Enemies
      * Ground
      * Dashboard
   * Methods
      * runGame


* Dashboard
   * Fields
      * Buttons
      * Help
      * Map
      * Pause
   * Methods
      * select


* Map
   * Fields
      * progress
   * Methods
      * increaseProgress


* Enemies
   * Fields
      * Animation
      * Int damage
   * Methods
      * attack


* MainCharacter
   * Fields
      * Animation: DefaultCharacter
      * Lives
   * Methods
      * attack
      * jump
      * moveRight
      * moveLeft
      * duck
* Ground
   * Fields
      * Image
      * collider/hitbox
   * Methods
      * Boolean hasCollided(Rectangle r)


* Animation
   * Fields
      * ArrayList<Image> frames
      *    * Methods
      * Act()
      * addFrame(Image image)
      * getFrame()
* Character
   * Fields
      * health
   * Methods
      * attack
      * takeDamage
      * move


Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
* List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
* Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]


Lindsay: Gameplay Elements, Enemies, Scenes 
Nicole: Gameplay Elements, Character Control, Scenes
Emily: Gameplay Elements, GUI, Music


Fun things to do if we feel like it:
Nicole voice every time you open the program: “Arcoir: Legendary Adventure” nicole says noooooooooooo :(
Intro animation that gives you the story