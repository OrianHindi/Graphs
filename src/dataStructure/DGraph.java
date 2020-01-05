package dataStructure;

import algorithms.Graph_Algo;
import gui.Graph_GUI;
import utils.Point3D;

import java.awt.*;
import java.io.Serializable;
import java.security.AlgorithmConstraints;
import java.util.*;
import java.util.List;

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

}
