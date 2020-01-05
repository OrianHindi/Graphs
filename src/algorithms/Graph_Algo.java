package algorithms;
import dataStructure.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @authors  Orian Hindi and Shani Hayik.
 *
 */
public class Graph_Algo implements graph_algorithms {
	private graph GA;

	//constructor
	public Graph_Algo() {
		this.GA = new DGraph();
	}
	public Graph_Algo(graph g){
		init(g);
	}

	/**
	 * Init this set of algorithms on the parameter - graph.
	 * @param g
	 */
	@Override
	public void init(graph g) {
		this.GA = g;
	}

	/**
	 * Init a graph from file
	 * @param file_name
	 */
	@Override
	public void init(String file_name) { deserialize(file_name); }

	/** Saves the graph to a file.
	 *
	 * @param file_name
	 */
	@Override
	public void save(String file_name)  {
		serialize(file_name);
	}

	/**
	 * Serialization is a mechanism of converting the state of an object into a byte stream.
	 * @param file_name
	 */
	private void serialize(String file_name) {
		//Saving of object in a file
		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			// Method for serialization of object
			out.writeObject(this.GA);

			out.close();
			file.close();

			System.out.println("Object has benn serialized");
		} catch (IOException e) {
			System.err.println("IOException is caught,Object didnt save.");
		}
	}

	/**Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory
	 *
	 * @param file_name
	 */
	private void deserialize(String file_name) {
		// Reading the object from a file
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);
			// Method for deserialization of object
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

	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node. NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
	 * @return
	 */
	@Override
	public boolean isConnected() {
		if(this.GA.nodeSize() ==1 || this.GA.nodeSize()==0) return true;
		graph copied = this.copy();
		tagZero(copied);
		Collection<node_data> s = copied.getV();
		Iterator it = s.iterator();
		node_data temp = (node_data) it.next();
		DFSUtil(copied,temp);
		for (node_data node : s) {
			if (node.getTag() == 0) return false;
		}
		transPose(copied);
		tagZero(copied);
	    Collection<node_data> s1 = copied.getV();
		Iterator it1 = s1.iterator();
		node_data temp1 = (node_data) it1.next();
		DFSUtil(copied,temp1);
		for (node_data node1 : s1) {
			if (node1.getTag() == 0) return false;
		}

		return true;
	}

	/**
	 * this function sets all nodes to zero
	 * @param g
	 */
	private void tagZero(graph g) {
		Collection<node_data> temp = g.getV();
		for (node_data node : temp) {
			node.setTag(0);
		}
	}
	/**
	 *  algorithm for traversing or searching tree or graph data structures. The algorithm starts
	 *  at the root node and explores as far as possible along each branch before backtracking.
	 * @param copy
	 * @param n
	 */
	private void DFSUtil(graph copy,node_data n) {
		n.setTag(1);
		Collection<edge_data> temp = copy.getE(n.getKey());
		if (temp != null) {
			for (edge_data edge : temp) {
				if (copy.getNode(edge.getDest()) != null && copy.getE(edge.getDest()) != null && copy.getNode(edge.getDest()).getTag() == 0) {
					DFSUtil(copy,copy.getNode(edge.getDest()));
				}
			}
		}
	}

	/**
	 * returns another directed graph on the same set of vertices with all
	 * of the edges reversed compared to the orientation of the corresponding edges in G
	 * @param g
	 */
	private void transPose(graph g) {
		Iterator it = g.getV().iterator();
		while (it.hasNext()) {
			node_data temp = (node_data) it.next();
			if(g.getE(temp.getKey())!=null) {
				Iterator it1 = g.getE(temp.getKey()).iterator();
				while (it1.hasNext()) {
					edge_data temp1 = (edge_data) it1.next();
					if (temp1 != null && temp1.getTag() == 0) {
						if (g.getEdge(temp1.getDest(), temp1.getSrc()) != null) {
							Edge temps = new Edge((Edge)g.getEdge(temp1.getSrc(),temp1.getDest()));
							double weight1 = g.getEdge(temp1.getSrc(),temp1.getDest()).getWeight();
							double weight2 = g.getEdge(temp1.getDest(),temp1.getSrc()).getWeight();
							g.connect(temp1.getSrc(),temp1.getDest(),weight2);
							g.connect(temps.getDest(),temps.getSrc(),weight1);
							g.getEdge(temps.getDest(), temps.getSrc()).setTag(1);
							g.getEdge(temps.getSrc(),temps.getDest()).setTag(1);
							it1 = g.getE(temp.getKey()).iterator();
						} else {
							g.connect(temp1.getDest(), temp1.getSrc(), temp1.getWeight());
							g.getEdge(temp1.getDest(), temp1.getSrc()).setTag(1);
							g.removeEdge(temp1.getSrc(), temp1.getDest());
							it1 = g.getE(temp.getKey()).iterator();
						}
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

	/**
	 * returns the length of the shortest path between src to dest
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		if(src== dest ) return 0;
		if(this.GA.getNode(src)==null || this.GA.getNode(dest)==null) throw new RuntimeException("Wrong input,Missing nodes.");
		setNodes();
		node_data temp = this.GA.getNode(src);
		temp.setWeight(0);
		STPRec(temp, this.GA.getNode(dest));
		double ans = this.GA.getNode(dest).getWeight();
		return ans;
	}

	private void STPRec(node_data n, node_data dest) {
		if (n.getKey() == dest.getKey() && n.getTag() == 1) {
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

	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> ans = new ArrayList<>();
		this.shortestPathDist(src, dest);
		if(this.GA.getNode(src).getWeight() == Integer.MAX_VALUE || this.GA.getNode(dest).getWeight() == Integer.MAX_VALUE){
			System.out.print("There is not a path between the nodes.");
			return null;
		}
		graph copied = this.copy();
		transPose(copied);
		node_data first = copied.getNode(dest);
		ans.add(first);
		while (first.getKey() != src) {
			Collection<edge_data> temp = copied.getE(first.getKey());
			double check= first.getWeight();
			if(temp!=null) {
				for (edge_data edge : temp) {
					if (copied.getNode(edge.getDest()).getWeight() + edge.getWeight() == check) {
						first = copied.getNode(edge.getDest());
					}
				}
			}
			ans.add(first);
		}
		List<node_data> ans2 = new ArrayList<>();
		for (int i = ans.size()-1;i>=0;i--){
			ans2.add(ans.get(i));
		}
		return ans2;
	}

	/**
	 * computes a relatively short path which visit each node in the targets List.
	 * Note: this is NOT the classical traveling salesman problem,
	 * as you can visit a node more than once, and there is no need to return to source node -
	 * just a simple path going over all nodes in the list.
	 * @param targets
	 * @return
	 */
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<Integer> targets1 = new ArrayList<>();
		List<node_data> ans = new ArrayList<>();
		for (Integer target: targets) {
			if(!targets1.contains(target)){
				targets1.add(target);
			}
		}
		if(targets1.size()==1){
			ans.add(this.GA.getNode(targets.get(0)));
			return ans;
		}
		int help=targets1.get(0);
		int targetSize = targets1.size();
		while(!targets1.isEmpty()) {
			double minWeight = Integer.MAX_VALUE;
			int geti=-1;
			for (int j = 0; j <targetSize ; j++) {
				if(shortestPathDist(help,targets1.get(j)) !=0 &&shortestPathDist(help,targets1.get(j))<minWeight || targets1.size()==1 && shortestPathDist(help,targets1.get(j)) ==0) {
					minWeight = shortestPathDist(help, targets1.get(j));
					geti = targets1.get(j);
				}
			}
			List<node_data> ans2 = new ArrayList<>();
			if(geti==-1) return null;
			if(targets1.size()>1) {
				ans2 = shortestPath(help, geti);
				ans.addAll(ans2);
				targets1.remove((Integer) help);
				targetSize--;
			}
			else{
				targets1.remove((Integer)geti);
			}
			help=geti;
		}
		for (int ii = 0; ii <ans.size()-1 ; ii++) {
			if(ans.get(ii)==ans.get(ii+1)){
				ans.remove(ii);
			}
		}
		return ans;
	}

	/**
	 * Compute a deep copy of this graph.
	 * @return
	 */
	@Override
	public graph copy() {
		graph copy = new DGraph();
		Collection<node_data> nColl = this.GA.getV();
		for (node_data node : nColl) {
			node_data temp = new Node((Node) node );
			copy.addNode(node);
		}
		Collection<node_data> nColl2 = this.GA.getV();
		for (node_data node1 : nColl2) {
			Collection<edge_data> eColl = this.GA.getE(node1.getKey());
			if (eColl!=null) {
				for (edge_data edge : eColl) {
					copy.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
				}
			}
		}
		return copy;
	}
}

