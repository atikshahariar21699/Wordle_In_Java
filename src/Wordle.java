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
        File file = new File("E:\\Wordle In Java\\Wordle_in_java\\src\\5_letter_words.txt");
        Scanner input = new Scanner(file);
        List<String> wordlist=new ArrayList<String>();
        while (input.hasNext()) {
            String word  = input.next();
            wordlist.add(word);
        }
        return wordlist;
    }
    private static String random_word(List<String> wordlist){
        Random r=new Random();
        int random_index=r.nextInt(wordlist.size());
        return wordlist.get(random_index);
    }

    private static boolean is_real_word(String guess, List<String> wordlist){
        if (wordlist.contains(guess)) {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static String check_guess(String guess,String find){
         String x0="_____";

         for(int i=0;i<guess.length();i++){
             if(guess.charAt(i)==find.charAt(i)){
                 x0 = x0.substring(0, i) + 'X' + x0.substring(i + 1);
                 find = find.substring(0, i) + ' ' + find.substring(i + 1);
             }
         }

        for(int i=0;i<guess.length();i++){
            for(int j=0;j<find.length();j++){
                if((guess.charAt(i)==find.charAt(j))&&(x0.charAt(i)=='_') ){
                    x0 = x0.substring(0, i) + 'O' + x0.substring(i + 1);
                    find = find.substring(0, i) + ' ' + find.substring(i + 1);
                }
            }
        }
        System.out.println(x0);
        return x0;
    }

    private static String next_guess(List<String> wordlist){
         Scanner myObj = new Scanner(System.in);
         System.out.println("Please enter a guess: ");
         String user_input=myObj.nextLine();
         user_input=user_input.toLowerCase();
         while (is_real_word(user_input,wordlist)==false){
            System.out.println("That's not a real word!");
            System.out.println("Please enter a guess: ");
            user_input=myObj.nextLine();
            user_input=user_input.toLowerCase();
         }
         return user_input;
    }

    private static void play() throws FileNotFoundException {
        List<String>wordlist=word_list();
        String randomword= random_word(wordlist);
        System.out.println(randomword);
        int count=0;
        boolean win_probability=false;
        while(win_probability==false && count<6){
            count=count+1;
            if(check_guess(next_guess(wordlist),randomword).equals("XXXXX")){
                System.out.println("You won!");
                win_probability=true;
            }
            else if(count==6 && win_probability==false){
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