package BruceNodesLinks;

import java.util.ArrayList;

public class Node {
	ArrayList<Link> Links = new ArrayList<Link>();
	int name = -1;
	int breadthDistance = -1;
	Node breadthParent = null;
	
	Node()
	{
		
	}
	
	public void addLink(int index,int weight)
	{
		Links.add(new Link(index,weight));
	}
	
	public Link getLink(int index)
	{
		return Links.get(index);
	}

}
