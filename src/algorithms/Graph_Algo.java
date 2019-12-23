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
		transPose(this.GA);
		tagZero();
		Collection<node_data> s1 =this.GA.getV();
		Iterator it1 =s1.iterator();
		node_data temp1 = (node_data)it1.next();
		DFSUtil(temp1);
		for (node_data node1: s1) {
			if(node1.getTag()==0) return false;
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
			for (edge_data edge : temp) {
				if (this.GA.getNode(edge.getDest()) != null && this.GA.getE(edge.getDest()) != null && this.GA.getNode(edge.getDest()).getTag() == 0) {
					DFSUtil(this.GA.getNode(edge.getDest()));
				}
			}
		}

	   private void transPose(graph g) {
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

	@Override
	public double shortestPathDist(int src, int dest) {

		return 0;
	}


	 private void tagNWeightZero(){
		 Collection<node_data>  temp = this.GA.getV();
		 for(node_data node : temp){
			 node.setTag(0);
		 	 node.setWeight(Integer.MAX_VALUE);
		 }

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
		graph copy;

		return null;
	}

}