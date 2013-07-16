package readFile;
import java.util.ArrayList;

public class Node 
{
	/*In the Node class, an ArrayList outLinks is created which stores 
	 * integers of outLinks for each page. The ArrayList link of type 
	 * Node is created to store the graphical information
	 * steps is implemented to keep track of the number of steps used to
	 * get from one page to another in a shortest manner
	 * id is implemented to assign a unique ID for each Node.
	 * visited is implemented to set the page as visited when looking 
	 * for a shortest path in the BFS algorithm.
	 * The constructor of Node is created which stores a boolean as well
	 * as the Id and the list of outLinks for each page.
	 */

	ArrayList<Node> link = new ArrayList<Node>();
	ArrayList<Integer> outLinks = new ArrayList<Integer>();
	int steps;
	int id;
	boolean visited;
	
	public Node(int i, ArrayList<Integer> links) 
	{
		this.id = i;
		this.outLinks = links;
		this.visited = false;
	}
}