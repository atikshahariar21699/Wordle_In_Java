import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class Wordle {

     private static List word_list() throws FileNotFoundException {
        File file = new File("E:\\Wordle In Java\\Wordle_in_java\\src\\test.txt");
        Scanner input = new Scanner(file);
        List<String> wordlist=new ArrayList<String>();
        int count = 0;
        while (input.hasNext()) {
            String word  = input.next();
            wordlist.add(word);
           // System.out.println(word);
           // count = count + 1;
        }
        //System.out.println(wordlist.size());
        return wordlist;

        //System.out.println("Word count: " + count);
    }

    private static String random_word(List<String> wordlist){
        Random r=new Random();
        int random_index=r.nextInt(wordlist.size());
        return wordlist.get(random_index);
    }

    public static void main(String[] args) throws FileNotFoundException {
       /* Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username ");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);*/
        List<String> wordlist=word_list();

        System.out.println(random_word(wordlist));
    }
}
