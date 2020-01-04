package dataStructure;

import algorithms.Graph_Algo;
import gui.Graph_GUI;
import utils.Point3D;

import java.awt.*;
import java.io.Serializable;
import java.security.AlgorithmConstraints;
import java.util.*;

/**
 * Fixes:
 * need to fix nullpoint if get edge.* or node.* throws nullpointexception.
 * @authors Shani hayik,Orian hindi,Omri yonatani.
 */


public class DGraph implements graph , Serializable {
	private HashMap<Integer,node_data> Nodemap= new HashMap<>();
	private HashMap<Integer,HashMap<Integer,edge_data>> Edgemap= new HashMap<>();
	private int edgeSize;
	private int nodeSize;
	private int MC;

	/**
	 * Constructor for DGraph.
	 */
	public DGraph(){
		this.Nodemap= new HashMap<>();
		this.Edgemap = new HashMap<>();
		this.edgeSize=0;
		this.nodeSize=0;
		this.MC=0;
	}

	/**
	 * This function return Node by his key.
	 * @param key - the node_id
	 * @return the Node that was requested.
	 */
	public node_data getNode(int key) {
		try {
			return this.Nodemap.get(key);
		}
		catch (Exception e){
			throw new RuntimeException(" The Node isnt exist.");
		}
	}

	/**
	 * This function return Edge that have benn requested.
	 * @param src The Source Node id.(From which node that Edge is going out).
	 * @param dest The Dest Node id .(To which node that Edge is going in).
	 * @return Edge that have been requested, if not exist return null.
	 */
	public edge_data getEdge(int src, int dest) {
		try {
			return this.Edgemap.get(src).get(dest); // if null point exception
		}
		catch(NullPointerException e){ //return null.
			return null;
		}
	}

	/**
	 * This function add Node to a Graph.
	 * @param n The node that we want add to the graph.
	 */
	public void addNode(node_data n) {
		this.Nodemap.put(n.getKey(),n);
		this.nodeSize++;
		this.MC++;
	}

	/**
	 * This function connect an Edge between 2 Nodes.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 */
	public void connect(int src, int dest, double w) {
		if(w<0) throw new RuntimeException("Weight of Edge cant be negative");
		edge_data temp = new Edge(src,dest,w);
		if(this.getNode(src)!=null && this.getNode(dest)!=null) {  // the nodes isnt exist.
			if (this.getEdge(src, dest) == null) {  // the edge isnt exist.
				if(this.Edgemap.get(src) == null) {   // the Hashmap of neighburs isnt exist
					HashMap<Integer, edge_data> add = new HashMap<>();
					this.Edgemap.put(src,add);
					this.Edgemap.get(src).put(dest,temp);
				}
				else {         //hash map of neighburs exist but the edge isnt.
					this.Edgemap.get(src).put(dest, temp);
				}
			}
			else{
				this.removeEdge(src,dest);
				this.connect(src,dest,w);
			}
		}
		else {
			throw new RuntimeException("Wrong input, Missing node.");
		}
		this.edgeSize++;
		this.MC++;
	}

	/**
	 * This function return the collection of the Nodes.
	 * @return The collection of nodes.
	 */
	public Collection<node_data> getV() {
		return this.Nodemap.values();
	}

	/**
	 *  This function return the Edge collection by node id.
	 * @param node_id represent what Node collection we want.
	 * @return The collection we mentioned above.
	 */
	public Collection<edge_data> getE(int node_id) {
		try {
			return this.Edgemap.get(node_id).values();
		}
		catch (NullPointerException e){
			return null;
		}
	}

	/**
	 * This function remove a Node from the graph.
	 * @param key the Node id we want to remove.
	 * @return the Node we removed or null if the Node isnt exist.
	 */
	public node_data removeNode(int key) {
		node_data removed = this.getNode(key);
		if(removed!=null){       //remove the node from the node hash.
			this.Nodemap.remove(key);
			this.nodeSize--;
			if(this.Edgemap.get(key)!=null)this.Edgemap.remove(key); // remove all the edge that key is src of them.
			Iterator iterator = this.Edgemap.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry map =(Map.Entry)iterator.next();
				int key2=(int) map.getKey();
				if(this.Edgemap.get(key2).get(key)!=null){
					removeEdge(key2,key);    //remove all the edges that the key is the dest of them.
				}
			}
		}
		return removed;
	}


	/**
	 * This function remove an Edge from the graph.
	 * @param src  the Node id that is the Edge source.
	 * @param dest  the Node id that is the Edge Destenation.
	 * @return The Edge that have benn removed or null if not exist.
	 */
	public edge_data removeEdge(int src, int dest) {
		edge_data temp =this.getEdge(src,dest);
		if(temp!=null) {
			this.Edgemap.get(src).remove(dest);
			this.edgeSize--;
			this.MC++;
			return temp ;
		}
		System.err.println("Edge isnt exist, for src:" + src + ",  dest:" + dest);
		return temp;
	}

	/**
	 * @return the size of Nodes in this graph.
	 */
	public int nodeSize() {
		return this.nodeSize;
	}

	/**
	 * @return the size of Edges in this graph.
	 */
	public int edgeSize() {
		return this.edgeSize;
	}

	/**
	 * @return the number of Changes happend in the graph.
	 */
	public int getMC() {
		return this.MC;
	}

	public static void main(String[] args) {
		Graph_Algo G = new Graph_Algo();
		Point3D p00 = new Point3D(1, 6, 0);
		Point3D p11 = new Point3D(0, 2, 3);
		Point3D p22 = new Point3D(1, 4, 0);
		Point3D p33 = new Point3D(5, 2, 0);
		Point3D p44 = new Point3D(6,5, 0);
		Point3D p55 = new Point3D(4,6, 0);
		Point3D p66 = new Point3D(3,5, 0);
		Point3D p77 = new Point3D(4,10,0);
		Point3D p88 = new Point3D(4.10,0);
		Point3D p99 = new Point3D(1,30);
		Point3D p10 = new Point3D(10,40);
		node_data node1 = new Node(p00);
		node_data node2 = new Node(p11);
		node_data node3 = new Node(p22);
		node_data node4 = new Node(p33);
		node_data node5 = new Node(p44);
		node_data node6 = new Node(p55);
		node_data node7 = new Node(p66);
		node_data node8 = new Node(p77);
		node_data node9 =new Node(p88);
		node_data node10 = new Node(p99);
		node_data node11 = new Node(p10);

		DGraph Dg = new DGraph();
		Dg.addNode(node1);
		Dg.addNode(node2);
		Dg.addNode(node3);
		Dg.addNode(node4);
		Dg.addNode(node5);
		Dg.addNode(node6);
		Dg.addNode(node7);
		Dg.addNode(node8);
		Dg.addNode(node9);
		Dg.addNode(node10);
		Dg.addNode(node11);


		Dg.connect(node1.getKey(), node2.getKey(), 5);
		Dg.connect(node1.getKey(), node3.getKey(), 3);
		Dg.connect(node1.getKey(), node4.getKey(), 2);
		Dg.connect(node2.getKey(), node5.getKey(), 2);
		Dg.connect(node3.getKey(), node6.getKey(), 4);
		Dg.connect(node3.getKey(),node1.getKey(),2);
		Dg.connect(node4.getKey(), node6.getKey(), 4);
		Dg.connect(node4.getKey(), node7.getKey(), 2);
		Dg.connect(node5.getKey(), node8.getKey(), 6);
		Dg.connect(node5.getKey(), node7.getKey(), 1);
		Dg.connect(node5.getKey(),node2.getKey(),4);
		Dg.connect(node6.getKey(),node11.getKey(),3);
		Dg.connect(node7.getKey(),node8.getKey(),4);
		Dg.connect(node7.getKey(),node6.getKey(),1);
		Dg.connect(node7.getKey(),node11.getKey(),9);
		Dg.connect(node8.getKey(),node7.getKey(),1);
		Dg.connect(node8.getKey(),node9.getKey(),9);
		Dg.connect(node9.getKey(),node8.getKey(),3);
		Dg.connect(node9.getKey(),node10.getKey(),5);
		Dg.connect(node10.getKey(),node9.getKey(),2);
		Dg.connect(node10.getKey(),node11.getKey(),1);
		Dg.connect(node11.getKey(),node10.getKey(),2);

		G.init(Dg);
		System.out.println("Distance betwenn 1-6 is :" + G.shortestPathDist(node1.getKey(),node6.getKey()));
		System.out.println("Distance between 6-7 is : " + G.shortestPathDist(node6.getKey(),node7.getKey()));
		boolean temp = G.isConnected();
		System.out.println("The graph is Connected :" + temp);
		System.out.println("The shortest path between 5-10 is :" + G.shortestPath(node5.getKey(),node10.getKey()));
	//	List<node_data> qqqq =  p.TSP(r);
//		System.out.println(p.isConnected());
//		System.out.println("r");
//		Point3D x = new Point3D(14,4,0);
//		Point3D x2 = new Point3D(-75,14,0);
//		Point3D x3 = new Point3D(80,5,0);
//		Point3D x4 = new Point3D(1,4,0);
//		Point3D x5 = new Point3D(-5,1,0);
//		Point3D x6 = new Point3D(8,3,0);
//		Point3D x7 = new Point3D(4,1,0);
//		Point3D x8 = new Point3D(75,14,0);
//		node_data a1 = new Node(x);
//		node_data a2 = new Node(x2);
//		node_data a3 = new Node(x3);
//		node_data a4 = new Node(x4);
//		node_data a5 = new Node(x5);
//		node_data a6 = new Node(x6);
//		node_data a7 = new Node(x7);
//		node_data a8 = new Node(x8);
//		DGraph d = new DGraph();
//		d.addNode(a1);
//		d.addNode(a2);
//		d.addNode(a3);
//		d.addNode(a4);
//		d.addNode(a5);
//		d.addNode(a6);
//		d.addNode(a7);
//		d.addNode(a8);
//		d.connect(a1.getKey(),a2.getKey(),5);
//		d.connect(a1.getKey(),a5.getKey(),2);
//		d.connect(a1.getKey(),a3.getKey(),6);
//		d.connect(a1.getKey(),a6.getKey(),5);
//		d.connect(a3.getKey(),a4.getKey(),7);
//		d.connect(a2.getKey(),a8.getKey(),8);
//		d.connect(a2.getKey(),a7.getKey(),3);
//		d.connect(a5.getKey(),a1.getKey(),5);
//		d.connect(a5.getKey(),a6.getKey(),2);
//		d.connect(a6.getKey(),a1.getKey(),3);
//		d.connect(a6.getKey(),a5.getKey(),3);
//		d.connect(a6.getKey(),a7.getKey(),3);
//		d.connect(a7.getKey(),a6.getKey(),3);
//		Node ps= (Node)d.getNode(10);
//		System.out.println(ps);
//		edge_data ps1 = d.getEdge(6,12);
//		Graph_Algo p = new Graph_Algo();
//		p.init(d);
//		List<Integer> r = new LinkedList<>();
//		r.add(a1.getKey());
//		r.add(a6.getKey());
//		r.add(a5.getKey());
//		List<node_data> ans = p.TSP(r);
//		//System.out.println(d.getNode(7).getWeight());
//		System.out.println(p.shortestPathDist(a1.getKey(),a6.getKey()));
//		System.out.println(a6.getInfo());
//		List<node_data> ans1 = p.shortestPath(a1.getKey(),a6.getKey());
//		List<node_data> ans2 = p.shortestPath(a5.getKey(),a6.getKey());
//		System.out.println("tsp" + ans);
//
//
//		System.out.println(ans2);
//
//		System.out.println(ans);







//		Point3D x = new Point3D(14,4,0);
//		Point3D x2 = new Point3D(-75,14,0);
//		Point3D x3 = new Point3D(80,5,0);
//		Point3D x4 = new Point3D(1,4,0);
//		Point3D x5 = new Point3D(-5,1,0);
//		Point3D x6 = new Point3D(8,3,0);
//		Point3D x7 = new Point3D(4,1,0);
//		Point3D x8 = new Point3D(75,14,0);
//		node_data a1 = new Node(x);
//		node_data a2 = new Node(x2);
//		node_data a3 = new Node(x3);
//		node_data a4 = new Node(x4);
//		node_data a5 = new Node(x5);
//		node_data a6 = new Node(x6);
//		node_data a7 = new Node(x7);
//		node_data a8 = new Node(x8);
//		node_data a9 = new Node(new Point3D(40,70));
//		DGraph d = new DGraph();
//		d.addNode(a1);
//		d.addNode(a2);
//		d.addNode(a3);
//		d.addNode(a4);
//		d.addNode(a5);
//		d.addNode(a6);
//		d.addNode(a7);
//		d.addNode(a8);
//		d.addNode(a9);
//		d.connect(a1.getKey(),a2.getKey(),5);
//		d.connect(a1.getKey(),a5.getKey(),2);
//		d.connect(a1.getKey(),a3.getKey(),6);
//		d.connect(a3.getKey(),a4.getKey(),7);
//		d.connect(a2.getKey(),a8.getKey(),8);
//		d.connect(a2.getKey(),a7.getKey(),3);
//		d.connect(a5.getKey(),a6.getKey(),2);
//		d.connect(a6.getKey(),a7.getKey(),3);
//		d.connect(a7.getKey(),a6.getKey(),3);
//		Graph_Algo p = new Graph_Algo();
//		p.init(d);
//		System.out.println(p.shortestPathDist(a8.getKey(),a2.getKey()));
//		Graph_GUI gp = new Graph_GUI();
//		DGraph new1 = new DGraph();
//		Node s1 = new Node(new Point3D(3,5));
//		Node s2 = new Node(new Point3D(7,10));
//		Node s3 = new Node(new Point3D(40,6));
//		new1.addNode(s1);
//		new1.addNode(s2);
//		new1.addNode(s3);
//		new1.connect(s1.getKey(),s2.getKey(),5);
//		new1.connect(s2.getKey(),s3.getKey(),12);
//		new1.connect(s3.getKey(),s1.getKey(),10);
//		Graph_Algo p2 = new Graph_Algo();
//		p2.init(new1);













//		System.out.println(d.Edgemap.toString());
//		Graph_Algo p = new Graph_Algo(d);
//		p.save("tempGraph.txt");
//		System.out.println(d.Edgemap.toString());
//		Graph_Algo readFile = new Graph_Algo();
//		readFile.init("tempGraph.txt");
//		System.out.println(" after read from file:  ");
//		System.out.println(readFile.isConnected());
//		DGraph s = (DGraph)readFile.copy();
//		System.out.println(s.Edgemap.toString());

	}

}
