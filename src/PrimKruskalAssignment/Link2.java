package PrimKruskalAssignment;

public class Link2 {
	
	String dest = "";
	int weight;
	Link2()
	{
		
	}
	
	public Link2(String dest, int weight)
	{
		this.dest = dest;
		this.weight = weight;
	}
	
	public Link2 getLink()
	{
		return this;
	}

}
