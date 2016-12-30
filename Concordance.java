import java.io.*;
import java.util.*;

public class Concordance 
{

    private ArrayList<String> sentences;    //Contains list of sentences from text file
    private Scanner reader;                 
    private BSTree tree;                    //Binary Search Tree to build Concondrance
    private int sentenceCounter;            //Keeps track of number of sentences

    /**
     * Builds a Concordance
     */
    public void buildConcordance(String fileName) 
	{
        try 
		{
            File file = new File(fileName);
            tree = new BSTree();
            sentences = new ArrayList<>();
            reader = new Scanner(file);
            sentenceCounter = 1;
            String text = "";
            while (reader.hasNext()) 
			{
                //Read file line-by-line till we reach end of a line.
                text += reader.nextLine();
                //A sentence ends either with '.', '!', '?' and one more none character(\W) is included to include '"' incase of ending quotes. The '$' symbol is used to match the pattern at the end of String
                Pattern delimiterPattern = Pattern.compile("(!|\\.|\\?)\\W$");
                Matcher matcher = delimiterPattern.matcher(text);
                //If a match is found that means we've read enough lines to make a sentence as per above rules
                if (matcher.find())
					{
                    //We might have read more than the length of sentence which can be used further and extract the remaining part
                    String sentence = text.substring(0, matcher.end());
                    Pattern sentencePattern = Pattern.compile("(\\.|!|\\?)");
                    Matcher match = sentencePattern.matcher(sentence);
                    int start = 0, end;
                    while (match.find())
						{
                        end = match.end();
                        //adding sentence to arraylist
                        sentences.add("[" + sentenceCounter + "]: " + sentence.substring(start, end).trim());
                        //increment the sentence counter
                        sentenceCounter++;
                        start = end;
                    }
                    //Use the remaining part of lines read from the file to complete next sentence
                    text = text.substring(matcher.end());
                    } 
				else
					{
                    text = text.concat(" ");
                    }
            }
            //Incase the last line of file doesn't ends with any of the delimiters it must considered as a sentence.
            if (text.length() > 0)
				{
                sentences.add(text);
                }
           
		   //Separate out words from the sentences and store it in Binary Search Tree
            for (String sentence : sentences)
				{
                Pattern pattern = Pattern.compile("((\\w+))");
                Matcher matcher = pattern.matcher(sentence);
                while (matcher.find()) 
				{
                    String word = matcher.group().toUpperCase();
                    tree.insert(word, sentence);
                }
            }
            System.out.println("Concordance is built");
        } 
		catch (FileNotFoundException ex)
		{
            System.out.println("The input file does not exist!");
        }
    }

    /**
     * Finds all matching words from the Concordance which matches the word given by user.
          */
    public void matchAndDisplay(String word)
	{
        if (tree != null)
		{
            if (word != null)
				{
                System.out.println("Looking for words starting with : " + word);
                List<String> matchWord = tree.searchWord(word.toUpperCase());
                System.out.println("Number of words matched : " + matchWord.size());
                //alphabeticall arrange
                if (!matchWord.isEmpty())
					{
                    //Converted List into array for sorting
                    String[] wordsArray = matchWord.toArray(new String[matchWord.size()]);
                    //Applied bubble-sort algorithm to ascendingly sort the words
                    for (int i = 0; i < wordsArray.length; i++) 
					{
                        for (int j = 0; j < wordsArray.length - 1; j++) 
						{
                            if (wordsArray[j].compareToIgnoreCase(wordsArray[j + 1]) > 0) 
							{
                                String temp = wordsArray[j + 1];
                                wordsArray[j + 1] = wordsArray[j];
                                wordsArray[j] = temp;
                            }
                        }
                    }
                    //Cleared the List containig unsorted words
                    matchWord.clear();
                    //Converted the Array back into sorted List
                    matchWord.addAll(Arrays.asList(wordsArray));
                    for (int i = 0; i < matchWord.size(); i++) 
					{
                        System.out.print(matchWord.get(i));
                        if (i < matchWord.size() - 1) 
						{
                            System.out.print(" , ");
                        }
                    }
                    System.out.println(""); //To print the ConcordanceMenu from newline
                }
            }
        } 
		else
		{
            System.out.println("Concordance isn't built. Please build the Concordance first");
        }
    }

    /**
     * Displays all the lines which contains the given word
          */
    public void searchAndDisplay(String word) 
	{
        if (tree != null)
		{
            if (sentences != null) 
			{
                System.out.println("Search word : " + word);
                List<String> matchedSentences = tree.matchSentence(word.toUpperCase());
                System.out.println("Number of sentences : " + matchedSentences.size());
                System.out.println();
                if (matchedSentences.size() > 0) 
				{
                    for (int i = 0; i < matchedSentences.size(); i++) 
					{
                        System.out.println("(" + (i + 1) + ") " + matchedSentences.get(i));
                        System.out.println("");
                    }
                }
            }
        }
		else
		{
            System.out.println("Concordance isn't built. Please build the Concordance first");
        }
		
    }

    /**
     * Displays all the lines containing the given word and saves the result in the form of text file
         */
    public void searchAndSave(String word) 
	{
        if (sentences != null) 
		{
            System.out.println("Search word : " + word);
            List<String> matchedSentences = tree.matchSentence(word);
            System.out.println("Number of sentences : " + matchedSentences.size());
            if (matchedSentences.size() > 0) 
			{
                String fileName = "ContextOf-" + word.toUpperCase() + ".txt";
                File outputFile = new File(fileName);
                System.out.println("Data saved in " + fileName);
                try (PrintWriter writer = new PrintWriter(outputFile, "UTF-8")) 
				{
                    for (int i = 0; i < matchedSentences.size(); i++) 
					{
                        writer.println("(" + (i + 1) + ") " + matchedSentences.get(i));
                        writer.println("");
                    }
                    writer.flush();
                } 
				catch (FileNotFoundException ex) 
				{
                    System.out.println("File doesn't exist");
                }
            }
        } 
		else 
		{
            System.out.println("Concordance isn't built. Please build the Concordance first");
        }
    }
}
