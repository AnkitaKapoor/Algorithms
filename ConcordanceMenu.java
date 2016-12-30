
import java.util.*;

public class ConcordanceMenu 
{
/**
     * The menu is displayed, which accept the letters ‘R’, ‘M’, ‘S’, ‘W’ or ‘Q’ and be case insensitive to perform operations
     */
    public static void main(String[] args) 
	{
        Scanner kb = new Scanner(System.in);
        String choice;
        Concordance concordance = new Concordance();
        System.out.println("Enter any of the following letter [X] to perform specified operation");
        do {
            System.out.println("[R] Read in a book and build the concordance\n"
                    + "[M] List the words that start with a given string\n"
                    + "[S] Search for a word and the sentences in which it appears\n"
                    + "[W] Write to a file the sentences in which a word appears\n"
                    + "[Q] Quit");
            System.out.print("Enter choice:");
            choice = kb.next();
            switch (choice.toUpperCase()) 
			{
                case "R":
                    System.out.println("Enter the name of file for which Concordance is to be built:");
                    String fileName = kb.next();
                    concordance.buildConcordance(fileName);
                    break;
                case "M":
                    System.out.println("Enter a pattern to be searched:");
                    String pattern = kb.next();
                    concordance.matchAndDisplay(pattern);
                    break;
                case "S":
                    System.out.println("Enter a word to be searched:");
                    pattern = kb.next();
                    concordance.searchAndDisplay(pattern);
                    break;
                case "W":
                    System.out.println("Enter a word to be searched:");
                    pattern = kb.next();
                    concordance.searchAndSave(pattern);
                    break;
                case "Q":
                    System.exit(0);
                default:
                    System.out.println("Invalid input enter again");
            }
        }
		while (!choice.equalsIgnoreCase("Q"));
    }

}
