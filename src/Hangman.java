/**
 * This class is used to create a hangman class for a user to play
 *
 * @author Amanda C., Tony C., Raisa S.
 *
 */


import java.util.Scanner;

public class Hangman {
    /** The name of the user */
    private String name;
    /** The topic of the guess */
    private int topics;
    /** How guess looks everytime the user guesses */
    private String guess;
    /** The randomWord that user will get */
    private String randomWord;

    /**allow user interaction*/
    Scanner scan = new Scanner(System.in);

    /**initiates a hangman object
     * @param name the name
     * sets guess to an empty string
     */
    //constructor
    public Hangman(String name) {
        this.name = name;
        guess = "";

    }
    /** initiates another hangman object with no parameters*/
    public Hangman() {// overloaded constructor
        this.name = "Player";
        guess = "";
    }

    /** sets random word to a randomWord */
    public void initializeRandomWord() {
        randomWord = randomWord();
    }

    /** allows user to guess a letter repeatedly until a hangman is drawn or until user guesses word
     *
     * <p>
     *  If the user guesses wrong, there will be a word bank to pops up to remind the user what letters
     *  were already used and incorrect. It will also start drawing hangman using the hangman method below and
     *  stop once the user guesses up to 7 guesses.
     *  <p>
     *  If the user guesses a letter correct, it will replace that specific underscore with the letter
     *  the user guesses.
     * */
    public void hangmanPlay() {
        int guesses = 1;
        int wrongWords = 0;
        String guess = guess();
        String test = "";
        String temp = guess();
        String wordLine = "";
        String hangMan = hangMan(wrongWords);
        String letter = "";

        System.out.println(guess);// the spaces for the player to guess
        System.out.println("");
        System.out.print("Enter a letter: ");
        letter = scan.nextLine();
        System.out.println("");
        while (letter.length() != 1) {
            System.out.print("Enter a valid letter: ");
            letter = scan.nextLine();
            System.out.println("");
        }
        wordLine += letter;

        while (guesses < 7 && guess.indexOf("_") != -1 && !temp.equals(randomWord)) {
            test = "";
            for (int count = 0; count < randomWord.length(); count ++) {
                if ((randomWord.substring(count, count + 1)).equals(letter)){
                    test += letter;
                } else if (!randomWord.substring(count, count + 1).equals(letter)) {
                    test += "_";
                }
            }
            for (int count = 0; count < randomWord.length(); count ++) {
                if ((temp.substring(count, count + 1)).equals("_") && !(test.substring(count, count + 1)).equals("_")) {
                    temp = temp.substring(0, count) + test.substring(count, count + 1) + temp.substring(count + 1);
                }
            }
            if (temp.length() > guess.length()){
                temp = temp.substring(0, guess.length()); //to cut the extra spaces
            }

            if (test.equals(guess)) {
                guesses++;
                wrongWords++;
            }

            System.out.println("");
            if (guesses < 7) {
                System.out.println(temp);
                System.out.println("");
                hangMan = hangMan(wrongWords);
                System.out.println(hangMan);
                if (hangMan.length() == 1) {
                    System.out.println("");
                }
                if (!temp.equals(randomWord)) {
                    System.out.print("Inputted guesses: ");
                    System.out.println(wordLine);
                }
            }

            if (guesses < 7) {
                if (!temp.equals(randomWord)) {
                    System.out.print("Enter a letter: ");
                    letter = scan.nextLine();
                    while (letter.length() != 1) {
                        System.out.print("Enter a valid letter: ");
                        letter = scan.nextLine();
                        System.out.println("");
                    }
                    if (wordLine.indexOf(letter) == -1) {
                        wordLine += ", " + letter;
                    }
                }
            }
        }
        System.out.println("");
        hangMan = hangMan(7);
        System.out.println(hangMan);

        if (temp.equals(randomWord)) {
            System.out.println("You win, the word is " + randomWord + "!");
        }  else  {
            System.out.println("Oh no! You ran out of guesses. You lost :(");
            System.out.println("The word was " + randomWord + ".");
        }
    }

    /**
     * returns hangman picture when user guesses incorrectly
     * @param wrongWord the number of times user guesses wrongWord
     * @return the hangman picture
     */
    public String hangMan(int wrongWord) {
        if (wrongWord == 0) {
            return "";
        } else if (wrongWord == 1) {
            return "0";
        } else if (wrongWord == 2) {
            return "  0\n" +
                    "  |\n";
        } else if (wrongWord == 3) {
            return "  0\n" +
                    "  |\n" +
                    " /\n";
        } else if (wrongWord == 4) {
            return "  0\n" +
                    " \\|\n" +
                    " /\n";
        } else if (wrongWord == 5) {
            return "  0\n" +
                    " \\|\n" +
                    " / \\ ";
        } else if (wrongWord == 6) {
            return "  0\n" +
                    " \\|\n" +
                    " / \\ ";
        } else {
            return "  0\n" +
                    " \\|/\n" +
                    " / \\";
        }
    }

    /**
     * returns the guess word in underscores for the player to guess
     * @return the guess word in underscores
     */
    //player's guess
    public String guess() {
        for (int i = 0; i < randomWord.length(); i ++) {
            guess += "_";
        }
        return guess;

    }

    /**
     * Sets topic to a specific topic
     *
     * @param topic the number for a specific topic user inputted
     */
    public void setTopic(int topic) {
        if (topic == 1) {
            topics = 1;
            System.out.println("Topic: Fruit");
        } else if (topic == 2){
            topics = 2;
            System.out.println("Topic: Animals");
        } else {
            topics = 3;
            System.out.println("Topic: Countries");
        }
    }

    /**
     * returns randomWord from a specific topic user inputted
     * @return a random word
     */

    private String randomWord() {
        int randomNum = (int) (Math.random() * 10) + 1;
        //topic 1 (FRUITS)
        if (topics == 1 && randomNum == 1) {
            randomWord = "apple";
        }
        if (topics == 1 && randomNum == 2){
            randomWord = "passionfruit";
        }
        if (topics == 1 && randomNum == 3) {
            randomWord = "orange";
        }
        if (topics == 1 && randomNum == 4) {
            randomWord = "grape";
        }
        if (topics == 1 && randomNum == 5) {
            randomWord = "banana";
        }
        if (topics == 1 && randomNum == 6) {
            randomWord = "plum";
        }
        if (topics == 1 && randomNum == 7) {
            randomWord = "papaya";
        }
        if (topics == 1 && randomNum == 8){
            randomWord = "coconut";
        }
        if (topics == 1 && randomNum == 9) {
            randomWord = "dragonfruit";
        }
        if (topics == 1 && randomNum == 10) {
            randomWord = "cherry";
        }

        //topic 2 (ANIMALS)
        if (topics == 2 && randomNum == 1) {
            randomWord = "turtle";
        }
        if (topics == 2 && randomNum == 2) {
            randomWord = "pig";
        }
        if (topics == 2 && randomNum == 3) {
            randomWord = "cow";
        }
        if (topics == 2 && randomNum == 4) {
            randomWord = "chicken";
        }
        if (topics == 2 && randomNum == 5) {
            randomWord = "bobcat";
        }
        if (topics == 2 && randomNum == 6) {
            randomWord = "micropachycephalosaurus";
        }
        if (topics == 2 && randomNum == 7) {
            randomWord = "basilosaurus";
        }
        if (topics == 2 && randomNum == 8) {
            randomWord = "cockroach";
        }
        if (topics == 2 && randomNum == 9) {
            randomWord = "dragonfly";
        }
        if (topics == 2 && randomNum == 10) {
            randomWord = "hamster";
        }

        //topic 3 (COUNTRIES)
        if (topics == 3 && randomNum == 1) {
            randomWord = "afghanistan";
        }
        if (topics == 3 && randomNum == 2) {
            randomWord = "pakistan";
        }
        if (topics == 3 && randomNum == 3) {
            randomWord = "ukraine";
        }
        if (topics == 3 && randomNum == 4) {
            randomWord = "czechoslovakia";
        }
        if (topics == 3 && randomNum == 5) {
            randomWord = "scotland";
        }
        if (topics == 3 && randomNum == 6) {
            randomWord = "kyrgyzstan";
        }
        if (topics == 3 && randomNum == 7) {
            randomWord = "bangladesh";
        }
        if (topics == 3 && randomNum == 8) {
            randomWord = "america";
        }
        if (topics == 3 && randomNum == 9) {
            randomWord = "philippines";
        }
        if (topics == 3 && randomNum == 10) {
            randomWord = "korea";
        }
        return randomWord;
    }

    /**
     * prints a statement out if user finishes all guesses.
     */
    public void tempMethod() {
        System.out.println("The word was " + randomWord + "!");
    }
}