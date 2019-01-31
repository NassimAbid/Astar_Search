package AIcoursework;

class Node {

	public final String value;
	public double g_scores;
	public double h_scores;
	public double f_scores = 9999;
	public Edge[] adjacencies;
	public Node parent;
	public int sPacks, mPacks, lPacks;

	public Node(String val) {
		value = val;
	}

	public String toString() {
		return value;
	}
	// Returns number of Packages

	int getmPacks() {
		return this.mPacks;
	}


	int getsPack() {
		return this.sPacks;
	}

	int getlPack() {
		return this.lPacks;
	}

	public void setH(double hVal) {
		this.h_scores = hVal;
	}



	// Takes a Packages from node

	void takelPack() {
		this.lPacks--;
	}

	void takemPack() {
		this.mPacks--;
	}

	void takePack() {
		this.sPacks--;
	}


	//put large packages in the node
	void putlPack() {
		this.lPacks++;
	}
	//put medium packages in the node
	void putmPack() {
		this.mPacks++;
	}
	//put small packages in the node
	void putsPack() {
		this.sPacks++;
	}

	public void setPacks(int Small, int Medium, int Large) {
		this.lPacks = Large;
		this.mPacks = Medium;
		this.sPacks = Small;
		
		
	}

}