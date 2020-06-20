package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game.
 */
public class LinkedListGame {

  private LLIntegerNode priorGuesses;
  private LLIntegerNode candidateList;
  private LLIntegerNode candListData;
  private LLIntegerNode tailC;
  private LLIntegerNode tailP;
  private boolean isOver;
  private int guess;

  /********************************************************
   * NOTE: for this project you must use linked lists implemented by yourself. You
   * are NOT ALLOWED to use Java arrays of any type, or any class in the java.util
   * package (such as ArrayList).
   *******************************************************/

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary, but DO NOT remove any
   * provided method, and do NOT add new files (as they will be ignored by the
   * autograder).
   *******************************************************/

  // LinkedListGame constructor method
  public LinkedListGame() {
    reset();
  }

  /**
   * Resets data members and game state so we can play again.
   * 
   */
  public void reset() {
    isOver = false;
    guess = 1000;
    priorGuesses = null;
    candidateList = new LLIntegerNode(1000);   
    tailP = priorGuesses;
    tailC = candidateList;    
    for (int i = 1; i < 9000; i++) {
      candListData = new LLIntegerNode(i + 1000);
      tailC.setLink(candListData);
      tailC = candListData;
    }
  }

  /**
   * Returns true if n is a prior guess; false otherwise.
   * 
   */
  public boolean isPriorGuess(int n) {
    boolean isPriorGuess = false;
    LLIntegerNode tmp = priorGuesses;
    while (tmp != null) {
      if (tmp.getInfo() == n) {
        isPriorGuess = true;
      }
      tmp = tmp.getLink();
    }
    return isPriorGuess;
  }

  /**
   * Returns the number of guesses so far.
   * 
   */
  public int numGuesses() {
    int numGuesses = 0;
    LLIntegerNode tmp = priorGuesses;
    while (tmp != null) {
      numGuesses++;
      tmp = tmp.getLink();
    }
    return numGuesses;
  }

  /**
   * Returns the number of matches between integers a and b. You can assume that
   * both are 4-digits long (i.e. between 1000 and 9999). The return value must be
   * between 0 and 4.
   * 
   * <p>A match is the same digit at the same location. For example: 1234 and 4321
   * have 0 match; 1234 and 1114 have 2 matches (1 and 4); 1000 and 9000 have 3
   * matches (three 0's).
   */
  public static int numMatches(int a, int b) {
    int digitA;
    int digitB;
    int numMatches = 0;
    while (a > 0) {
      digitA = a % 10;
      digitB = b % 10;
      if (digitA == digitB) {
        numMatches++;
      }
      a = a / 10;
      b = b / 10;
    }
    return numMatches;
  }

  /**
   * Returns true if the game is over; false otherwise. The game is over if the
   * number has been correctly guessed or if no candidate is left.
   */
  public boolean isOver() {
    return isOver;
  }

  /**
   * Returns the guess number and adds it to the list of prior guesses. The
   * insertion should occur at the end of the prior guesses list, so that the
   * order of the nodes follow the order of prior guesses.
   */
  public int getGuess() {
    LLIntegerNode currGuess = new LLIntegerNode(guess);
    if (priorGuesses == null) {
      priorGuesses = currGuess;
      tailP = currGuess;
    } else {
      tailP.setLink(currGuess);
      tailP = currGuess;
    }
    return guess;
  }

  /**
   * Updates guess based on the number of matches of the previous guess. If
   * nmatches is 4, the previous guess is correct and the game is over. Check
   * project description for implementation details.
   * 
   * <p>Returns true if the update has no error; false if no candidate is left
   * (indicating a state of error);
   */
  public boolean updateGuess(int nmatches) {
    boolean success = false;
    if (nmatches == 4) {
      isOver = true;
      success = true;
    } else {
      LLIntegerNode tmp = candidateList;
      LLIntegerNode tempH = null;
      LLIntegerNode tempT = null;
      while (tmp != null) {
        if (numMatches(tmp.getInfo(), guess) == nmatches) {
          LLIntegerNode newNode = new LLIntegerNode(tmp.getInfo());
          if (tempH == null) {
            tempH = newNode;
            tempT = newNode;
          } else {
            tempT.setLink(newNode);
            tempT = tempT.getLink();
          }
        }
        tmp = tmp.getLink();
      }
      if (tempH == null && tempT == null) {
        return false;
      }
      candidateList = tempH;
      tailC = tempT;
      guess = candidateList.getInfo();
      success = true;
    }
    return success;
  }

  /**
   * Returns the head of the prior guesses list. Returns null if there hasn't been
   * any prior guess
   */
  public LLIntegerNode priorGuesses() {
    return priorGuesses;
  }

  /**
   * Returns the list of prior guesses as a String. For example, if the prior
   * guesses are 1000, 2111, 3222, in that order, the returned string should be
   * "1000, 2111, 3222", in the same order, with every two numbers separated by a
   * comma and space, except the last number (which should not be followed by
   * either comma or space).
   *
   * <p>Returns an empty string if here hasn't been any prior guess
   */
  public String priorGuessesString() {
    String result = "";
    LLIntegerNode tmp = priorGuesses;
    if (numGuesses() != 0) {
      result += tmp.getInfo();
      tmp = tmp.getLink();
      while (tmp != null) {
        result += ", " + tmp.getInfo();
        tmp = tmp.getLink();
      }
    }
    return result;
  }
}