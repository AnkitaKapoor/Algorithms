
import java.util.*;

public class Node 
{
    private final String word;
    private final List<String> contexts;
    private Node left;
    private Node right;

     public Node(String word) 
	 {
        this.word = word;
        left = null;
        right = null;
        contexts = new ArrayList<>();
    }

    public void setLeft(Node node) 
	{
        left = node;
    }

    public void setRight(Node node) 
	{
        right = node;
    }

    public Node getLeft() 
	{
        return left;
    }

    public Node getRight()
	{
        return right;
    }

    public String getWord() 
	{
        return word;
    }

    public List<String> getContexts() 
	{
        return contexts;
    }
}
