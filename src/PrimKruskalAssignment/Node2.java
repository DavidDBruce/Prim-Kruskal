package PrimKruskalAssignment;

import java.util.ArrayList;

import BruceNodesLinks.Link;

public class Node2 {
	ArrayList<Link2> links = new ArrayList<Link2>();
	String name = "";
	
	
	Node2()
	{
		
	}
	
	public void addLink(String dest,int weight)
	{
		links.add(new Link2(dest,weight));
	}
	
	public Link2 getLink(String dest)
	{
		Link2 r = new Link2();
		for(int i = 0; i < links.size(); i++)
		{
			if(dest.equals(links.get(i).dest))
			{
				r = links.get(i);
			}
		}
		
		
		return r;
	}

}
