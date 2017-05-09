package BruceNodesLinks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class GraphTraversal {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Node> graph  = new ArrayList<Node>();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the path to your input file.");
		String firstChoice = "";
		firstChoice = s.nextLine();
		File f;
		
		f = new File(firstChoice);

		Scanner fileS = new Scanner(f);
		while (fileS.hasNextInt())
		{
			int n = fileS.nextInt();
			int l = fileS.nextInt();
			int w = fileS.nextInt();
			
			while(graph.size()-1 < n)
			{
				graph.add(new Node());
			}
			while(graph.size()-1 < l)
			{
				graph.add(new Node());
			}
			
			graph.get(n).addLink(l,w);
			graph.get(n).name = n;
			graph.get(l).addLink(n,w);
			graph.get(l).name = l;
			
//			graph.set(n,n1);
//			graph.set(l,n2);
			
			
		}
		//System.out.println("Graph has been made!");
		System.out.println("Enter 0 for Depth First search, 1 for Breadth First, or 2 for both.");
		boolean depth = false;
		boolean breadth = false;
		int secondChoice = s.nextInt();
		if(secondChoice == 0 || secondChoice == 2)
		{
			depth = true;
		}
		if(secondChoice == 1 || secondChoice == 2)
		{
			breadth = true;
		}
		if(depth)
		{
			System.out.println("Enter a number between 0 and " + (graph.size()-1) + " for our starting point.");
			int startPos = s.nextInt();
			int currentPosition = startPos;
			Node currentNode;
			//int depthResult = 0;
			String depthOutput = "[";
			boolean[] hasVisited = new boolean[graph.size()];
			boolean[] hasStacked = new boolean[graph.size()];
			for(int i = 0; i < hasVisited.length;i++)
			{
				hasVisited[i] = false;
				hasStacked[i] = false;
			}
			Stack<Node> stack = new Stack<Node>();
			stack.push(graph.get(currentPosition));
			hasStacked[currentPosition] = true;
			while(stack.size() > 0)
			{
				currentNode = (Node) stack.pop();
				System.out.println("Popped node " + currentNode.name + ".");
				depthOutput += currentNode.name;
				depthOutput += ',';
				currentPosition = currentNode.name;
				
				if(!hasVisited[currentPosition]) 
				{
					hasVisited[currentPosition] = true;
					for(int i = 0; i < currentNode.Links.size(); i++)
					{
						if(!hasVisited[currentNode.Links.get(i).index] && !hasStacked[currentNode.Links.get(i).index])
						{
							stack.push(graph.get(currentNode.Links.get(i).index));
							hasStacked[currentNode.Links.get(i).index] = true;
							System.out.println("Pushed node " + stack.peek().name);
							}
					}
					//depthResult++;
					
				}
			}
			depthOutput = depthOutput.substring(0, depthOutput.length()-1);
			System.out.println("Starting from vertex " + startPos + " the Depth First value is " + depthOutput + "].");	
		}
		if(breadth)
		{
			System.out.println("Enter a number between 0 and " + (graph.size()-1) + " for our starting point.");
			Queue<Node> q = new LinkedList<Node>();
			int startPos = s.nextInt();
			graph.get(startPos).breadthDistance = 0;
			q.add(graph.get(startPos));
			Node currentNode = null;
			String breadthOutput = "[";
			while(!q.isEmpty())
			{
				currentNode = q.remove();
				breadthOutput += currentNode.name;
				breadthOutput += ',';
				
				for(int i = 0; i < currentNode.Links.size(); i++)
				{
					if(graph.get(currentNode.Links.get(i).index).breadthDistance < 0)
					{
						graph.get(currentNode.Links.get(i).index).breadthDistance = currentNode.breadthDistance+1;
						graph.get(currentNode.Links.get(i).index).breadthParent = currentNode;
						q.add(graph.get(currentNode.Links.get(i).index));
					}
				}
			}
			breadthOutput = breadthOutput.substring(0, breadthOutput.length()-1);
			System.out.println("Starting from vertex " + startPos + " the Breadth First value is " + breadthOutput + "].");
			
			
		}
		s.close();
		fileS.close();
	}
	

}
