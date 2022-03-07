/**
 * Project 3.6.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 * After wathcing the memory strings appear in the buttons one at a time, the
 * player recreates the sequence from memory.
 */
public class MemoryGame
{
  public static void main(String[] args) {

    // Creates the "memory strings" - an array of single character strings to 
    // show in the buttons, one element at a time. This is the sequence
    // the player will have to remember.
    String[] memStringsFull = {"a", "b", "c", "d", "e", "f", "g", "1", "2", "3"};
    int memSeqLength = 3;

    // Creates the game and gameboard
    // Randomized board starting with 3 buttons
    MemoryGameGUI game = new MemoryGameGUI();
    int buttons = 3;
    int rounds = 1;
    Boolean play = true;
    int score = 0;
    double delay = 1.5;
    int incorrect = 0;
    Boolean playRandom = true;

    String name = game.Intro();
    Object level = game.chooseLevel();
    if (level == "Easy") {
      delay += 0.4;
      playRandom = false;
    } else if (level == "Hard") {
      delay -= 0.4;
    }

    // Plays the game until user wants to quit or user wins/loses
    while (play == true) {
      // Create new board
      game.createBoard(buttons, playRandom);
      game.showDifficulty(rounds);

      // Creates a new array of strings to memorize
      String[] memStrings = new String[memSeqLength];
      // Adds elements of memStringsFull
      for (int i = 0; i < memSeqLength; i++) {
        memStrings[i] = memStringsFull[i];
      }

      // Creates a new array that will contain the randomly ordered memory strings
      String[] ranMemStrings = new String[memSeqLength];

      // Overloads the next method in RandomPermutation to create a random sequence 
      // of the memory strings, passed as a parameter
      ranMemStrings = RandomPermutation.next(memStrings);
      // Creates a new string that will contain the elements of the array ranMemStrings
      String ranMemString = "";
      // Adds the elements of the array ranMemStrings
      for (int i = 0; i < memSeqLength; i++) {
        ranMemString += ranMemStrings[i];
      }

      // Plays one sequence, delaying half a second for the strings to show
      // in the buttons
      // Saves the player's guess
      String guess = game.playSequence(ranMemStrings, delay);
      Boolean match = false;

      // Determine if player's guess matches all elements of the random sequence.
      // Cleanup the guess - remove commas and spaces. Refer to a new String method 
      // replace to make this easy.
      String cleanGuess = guess.replace(",", "").replace(" ", "");
        
        // iterate to determine if guess matches sequence
      if (ranMemString.equals(cleanGuess)) {
        match = true;
      } else {
        match = false;
      }

      // If match, increase score, and signal a match, otherwise, try again.
      if (match) {
        score++;
        game.matched();
      } else {
        game.tryAgain();
        incorrect++;
        if (incorrect == 3) {
          game.lose(name);
          game.showScore(score, rounds);
          game.quit();
        }
      }

      // Asks if user wants to play another round of the game 
      play = game.playAgain();

      // If user says yes, increase difficulty
      if (play) {
        // Tracks the number of games played
        rounds++;
        // Increase difficulty
        buttons++;
        memSeqLength++;
        delay -= 0.1;
        if (score == 10) {
          game.win(name);
          game.showScore(score, rounds);
          game.quit();
        }
      }
    }
    // When done playing, show score and end the game.
    game.showScore(score, rounds);
    game.quit();
  }
}