package dataStructure;

import algorithms.Graph_Algo;
import gui.Graph_GUI;
import utils.Point3D;

import java.io.Serializable;
import java.security.AlgorithmConstraints;
import java.util.*;

/**
 * Fixes:
 * need to fix nullpoint if get edge.* or node.* throws nullpointexception.
 */


public class DGraph implements graph , Serializable {
	private HashMap<Integer,node_data> Nodemap= new HashMap<>();
	private HashMap<Integer,HashMap<Integer,edge_data>> Edgemap= new HashMap<>();
	private int edgeSize;
	private int nodeSize;
	private int MC;


	public DGraph(){
		this.Nodemap= new HashMap<>();
		this.Edgemap = new HashMap<>();
		this.edgeSize=0;
		this.nodeSize=0;
		this.MC=0;
	}


	public node_data getNode(int key) {
		try {
			return this.Nodemap.get(key);
		}
		catch (Exception e){

			throw new RuntimeException(" the Node isnt exist.");
		}
	}


	public edge_data getEdge(int src, int dest) {
		try {
			return this.Edgemap.get(src).get(dest);
		}
		catch(NullPointerException e){
			return null;
		}
	}


	public void addNode(node_data n) {
		this.Nodemap.put(n.getKey(),n);
		this.nodeSize++;
		this.MC++;
	}


	public void connect(int src, int dest, double w) {
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
		}
		else {
			System.err.println("Wrong input,Missing Nodes.");
			return;
		}
		this.edgeSize++;
		this.MC++;
	}


	public Collection<node_data> getV() {
		return this.Nodemap.values();
	}


	public Collection<edge_data> getE(int node_id) {
		try {
			return this.Edgemap.get(node_id).values();
		}
		catch (NullPointerException e){
			return null;
		}
	}


	public node_data removeNode(int key) {
		node_data removed = this.getNode(key);
		if(removed!=null){
			this.Nodemap.remove(key);
			this.nodeSize--;
			if(this.Edgemap.get(key)!=null)this.Edgemap.remove(key);
			Iterator iterator = this.Edgemap.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry map =(Map.Entry)iterator.next();
				int key2=(int) map.getKey();
				if(this.Edgemap.get(key2).get(key)!=null){
					removeEdge(key2,key);
				}
			}
		}
		return removed;
	}


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


	public int nodeSize() {
		return this.nodeSize;
	}


	public int edgeSize() {
		return this.edgeSize;
	}


	public int getMC() {
		return this.MC;
	}

	public HashMap getHash(){ return this.Nodemap; }

	public HashMap getEdgeHash(){return this.Edgemap;}

	public static void main(String[] args) {
		Point3D x = new Point3D(14,4,0);
		Point3D x2 = new Point3D(-75,14,0);
		Point3D x3 = new Point3D(80,5,0);
		Point3D x4 = new Point3D(1,4,0);
		Point3D x5 = new Point3D(-5,1,0);
		Point3D x6 = new Point3D(8,3,0);
		Point3D x7 = new Point3D(4,1,0);
		Point3D x8 = new Point3D(75,14,0);
		node_data a1 = new Node(x);
		node_data a2 = new Node(x2);
		node_data a3 = new Node(x3);
		node_data a4 = new Node(x4);
		node_data a5 = new Node(x5);
		node_data a6 = new Node(x6);
		node_data a7 = new Node(x7);
		node_data a8 = new Node(x8);
		node_data a9 = new Node(new Point3D(40,70));
		DGraph d = new DGraph();
		d.addNode(a1);
		d.addNode(a2);
		d.addNode(a3);
		d.addNode(a4);
		d.addNode(a5);
		d.addNode(a6);
		d.addNode(a7);
		d.addNode(a8);
		d.addNode(a9);
		d.connect(a1.getKey(),a2.getKey(),5);
		d.connect(a1.getKey(),a5.getKey(),2);
		d.connect(a1.getKey(),a3.getKey(),6);
		d.connect(a3.getKey(),a4.getKey(),7);
		d.connect(a2.getKey(),a8.getKey(),8);
		d.connect(a2.getKey(),a7.getKey(),3);
		d.connect(a5.getKey(),a6.getKey(),2);
		d.connect(a6.getKey(),a7.getKey(),3);
		d.connect(a7.getKey(),a6.getKey(),3);
		Graph_Algo p = new Graph_Algo();
		p.init(d);
		System.out.println(p.shortestPathDist(a1.getKey(),a9.getKey()));
		Graph_GUI gp = new Graph_GUI();
		DGraph new1 = new DGraph();
		Node s1 = new Node(new Point3D(3,5));
		Node s2 = new Node(new Point3D(7,10));
		Node s3 = new Node(new Point3D(40,6));
		new1.addNode(s1);
		new1.addNode(s2);
		new1.addNode(s3);
		new1.connect(s1.getKey(),s2.getKey(),5);
		new1.connect(s2.getKey(),s3.getKey(),12);
		new1.connect(s3.getKey(),s1.getKey(),10);
		Graph_Algo p2 = new Graph_Algo();
		p2.init(new1);
		p2.save("p2Grap");












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
