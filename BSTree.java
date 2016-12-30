import java.util.*;

/**
 * A Binary Tree to store the Context of word
 */

public class BSTree 
{
    private List<String> list;
    private Node root;

    public BSTree()
	{
        root = null;
    }

    /**
     * Inserts a word and its context into the Binary Search Tree
     *  word: value given by the user that has to be inserted
     *  context: value containing that word
     */
    public void insert(String word, String context) 
	{
        root = insert(word, context, root);
    }

    /**
     * Inserts a word and its context into the Binary Search Tree
         */
    private Node insert(String word, String context, Node node) 
	{
        if (node == null) 
		{
            Node newNode = new Node(word);
            newNode.getContexts().add(context);
            return newNode;
        } 
		else if (node.getWord().compareToIgnoreCase(word) == 0) 
		{
            node.getContexts().add(context);
        } 
		else if (node.getWord().compareToIgnoreCase(word) > 0) 
		{
            if (node.getLeft() == null) 
			{
                node.setLeft(insert(word, context, node.getLeft()));
            } 
			else 
			{
                insert(word, context, node.getLeft());
            }
        } 
		else if (node.getRight() == null) 
		{
            node.setRight(insert(word, context, node.getRight()));
        } 
		else 
		{
            insert(word, context, node.getRight());
        }
        return node;
    }

    /**
     * Generates List of String that matches the given String value
     */
    public List<String> searchWord(String word) 
	{
        list = new ArrayList<>();
        searchWord(word, root);
        return list;
    }

    /**
     * Generates a List of String that matches the given String value
    */
    private void searchWord(String word, Node node) 
	{
        if (node != null) 
		{
            if (node.getWord().startsWith(word)) 
			{
                list.add(node.getWord());
            }
            searchWord(word, node.getLeft());
            searchWord(word, node.getRight());
        }
    }

    /**
     * Generates a List of sentences that contains the given String value
     */
    public List<String> matchSentence(String word) 
	{
        list = new ArrayList<>();
        matchSentence(word, root);
        return list;
    }

    /**
     * Add all the sentences in a List that contains the given String value
      */
    private void matchSentence(String word, Node node) 
	{
        if (node != null) 
		{
            if (node.getWord().equalsIgnoreCase(word)) 
			{
                for (String context : node.getContexts()) 
				{
                    if (!list.contains(context)) 
					{
                        list.add(context);
                    }
                }
            } 
			else if (node.getWord().compareToIgnoreCase(word) > 0) 
			{
                matchSentence(word, node.getLeft());
            } 
			else 
			{
                matchSentence(word, node.getRight());
            }
        }
    }
}
