package AIcoursework;

class Edge {
	public double Cost;
	public final Node Target;

	public void updateCost(double newCost) {
		this.Cost = newCost;
	}

	public Edge(Node TargetNode, double CostVal) {
		Target = TargetNode;
		Cost = CostVal;
	}

}
