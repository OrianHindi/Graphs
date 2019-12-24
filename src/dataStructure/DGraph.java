package dataStructure;

import algorithms.Graph_Algo;
import utils.Point3D;

import java.io.Serializable;
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

		DGraph g = new DGraph();
		Point3D p0=new Point3D(-50,50);
		Point3D p1=new Point3D(0,50);
		Point3D p2=new Point3D(0,20);
		Point3D p3=new Point3D(-50,15);
		Point3D p4=new Point3D(15,50);
		Point3D p5=new Point3D(10,40);
		Point3D p6=new Point3D(5,30);
		Point3D p7=new Point3D(0,10);
		Point3D p8=new Point3D(-10,2);
		Point3D p9=new Point3D(-50,-1);
		Point3D p10=new Point3D(60,-10);

		Node a0=new Node(1,0,0,p10,"a");
		Node a=new Node(2,0, 0,p0, "b");
		Node b=new Node(3,0, 0,p1,"a");
		Node c=new Node(4, 0,0,p2, "a");
		Node d=new Node(5,0, 0,p3, "a");
		Node e=new Node(6, 0,0,p4, "a");
		Node f=new Node(7, 0,0,p5, "a" );
		Node j=new Node(8, 0,0,p6, "a");
		Node h=new Node(9, 0,0,p7, "a");
		Node i=new Node(10, 0,0,p8, "a");
		Node k=new Node(11, 0,0,p9,"a");

		g.addNode(a0);
		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		g.addNode(d);
		g.addNode(e);
		g.addNode(f);
		g.addNode(j);
		g.addNode(h);
		g.addNode(i);
		g.addNode(k);


		 g.connect(a0.getKey(),a.getKey(),1);
		 g.connect(a0.getKey(),b.getKey(), 2);
		 g.connect(a0.getKey(),c.getKey(),3);
		 g.connect(a.getKey(),d.getKey(), 11);
		 g.connect(a.getKey(),e.getKey(),12);
		 g.connect(b.getKey(),f.getKey(), 0);
		 g.connect(b.getKey(),j.getKey(),1);
		 g.connect(c.getKey(),h.getKey(), 4);
		 g.connect(c.getKey(), i.getKey(), 5);
		 g.connect(d.getKey(), k.getKey(), 14);
		 g.connect(e.getKey(),k.getKey(), 0);
		 g.connect(f.getKey(),k.getKey(), 2);
		 g.connect(j.getKey(),k.getKey(), 15);
		 g.connect(h.getKey(),k.getKey(), 20);
		 g.connect(i.getKey(),k.getKey(), 35);
		 Graph_Algo ga = new Graph_Algo(g);

		System.out.println("shortest path is: "+ga.shortestPathDist(1, 11));
	//	gg.drawDGraph();
//		Point3D x = new Point3D(14,4,0);
//		Point3D x2 = new Point3D(-75,14,0);
//		Point3D x3 = new Point3D(80,5,0);
//		Point3D x4 = new Point3D(1,4,0);
//		Point3D x5 = new Point3D(-5,1,0);
//		Point3D x6 = new Point3D(8,3,0);
//		Point3D x7 = new Point3D(4,1,0);
//		Point3D x8 = new Point3D(75,14,0);
//		node_data a1 = new Node(1,2,0,x,"asf");
//		node_data a2 = new Node(2,4,0,x2,"ads");
//		node_data a3 = new Node(3,50,0,x3,"sf");
//		node_data a4 = new Node(4,2,0,x4,"asf");
//		node_data a5 = new Node(5,4,0,x5,"ads");
//		node_data a6 = new Node(6,50,0,x6,"sf");
//		node_data a7 = new Node(7,2,0,x7,"asf");
//		node_data a8 = new Node(8,4,0,x8,"ads");
//		DGraph d = new DGraph();
//		d.addNode(a1);
//		d.addNode(a2);
//		d.addNode(a3);
//		d.addNode(a4);
//		d.addNode(a5);
//		d.addNode(a6);
//		d.addNode(a7);
//		d.addNode(a8);
//		d.connect(a1.getKey(),a4.getKey(),1);
//		d.connect(a1.getKey(),a2.getKey(),4);
//		d.connect(a4.getKey(),a2.getKey(),1);
//		d.connect(a4.getKey(),a3.getKey(),3);
//		d.connect(a2.getKey(),a3.getKey(),2);
//		d.connect(a2.getKey(),a7.getKey(),3);
//		d.connect(a5.getKey(),a6.getKey(),2);
//		d.connect(a6.getKey(),a7.getKey(),3);
//		d.connect(a7.getKey(),a6.getKey(),3);
//		Graph_Algo p = new Graph_Algo(d);
//		ArrayList<node_data> ans = (ArrayList)p.shortestPath(1,7);
	//	System.out.println(p.shortestPathDist(1,3));










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
