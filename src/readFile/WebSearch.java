package readFile;
import java.io.*;
import java.util.*;

public class WebSearch 
{
	/*The toString method accepts any field of type ArrayList.
	 * In this case, the toString method is used both on type
	 * ArrayList<String> and ArrayList<Integer>.
	 * First a string is created, what the method will return.
	 * The method loops through the length of the ArrayList and
	 * adds to the string the value at that ArrayList index.
	 * If the current iteration is not on the last ArrayList index,
	 * a comma is added to separate the output values.	
	 */
	public static String toString(ArrayList<?> values)
	{
		String result = "";
		for (int i = 0; i < values.size(); i++)
		{
			result += values.get(i);
			if (i != values.size() - 1)
				result += ", ";
		}
		return result;
	}

	/*In the Method BFS, the endID and the origin Node is put in.  A new Queue 
	 * of Nodes is created and the origin node is added to the Queue.
	 * Steps is set to 0 as it will be incremented as a path is followed. 
	 * Degrees is set to -1, which will be returned once the link is found 
	 * or not found. Next each link that was set to true for visited 
	 * is set to false to make sure that the graph is reset to its original
	 * graph.  Next, While the Queue is not empty each node is removed from 
	 * the Queue and if this is the same as endID, the number of steps is 
	 * returned. If its node id != endID more nodes are enqueued to q and the 
	 * process is repeated until the queue is empty. If found, the degrees is 
	 * returned else -1 is returned.
	 */
	static int BFS (int endID, Node origin)
	{
		Queue<Node> nodeQueue = new LinkedList<Node>();
		origin.steps = 0;
		nodeQueue.add(origin);
		int degrees = -1;
		Node n = null;
		nodeQueue.add(origin);
		while (!nodeQueue.isEmpty())
		{
			n = nodeQueue.remove();
			for(Node adjacent : n.link)
			{
				if(adjacent.visited)
				{
					nodeQueue.add(adjacent);
					adjacent.visited = false;
				}
			}
		}
		nodeQueue.add(origin);
		while (!nodeQueue.isEmpty())
		{
			n = nodeQueue.remove();
			if (n.id == endID)
				degrees = n.steps;
			else
			{
				for(Node adjacent : n.link)
				{
					if(!adjacent.visited)
					{					
						adjacent.steps = n.steps + 1;
						nodeQueue.add(adjacent);
						adjacent.visited = true;
					}
				}
			}
		}
		return degrees;
	}
	public static void main(String[] args) throws IOException, BadInputException 
	{
		ReadFile rf = new ReadFile(args[0]);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numPages = rf.numPages;
		boolean repeat = false;
		ArrayList<Node> nodeList = new ArrayList<Node>();
		System.out.println("Welcome to WebSearch!");
		while (true)
		{
			System.out.println("");
			System.out.println("Choose an option or type 'quit' to quit:");
			System.out.println("1. Print Graph");
			System.out.println("2. Degrees of Separation");
			String menu = br.readLine();
			if (menu.equals("1"))
			{
			/* When menu equals 1, a for loop iterates through each 
			 * value of numPages, printing the current numPage value,
			 * converting the keyPhrases ArrayList to a String, followed
			 * by converting the OutLinks ArrayList to a String.
			 */
				System.out.println("");
				System.out.println("Printing Graph...");
				for (int i = 0; i < numPages; i++)
					System.out.println(i + " (" + toString(rf.getKeyPhrases(i)) + "): " + toString(rf.getOutLinks(i)));
			}
			else if (menu.equals("2"))
			/* When menu equals 2, an array of new nodes is created for 
			 * each of the pages and within each node is an array that 
			 * stores the outLinks of each page. Once the array of nodes 
			 * is created, each of the nodes within nodeList is accessed 
			 * and the size is received.  Once the size is received a 
			 * graph of nodes is created.
			 */
			{
				if(!repeat)
				{
					for (int i = 0; i < numPages; i++)
					{
						Node newNode = new Node(i, rf.getOutLinks(i));
						nodeList.add(newNode);
					}
					for (int i = 0; i < nodeList.size(); i++)
					{
						for (int j = 0; j < nodeList.get(i).outLinks.size(); j++)
						{		
							int x = nodeList.get(i).outLinks.get(j);
							nodeList.get(i).link.add(nodeList.get(x));
						}
					}
					repeat = true;
				}
				System.out.println("Title of origin page > ");
				String origin = br.readLine();
				System.out.println("Title of destination page > ");
				String destination = br.readLine();
				
				/*In the code below, once the index of the origin and destination 
				 * is found, the destinationID and the originID from the node list 
				 * is used within the BFS(Breadth First Search) method, and once the 
				 * answer is found it is printed out.
				 */
				int originID = 0;
				int destinationID = 0;
				for (int i = 0; i < nodeList.size(); i++)
				{
					if (origin.equalsIgnoreCase(rf.getPageTitle(i)))
						originID = i;
				}
				for (int j = 0; j < nodeList.size(); j++)
				{
					if (destination.equalsIgnoreCase(rf.getPageTitle(j)))
						destinationID = j;
				}
				int degrees = BFS(destinationID, nodeList.get(originID));
				System.out.print("Degrees of separation between pages " + "\"" + origin + "\"" + " and \"" + destination +"\": " );
				System.out.println(degrees);
			}
			else if (menu.equals("quit"))
				System.exit(0);
		}
	}
}