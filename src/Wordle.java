//The whole code was written by Md. Atik Shahariar
//ide intellij idea ultimate, with open jdk 18.0
//the 5_letter_words.txt  file was collected from  https://github.com/charlesreid1/five-letter-words/blob/master/sgb-words.txt

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class Wordle {

    // This function reads the 5_letter_words.txt file and returns a list of all the words in the file.
     private static List word_list() throws FileNotFoundException {
        File file = new File("E:\\Wordle In Java\\Wordle_in_java\\src\\5_letter_words.txt");  //put text the file path in here
        Scanner input = new Scanner(file);
        List<String> wordlist=new ArrayList<String>();

        while (input.hasNext()) {
            String word  = input.next();
            wordlist.add(word);
        }
        return wordlist;
    }
    //this function takes a list of words as a parameter and returns a random word from this list
    private static String random_word(List<String> wordlist){
        Random r=new Random();
        int random_index=r.nextInt(wordlist.size());
        return wordlist.get(random_index);
    }

    //#This function takes two parameters, a guess and a word list and returns True if the guess word is in the word list and False otherwise.
    private static boolean is_real_word(String guess, List<String> wordlist){
        if (wordlist.contains(guess)) {
            return true;
        }
        else
        {
            return false;
        }
    }



    /*
##This function takes two parameters. The first is the guessed word and the second is the word the user has to find. check_guess() returns a string containing the following characters:
##X for each character in the guess that is at the correct position.
##O for each character in the guess that is in the word but not at the correct position.
##_ for each character in the guess that is not part of the word. For example, check_guess("birds", "words") should return __XXX.
##If a letter is used twice in the guessed word and exists only once in the word to be found, then only one letter in the return string is marked. In case one of the two letters is positioned correctly, then this letter is marked with an X in the return string. For example, check_guess("carat", "train") should return _OO_O. Another example, check_guess("taunt", "train") should return XO_O_
 */
    private static String check_guess(String guess,String find){
         String x0="_____";                                                                 //This string will hold the output pattern. by defaut it is considered that, the guess word doesnot match with the find word at all so pattern is "_____"

         for(int i=0;i<guess.length();i++){                                                 //this loop finds if any character of the guess word and the find word has same value while having SAME index number
             if(guess.charAt(i)==find.charAt(i)){
                 x0 = x0.substring(0, i) + 'X' + x0.substring(i + 1);              //##replaces '_' character of the xo string with character 'X'
                 find = find.substring(0, i) + ' ' + find.substring(i + 1);        //replaces the character of the find string with character ' ', to avoid comparing with the same element over and over again
             }
         }

        for(int i=0;i<guess.length();i++){                                                  //this loop finds if any character of the guess word and the find word has has same value while having DIFFERENT index number
            for(int j=0;j<find.length();j++){
                if((guess.charAt(i)==find.charAt(j))&&(x0.charAt(i)=='_') ){
                    x0 = x0.substring(0, i) + 'O' + x0.substring(i + 1);           //replaces '_' character of the xo string with character 'O'
                    find = find.substring(0, j) + ' ' + find.substring(j + 1);     //replaces the character of the find string with character ' ', to avoid comparing with the same element over and over again
                }
            }
        }
        System.out.println(x0);
        return x0;
    }



//This function takes a word list as a parameter. The function asks the user for a guess, converts the guess to lower case and checks if the guess is in the word list. If this is the case, the guess is returned. Otherwise, the function asks the user for another guess.
    private static String next_guess(List<String> wordlist){
         Scanner myObj = new Scanner(System.in);
         System.out.println("Please enter a guess: ");
         String user_input=myObj.nextLine();
         user_input=user_input.toLowerCase();
         while (is_real_word(user_input,wordlist)==false){                                   //while loop keeps running until a word from the word list is gussed
            System.out.println("That's not a real word!");
            System.out.println("Please enter a guess: ");
            user_input=myObj.nextLine();
            user_input=user_input.toLowerCase();
         }
         return user_input;
    }



    /*

#this function
#Uses the functions word_list and random_word to select a random 5 letter word.
#Asks the user for a guess using the next_guess function.
#Checks each guess using the check_guess function and shows the result to the user.
#Checks if the users guessed the right word with six guesses or less. If yes, the user wins and the function prints You won!. Otherwise the user loses and
#the function prints You lost! as well as The word was: followed by the word the user had to find.

     */
    private static void play() throws FileNotFoundException {
        List<String>wordlist=word_list();
        String randomword= random_word(wordlist);                                          //selects a random word from the word list
        //System.out.println(randomword);
        int count=0;
        boolean win_probability=false;                                                     //false means users has not won or lost yet
        while(win_probability==false && count<6){                                          //until user wins or looses or tries six times the while loop keeps running,
            count=count+1;
            if(check_guess(next_guess(wordlist),randomword).equals("XXXXX")){              //if user gusses the word correctly he/she wins
                System.out.println("You won!");
                win_probability=true;
            }
            else if(count==6 && win_probability==false){                                   //users tries 6 times still could not guess correctly, he/she looses
                win_probability = true;
                System.out.println("You lost!");
                System.out.println("The word was: "+randomword);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
       play();

    }
}


       /*check_guess("fungi","jujus");
        check_guess("hands","banal");
        check_guess("hands","jenny");
        check_guess("spats","heals");
        check_guess("carat", "train");
        check_guess("taunt", "train");*/