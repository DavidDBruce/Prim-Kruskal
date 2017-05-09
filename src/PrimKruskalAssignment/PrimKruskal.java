package PrimKruskalAssignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class PrimKruskal {
	public static void main(String[] args0) throws FileNotFoundException
	{
		HashMap<String,Node2> graph  = new HashMap<String,Node2>();
		ArrayList<Edge> theEdges = new ArrayList<>();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the path to your input file.");
		String firstChoice = "";
		firstChoice = s.nextLine();
		File f;
		
		f = new File(firstChoice);

		Scanner fileS = new Scanner(f);
		while (fileS.hasNext())
		{
			ArrayList<String> theKeys = new ArrayList<>();
			String source = fileS.next();
			String dest = fileS.next();
			int wt = fileS.nextInt();
			
			boolean srcNew = true;
			boolean destNew = true;
			
			theKeys.addAll(graph.keySet());
			for(int i = 0; i < theKeys.size(); i++)
			{
				if(theKeys.get(i).equals(source))
				{
					srcNew = false;
				}
				else if(theKeys.get(i).equals(dest))
				{
					destNew = false;
				}
			}
			
			if(srcNew && destNew)
			{
				Node2 srcNode = new Node2();
				Node2 destNode = new Node2();
				srcNode.addLink(dest, wt);
				destNode.addLink(source, wt);
				graph.put(source, srcNode);
				graph.put(dest, destNode);
			}
			else if(srcNew && !destNew)
			{
				Node2 srcNode = new Node2();
				srcNode.addLink(dest, wt);
				graph.get(dest).addLink(source, wt);
				graph.put(source, srcNode);
			}
			else if(!srcNew && destNew)
			{
				Node2 destNode = new Node2();
				graph.get(source).addLink(dest, wt);
				destNode.addLink(source, wt);
				graph.put(dest, destNode);
			}
			else if(!srcNew && !destNew)
			{
				graph.get(source).addLink(dest,wt);
				graph.get(dest).addLink(source,wt);
			}
			else
			{
				System.out.println("Something isn't right.");
			}
			theEdges.add(new Edge(source,dest,wt));
		}
		
		System.out.println("Prims coming up...");
		
		ArrayList<String> theKeysReached = new ArrayList<>();
		ArrayList<String> theKeysUnreached = new ArrayList<>();
		ArrayList<Edge> primOrder = new ArrayList<>();
		theKeysUnreached.addAll(graph.keySet());
		Random r = new Random();
		int firstNode = 0;
				//r.nextInt(theKeysUnreached.size());
		theKeysReached.add(theKeysUnreached.get(firstNode));
		theKeysUnreached.remove(firstNode);
		
		ArrayList<Edge> curEdges = new ArrayList<>();
		while(!theKeysUnreached.isEmpty())
		{
			for(int i = 0; i < theEdges.size(); i++)
			{	
				if(theEdges.get(i).pointA.equals(theKeysReached.get(theKeysReached.size()-1)) || theEdges.get(i).pointB.equals(theKeysReached.get(theKeysReached.size()-1))) // If it has either A or B but...
				{
					if(!(theEdges.get(i).pointA.equals(theKeysReached.get(theKeysReached.size()-1)) && theEdges.get(i).pointB.equals(theKeysReached.get(theKeysReached.size()-1)))) // not both.
					{
						if(!primOrder.contains(theEdges.get(i)) && !curEdges.contains(theEdges.get(i))) //If the edge is not already in our edges.
						{
							if(!(theKeysReached.contains(theEdges.get(i).pointA) && theKeysReached.contains(theEdges.get(i).pointB)))
							{
								curEdges.add(theEdges.get(i));
							}
						}
						
					}
					
				}
			}
			int smallestEdge = curEdges.get(0).weight;
			
			for(int j = 0; j < curEdges.size(); j++)
			{
				if(smallestEdge > curEdges.get(j).weight)
				{
					smallestEdge = curEdges.get(j).weight;
				}
			}
			for(int j = 0; j < curEdges.get(0).weight; j++)
			{
				if(smallestEdge == curEdges.get(j).weight)
				{
					primOrder.add(curEdges.get(j));
					if(theKeysReached.contains(curEdges.get(j).pointB))
					{
						theKeysReached.add(curEdges.get(j).pointA);
					}
					else
					{
						theKeysReached.add(curEdges.get(j).pointB);
					}
					curEdges.remove(j);
					for(int k = 0; k < theKeysUnreached.size(); k++)
					{
						if(theKeysUnreached.get(k).equals(theKeysReached.get(theKeysReached.size()-1)))
						{
							theKeysUnreached.remove(k);
							break;
						}
					}
					break;
				}
			}
			for(int i = 0; i < curEdges.size(); i++)
			{
				if(theKeysReached.contains(curEdges.get(i).pointA) && theKeysReached.contains(curEdges.get(i).pointB)) //If point A and B are both reached drop the edge.
				{
					curEdges.remove(i);
				}
			}
		}
		String primOutput = "{";
		for(int i = 0; i < primOrder.size(); i++)
		{
			primOutput += "(";
			primOutput += primOrder.get(i).pointA;
			primOutput += ",";
			primOutput += primOrder.get(i).pointB;
			primOutput += ")";
		}
		System.out.println(primOutput + "}");
		
		System.out.println("Kruskal coming up...");
		
		int[] forSorting = new int[theEdges.size()];
		for(int i = 0; i < theEdges.size(); i++)
		{
			forSorting[i] = theEdges.get(i).weight;
		}
		ArrayList<Edge> sortedEdges = new ArrayList<>();
		Arrays.sort(forSorting);
		for(int i = 0; i < forSorting.length; i++)
		{
			for(int j = 0; j < theEdges.size(); j++)
			{
				if(forSorting[i] == theEdges.get(j).weight)
				{
					sortedEdges.add(theEdges.get(j));
					break;
				}
			}
		}
		

		ArrayList<Edge> kruskalOrder = new ArrayList<>();
		ArrayList<String> theKeys = new ArrayList<>();
		theKeys.addAll(graph.keySet());
		HashMap<String,HashSet<String>> vset = new HashMap<>();
		for(int i = 0; i < theKeys.size(); i++)
		{
			HashSet<String> v = new HashSet<>();
			v.add(theKeys.get(i));
			
			vset.put(theKeys.get(i), v);
		}
		
		
		
		for(int i = 0; i < theEdges.size(); i++)
		{
			HashSet<String> v = new HashSet<>();
			if(!vset.get(sortedEdges.get(i).pointA).contains(sortedEdges.get(i).pointB))
			{
				kruskalOrder.add(sortedEdges.get(i));
				v.addAll(vset.get(sortedEdges.get(i).pointA));
				v.addAll(vset.get(sortedEdges.get(i).pointB));
				for(String u:v)
				{
					vset.put(u,v);
				}
			}
				
		}
		
		String kruskalOutput = "{";
		for(int i = 0; i < kruskalOrder.size(); i++)
		{
			kruskalOutput += "(";
			kruskalOutput += kruskalOrder.get(i).pointA;
			kruskalOutput += ",";
			kruskalOutput += kruskalOrder.get(i).pointB;
			kruskalOutput += ")";
		}
		System.out.println(kruskalOutput + "}");
		
		
		
		
		
		
		
		
		
		fileS.close();
		s.close();
	}


}

class CycleCheck
{
	
	public CycleCheck()
	{
		
	}
	
	public boolean isCycled(ArrayList<String> reached,ArrayList<Node2> adjacencyMap, Edge e, ArrayList<Edge> eList)
	{
		Stack<String> s = new Stack<>();
		String currentNode;
		s.push(reached.get(0));
		int i = 1;
		while(s.size() > 0)
		{
			currentNode = s.pop();
		}
		return false;
	}
}
