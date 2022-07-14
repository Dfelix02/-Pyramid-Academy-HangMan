import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class HangMan {
    public static void hangManDialog(int guesses) {
        if (guesses == 0) {
            System.out.println("H A N G M A N");
            System.out.println("+___+");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("===");
        } else if (guesses == 1) {
            System.out.println("H A N G M A N");
            System.out.println("+___+");
            System.out.println("|  O");
            System.out.println("|");
            System.out.println("|");
            System.out.println("===");
        } else if (guesses == 2) {
            System.out.println("H A N G M A N");
            System.out.println("+___+");
            System.out.println("|  O");
            System.out.println("| /");
            System.out.println("|");
            System.out.println("===");
        } else if (guesses == 3) {
            System.out.println("H A N G M A N");
            System.out.println("+___+");
            System.out.println("|  O");
            System.out.println("| /|");
            System.out.println("|");
            System.out.println("===");
        } else if (guesses == 4) {
            System.out.println("H A N G M A N");
            System.out.println("+___+");
            System.out.println("|  O");
            System.out.println("| /|\\");
            System.out.println("|");
            System.out.println("===");
        } else if (guesses == 5) {
            System.out.println("H A N G M A N");
            System.out.println("+___+");
            System.out.println("|  O");
            System.out.println("| /|\\");
            System.out.println("| /");
            System.out.println("===");
        } else {
            System.out.println("H A N G M A N");
            System.out.println("+___+");
            System.out.println("|  O");
            System.out.println("| /|\\");
            System.out.println("| / \\");
            System.out.println("===");
        }

    }

    public static ArrayList<Character> word() throws FileNotFoundException {
        File wordsFile = new File("C:\\GenSpark\\projects\\HangMan\\src\\words");
        Scanner readWordFile = new Scanner(wordsFile);
        ArrayList<Character> wordCharacters = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        while (readWordFile.hasNextLine()) {
            words.add(readWordFile.next());
        }

        int randomNum = (int) (Math.random() * words.size());
        String word = words.get(randomNum);
        int wordLength = word.length();

        for (int i = 0; i < wordLength; i++) {
            wordCharacters.add(word.charAt(i));
        }
        return wordCharacters;
    }

    public static char userCharGuess() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("\nGuess a letter");
        String userGuess = userInput.nextLine();
        while ((userGuess.length() != 1) || !Character.isAlphabetic(userGuess.charAt(0))) {
            System.out.println("Guesses should be a single character at the time, (a-z)");
            System.out.println("Guess a letter");
            userGuess = userInput.nextLine();
        }

        return userGuess.charAt(0);
    }

    public static void dialog() throws FileNotFoundException {
        ArrayList<Character> word = word();
        ArrayList<Character> userWordGuesses = new ArrayList<>();
        for (int i = 0; i < word.size(); i++) {
            userWordGuesses.add('_');
        }
        HashSet<Character> userGuesses = new HashSet<>();
        int guesses = 0;
        boolean guessed = true;
        do {
            hangManDialog(guesses);
            System.out.println("Missed letters");
            for (Character character : userWordGuesses) {
                System.out.print(character);
            }
            char guess = userCharGuess();

            while (userGuesses.contains(guess)) {
                System.out.println("You have already guessed this character");
                guess = userCharGuess();
            }
            userGuesses.add(guess);
            if (word.contains(guess)) {
                for (int i = 0; i < word.size(); i++) {
                    if (word.get(i).equals(guess)) {
                        userWordGuesses.remove(i);
                        userWordGuesses.add(i, word.get(i));
                    }
                }

            } else {
                guesses++;
            }
            if (word.equals(userWordGuesses)) {
                guessed = false;
            }

        } while (guesses < 6 && guessed);
        if (word.equals(userWordGuesses)){
            System.out.println("Congratulations You have guessed the word!");
        }else {
            System.out.println("Better Luck next Time!");
        }


    }
    public static char continueGame(){
        System.out.println("Would you like to play again? (y or n)\n");
        Scanner userInput = new Scanner(System.in);
        String userContinue = userInput.nextLine();
        while(userContinue.equalsIgnoreCase("y")&&userContinue.equalsIgnoreCase("n")){
            System.out.println("(y or n)\n");
            userContinue = userInput.nextLine();
        }
        return userContinue.charAt(0);
    }

    public static void main(String[] args) throws FileNotFoundException {
        dialog();
        char userContinue = continueGame();
        while(userContinue == 'y'){
            dialog();
            userContinue = continueGame();
        }
        System.out.println("Thanks for playing!");


    }
}

