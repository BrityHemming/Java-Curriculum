
# Gomoku Requirements
## Requirements
* Can set up two players.
* For a human player, collect their name. (A random player's name is randomly generated.)
* For each stone placement, use the player's name to ask questions.
* Since the random player doesn't require input, the UI should display stone placement and the results of placement. (Random player placement may fail since they don't know what they're doing.)
* Re-prompt on failed placement. The game must not proceed until a player has made a valid move.
* Display the final result of the game.
* Give the option to play again.
## Technical Requirements
* All rules are modeled inside the Gomoku class. You may not modify Gomoku, Player, HumanPlayer, RandomPlayer, Stone, or Result.
* Add your code to the project provided. Be sure to use sensible class names, method names, and packages.
* At least one class beyond the App class is required.
* Stones use 0-based rows and columns.
## Approach
Plan before you write code.

Take considerable time to understand the project code. Use existing tests to see how objects are constructed and used.

It may be helpful to break the game into two phases: set up and gameplay.

Write one method that accepts the game state and draws the board. Never draw the board in more than one place.

## Stretch Goals
Create a third Player implementation and add it to your list of player options. Can you build a player that makes good choices?
Black has an advantage as the starting player. Competitive Gomoku uses various opening rules to improve fairness. Implement one of those rules. For example, the Swap2 should be achievable using only the Gomoku's place and swap methods. In the starting screen, give the option to choose an opening rule.
