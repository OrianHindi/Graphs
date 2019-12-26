package algorithms;

import dataStructure.*;

import java.io.*;
import java.util.*;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @authors  Orian Hindi and Shani Hayik.
 *
 */
public class Graph_Algo implements graph_algorithms {

	private graph GA;


	public Graph_Algo() {
		this.GA = null;
	}

	public Graph_Algo(graph p) {
		this.GA = p;
	}

	@Override
	public void init(graph g) {
		this.GA = g;
	}

	@Override
	public void init(String file_name) {
		deserialize(file_name);

	}

	@Override
	public void save(String file_name) {
		serialize(file_name);
	}

	private void serialize(String file_name) {
		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(this.GA);

			out.close();
			file.close();

			System.out.println("Object has benn serialized");
		} catch (IOException e) {
			System.err.println("IOException is caught,Object didnt save.");
		}

	}

	private void deserialize(String file_name) {
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);

			this.GA = (graph) in.readObject();

			in.close();
			file.close();

			System.out.println("Object has been deserialized");
		} catch (IOException e) {
			System.err.println("IOException is caught,object didnt uploaded");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException is caught,object didnt uploaded");
		}
	}

	@Override
	public boolean isConnected() {
		graph copied = this.copy();
		System.out.println(copied.getE(2));
		tagZero(copied);
		Collection<node_data> s = copied.getV();
		Iterator it = s.iterator();
		node_data temp = (node_data) it.next();
		DFSUtil(temp);
		for (node_data node : s) {
			if (node.getTag() == 0) return false;
		}
		transPose(copied);
		tagZero(copied);
		Collection<node_data> s1 = copied.getV();
		Iterator it1 = s1.iterator();
		node_data temp1 = (node_data) it1.next();
		DFSUtil(temp1);
		for (node_data node1 : s1) {
			if (node1.getTag() == 0) return false;
		}

		return true;
	}

	private void tagZero(graph g) {
		Collection<node_data> temp = g.getV();
		for (node_data node : temp) {
			node.setTag(0);
		}
	}

	private void DFSUtil(node_data n) {
		n.setTag(1);
		Collection<edge_data> temp = this.GA.getE(n.getKey());
		if (temp != null) {
			for (edge_data edge : temp) {
				if (this.GA.getNode(edge.getDest()) != null && this.GA.getE(edge.getDest()) != null && this.GA.getNode(edge.getDest()).getTag() == 0) {
					DFSUtil(this.GA.getNode(edge.getDest()));
				}
			}
		}
	}

	private void transPose(graph g) {
		Iterator it = g.getV().iterator();
		while (it.hasNext()) {
			node_data temp = (node_data) it.next();
			Iterator it1 = g.getE(temp.getKey()).iterator();
			while (it1.hasNext()) {
				edge_data temp1 = (edge_data) it1.next();
				if (temp1 != null && temp1.getTag() == 0) {
					if (g.getEdge(temp1.getDest(), temp1.getSrc()) != null) {
						g.getEdge(temp1.getDest(), temp1.getSrc()).setTag(1);
						temp1.setTag(1);
					} else {
						g.connect(temp1.getDest(), temp1.getSrc(), temp.getWeight());
						g.getEdge(temp1.getDest(), temp1.getSrc()).setTag(1);
						g.removeEdge(temp1.getSrc(), temp1.getDest());
						it1 = g.getE(temp.getKey()).iterator();
					}
				}
			}
		}
	}

	public void setNodes() {
		Collection<node_data> temp = this.GA.getV();
		for (node_data node : temp) {
			node.setTag(0);
			node.setWeight(Integer.MAX_VALUE);
		}
	}


	@Override
	public double shortestPathDist(int src, int dest) {
		setNodes();
		node_data temp = this.GA.getNode(src);
		temp.setWeight(0);
		STPRec(temp, this.GA.getNode(dest));
		double ans = this.GA.getNode(dest).getWeight();


		return ans;
	}

	public void STPRec(node_data n, node_data dest) {
		if (n.getKey() == dest.getKey() || n.getTag() == 1) {
			return;
		}
		Collection<edge_data> temp = this.GA.getE(n.getKey());
		if (temp != null) {
			for (edge_data edge : temp) {
				if (this.GA.getNode(edge.getDest()).getWeight() > this.GA.getNode(edge.getSrc()).getWeight() + edge.getWeight()) {
					this.GA.getNode(edge.getDest()).setWeight(this.GA.getNode(edge.getSrc()).getWeight() + edge.getWeight());
					n.setTag(1);
					STPRec(this.GA.getNode(edge.getDest()), dest);
				}
			}
		}
	}


	@Override
	public List<node_data> shortestPath(int src, int dest) {
		ArrayList<node_data> ans = new ArrayList<>();
		this.shortestPathDist(src, dest);
		graph copied = this.copy();
		transPose(copied);
		node_data first = copied.getNode(dest);
		ans.add(first);
		double minWeight = Integer.MAX_VALUE;
		while (first.getKey() != src) {
			Collection<edge_data> temp = copied.getE(first.getKey());
			for (edge_data edge : temp) {
				if (copied.getNode(edge.getDest()).getWeight() < minWeight) {
					minWeight = copied.getNode(edge.getDest()).getWeight();
					first = copied.getNode(edge.getDest());
				}
			}
			ans.add(first);
		}
		return ans;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		int index=0;
		int tempIndex =0;
		int targetsIndex=0;
		boolean flag = true;
		List<node_data> ans = new LinkedList<>();
		List<node_data> temp = new LinkedList<>();
		while(!targets.isEmpty()){
			temp = shortestPath(targetsIndex,targetsIndex+1);
			for (int i = 0; i <temp.size() ; i++) {
				if(!ans.contains(temp.get(i)) && targets.contains(temp.get(i).getKey())){
					ans.add(temp.get(i));
					targets.remove(temp.get(i).getKey());
				}

			}

		}

		return ans;
	}

	@Override
	public graph copy() {
		graph copy = new DGraph();
		Collection<node_data> nColl = this.GA.getV();
		for (node_data node : nColl) {
			node_data temp = new Node((Node) node);
			copy.addNode(node);
		}
		Collection<node_data> nColl2 = this.GA.getV();
		for (node_data node1 : nColl2) {
			Collection<edge_data> eColl = this.GA.getE(node1.getKey());
			if (eColl != null) {
				for (edge_data edge : eColl) {
					copy.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
				}
			}
		}
		return copy;
	}


}

