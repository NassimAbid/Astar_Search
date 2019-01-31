package AIcoursework;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class AstarSearch {

	// initialise the graph of traversable nodes.
	static Node Truck = new Node("Truck");
	static Node wA = new Node("Warehouse A");
	static Node wB = new Node("Warehouse B");
	static int TotalMoves = 0;
	int i = 0;
	static List<Node> Movement = new ArrayList<Node>();

	// h scores is the straight-line distance from each entity
	public static void main(String[] args) {

		// Initialise Boxes
		Truck.setPacks(12, 0, 20);
		wA.setPacks(0, 37, 0);
		wB.setPacks(0, 15, 0);

		// Analyse Heuristics
		Truck.setH((wA.getmPacks() + wB.getmPacks()) * 2); // Number of trips to complete trucks goal
		wA.setH(Truck.getsPack() * 2); // Number of trips to complete wA goal
		wB.setH(Truck.getlPack() * 2); // Number of trips to complete wB goal

		// initialise the edges
		// Truck and all its adjacent nodes
		Truck.adjacencies = new Edge[] { new Edge(wA, GetCost(Truck, wA)), new Edge(wB, GetCost(Truck, wB)) };

		// Warehouse A and all its adjacent nodes
		wA.adjacencies = new Edge[] { new Edge(Truck, GetCost(wA, Truck)), };

		// Warehouse B and all its adjacent nodes
		wB.adjacencies = new Edge[] { new Edge(Truck, GetCost(wB, Truck)), };
		// starts the A* search from the start node(truck)
		AstarSearch(Truck);

		// this method prints off the Movement of the robot with the number of moves
		System.out.print("Movement: ");
		for (int i = 0; i < TotalMoves; i++) {
			int t = i + 1;
			System.out.println(" " + Movement.get(i) + " -> " + Movement.get(t) + ", Move: " + t + " || ");

		}

		System.out.print("Movement: ");
		for (int i = 0; i <= TotalMoves; i++) {
			int t = i + 1;
			System.out.print(" " + Movement.get(i) + " -> ");

		}
		System.out.println("\n total moves:" + " " + TotalMoves);
	}

	/**
	 * analyses the Cost of where to go next, this is done by taking the source NODE
	 * and looking at all the children and calculating which is the better
	 * destination.
	 */
	public static int GetCost(Node Source, Node Destination) {
		int Cost = 3;
		if (Destination.toString().equals("Warehouse A")) {
			if (Source.getsPack() > 0)
				Cost--;
			if (Destination.getmPacks() > 0)
				Cost--;
		} else if (Destination.toString().equals("Warehouse B")) {
			if (Source.getlPack() > 0)
				Cost--;
			if (Destination.getmPacks() > 0)
				Cost--;
		}
		return Cost;
	}

	/**
	 * This method does the movement of the packages when the robot moves to
	 * different locations Picking up and dropping packages
	 */
	public static void Movement(Node Source, Node Destination) {
		if (Destination.toString().equals("Truck") && Source.getmPacks() > 0) {
			Source.takemPack();
			Destination.putmPack();
		} else if (Destination.toString().equals("Warehouse A") && Source.getsPack() > 0) {
			Source.takePack();
			Destination.putsPack();
		} else if (Destination.toString().equals("Warehouse B") && Source.getlPack() > 0) {
			Source.takelPack();
			Destination.putlPack();
		}
	}

	// this is the method for the AstarSearch.
	public static void AstarSearch(Node source) {

		Movement.add(source);
		// priority queue for the nodes
		PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new Comparator<Node>() {
			// override compare method
			public int compare(Node i, Node j) {
				if (i.f_scores > j.f_scores) {
					return 1;
				}

				else if (i.f_scores < j.f_scores) {
					return -1;
				}

				else {
					return 0;
				}
			}

		});
		// g_score is the TotalMoves accumulative Cost
		// Cost from start
		source.g_scores = 0;

		queue.add(source);

		boolean complete = false;

		while ((!queue.isEmpty()) && (!complete)) {

			// the node in having the lowest f_score value is the poll function.
			Node current = queue.poll();

			Node bestNode = new Node("Best");
			double temp_g_scores = 0.0;
			double temp_f_scores = 0.0;
			// check every child of current node
			for (Edge e : current.adjacencies) {
				Node child = e.Target;
				double Cost = e.Cost;
				if (Cost == 3 && !child.toString().equals("Truck"))
					continue;

				temp_g_scores = current.g_scores + Cost; // Current value
				/*
				 * TotalMoves accumulative Cost to get to current + Cost to child TotalMoves
				 * heuristic Cost till child's goal completes TotalMoves accumulative Cost to
				 * get to child so far
				 */
				temp_f_scores = temp_g_scores + child.h_scores + child.g_scores;

				// if newer f_score is higher then skip 
				child.f_scores = temp_f_scores;
				if (bestNode.f_scores > child.f_scores) {
					bestNode = child;
					// bestNode.parent = current;
					bestNode.f_scores = temp_f_scores;
				} else
					continue;

			}
			// Required here to stop accumulated
			// Cost added to unvisited node
			bestNode.g_scores = temp_g_scores;

			if (queue.contains(bestNode)) {
				queue.remove(bestNode);
			}
			//Taking package with it
			Movement(current, bestNode); 
			
			Movement.add(bestNode);
			TotalMoves++;

			queue.add(bestNode);

			for (Edge x : bestNode.adjacencies) {
				x.updateCost(GetCost(bestNode, x.Target));
			}
			// The Heuristics for all places.
			Truck.setH((wA.getmPacks() + wB.getmPacks()) * 2); // Number of trips to complete trucks goal
			wA.setH(Truck.getsPack() * 2); // Number of trips to complete Warehouse A goal
			wB.setH(Truck.getlPack() * 2); // Number of trips to complete Warehouse B goal

			// the goal for the program to be finished
			if (Truck.getsPack() + Truck.getlPack() + wA.getmPacks() + wB.getmPacks() == 0) {
				complete = true;
			}
		}
	}
}
