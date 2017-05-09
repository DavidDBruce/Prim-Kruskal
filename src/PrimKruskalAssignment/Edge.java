package PrimKruskalAssignment;

public class Edge implements Comparable {
	
	String pointA = "";
	String pointB = "";
	int weight;
	Edge()
	{
		
	}
	
	public Edge(String pointA, String pointB, int weight)
	{
		this.pointA = pointA;
		this.pointB = pointB;
		this.weight = weight;
	}
	
	public Edge getLink()
	{
		return this;
	}

	@Override
	public int compareTo(Object arg0) {
		Edge e = (Edge) arg0;
		return this.weight - e.weight;
	}

}
