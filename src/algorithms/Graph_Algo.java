package algorithms;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @authors  Orian Hindi and Shani Hayik.
 *
 */
public class Graph_Algo implements graph_algorithms{

	private graph GA;


	public Graph_Algo(){
		this.GA=null;
	}
	public Graph_Algo(graph p){
		this.GA=p;
	}
	@Override
	public void init(graph g) {
		this.GA= g;
	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		this.tagZero();
		Collection<node_data> s = this.GA.getV();
		Iterator it =s.iterator();
		node_data temp = (node_data)it.next();
		DFSUtil(temp);
		for (node_data node : s) {
			if(node.getTag()==0)return false;
		}
		while (it.hasNext()){
			node_data end =(node_data) it.next();
		}
		transPose(this.GA);
		tagZero();
		Collection<node_data> s1 =this.GA.getV();
		Iterator it1 =s1.iterator();
		node_data temp1 = (node_data)it1.next();
		DFSUtil(temp1);
		for (node_data node1: s1) {
			if(node1.getTag()==0) return false;
		}
		while(it1.hasNext()){
			node_data end2=(node_data)it1.next();
		}
		transPose(this.GA);

		return true;
	}
	private void tagZero(){
		Collection<node_data>  temp = this.GA.getV();
		for(node_data node : temp){
			node.setTag(0);
		}
	}
	private void DFSUtil(node_data n){
		n.setTag(1);
		Collection<edge_data> temp =this.GA.getE(n.getKey());
		if(temp!=null) {
			for (edge_data edge : temp) {
				if (this.GA.getNode(edge.getDest()) != null && this.GA.getE(edge.getDest()) != null && this.GA.getNode(edge.getDest()).getTag() == 0) {
					DFSUtil(this.GA.getNode(edge.getDest()));
				}
			}
		}
	}
	   private void transPose(graph g){
		Iterator it = g.getV().iterator();
		while(it.hasNext()){
			node_data temp = (node_data)it.next();
			Iterator it1 =g.getE(temp.getKey()).iterator();
			while(it1.hasNext()){
				edge_data temp1 =(edge_data)it1.next();
				if(temp1 !=null &&temp1.getTag()==0){
					g.connect(temp1.getDest(),temp1.getSrc(),temp.getWeight());
					g.getEdge(temp1.getDest(),temp1.getSrc()).setTag(1);
					g.removeEdge(temp1.getSrc(),temp1.getDest());
					it1 =g.getE(temp.getKey()).iterator();
				}
				}
			}
		}







//
//		Collection<node_data> nColl = g.getV();
//		for (node_data node:nColl) {
//			Collection<edge_data> eColl = g.getE(node.getKey());
//			if(eColl!=null) {
//				for (edge_data edge : eColl) {
//					if (g.getE(edge.getSrc()) != null && g.getEdge(edge.getSrc(), edge.getDest()).getTag() == 0) {
//						g.connect(edge.getDest(), edge.getSrc(), edge.getWeight());
//						g.getEdge(edge.getDest(), edge.getSrc()).setTag(1);
//						g.removeEdge(edge.getSrc(), edge.getDest());
//					}
//				}
//			}
//		}



	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		graph copy = new DGraph();
		return null;
	}

}
//	Collection<node_data> vertex = this.GA.getV();
//	tagZero();
//	node_data srcNode;
//	boolean ans = true;
//		for (node_data node: ans) {
//				srcNode=node;
//				int sum = connect1(node);
//				if(sum!= vertex.size()){
//				return false;
//				}
//				else{
//				ans=ans&&connect2();
//				}
//
//				return ans;
//				}
//
//
//
//				return false;
//
//				}
//private int connect1(node_data node){
//		if(node.getTag()==1){
//		return 0;
//		}
//		node.setTag(1);
//		Collection<edge_data> neigburs = this.GA.getE(node.getKey());
//		int counter=1;
//		for(edge_data edge : neigburs){
//		counter+=connect1(this.GA.getNode(edge.getDest()));
//		}
//		return counter;
//		}