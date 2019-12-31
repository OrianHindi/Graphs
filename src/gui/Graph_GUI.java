package gui;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Graph_GUI {
    public static DGraph graph= new DGraph();
    public static Graph_Algo graph_algo= new Graph_Algo();


    public Graph_GUI (){
        graph= new DGraph();
        graph_algo= new Graph_Algo();
        StdDraw.g=this;
        this.openCanvas();
    }
    public Graph_GUI(DGraph graph){
        this.graph = graph;
        graph_algo.init(graph);
        StdDraw.g=this;
        this.openCanvas();
    }
    public  boolean isConnected(){
        graph_algo.init(graph);
        return graph_algo.isConnected();
    }

    public  void add_node (double x, double y) {   //change key
        checKeys(graph.getV());
        Point3D p = new Point3D(x,y);
        Node n = new Node(p);
        graph.addNode(n);
        StdDraw.g=this;
    }
    public void checKeys(Collection<node_data> temp){
        int key =Integer.MIN_VALUE;
        for (node_data node: temp) {
            if(node.getKey()>key){
                key=node.getKey();
            }
        }
        Node.keyNum=++key;

    }

    public void add_edge (int src,int dest,double weight) {
        graph.connect(src,dest,weight);
    }

    public  void remove_edge (int src,int dest) {
        graph.removeEdge(src, dest);

    }

    public void remove_node (int key) {     //change key

        graph.removeNode(key);
    }
    public void save(String file_name){
        graph_algo.init(graph);
        graph_algo.save(file_name);
    }

    public void load(String file_name){
        graph_algo.init(file_name);
        graph=(DGraph)graph_algo.copy();

    }
    public node_data findNode(double x, double y){
        Collection<node_data> temp = graph.getV();
        for (node_data node: temp) {
            if(x>=node.getLocation().x()-0.4 && x<= node.getLocation().x()+0.4 && y>=node.getLocation().y()-0.4 && y<=node.getLocation().y()+0.4) return node;
        }
        return null;
    }
    public double ShortestPath(int src,int dest){
        graph_algo.init(graph);
        return graph_algo.shortestPathDist(src,dest);
    }
    public List<node_data> ShortestPathList(int src,int dest){
        graph_algo.init(graph);
        return graph_algo.shortestPath(src,dest);
    }

    public void Clean(){
        graph= new DGraph();
        graph_algo.init(graph);
        this.printGraph();
    }

    private void openCanvasDef(){
        StdDraw.setCanvasSize(1000,1000);

    }

    public void openCanvas(){
        StdDraw.setCanvasSize(1000,1000);
        StdDraw.setXscale(-100,100);
        StdDraw.setYscale(-100,100);
        printGraph();
    }
    public  void printGraph(){
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setPenRadius(0.15);
        DGraph d = this.graph;
        if(d!=null) {
            Iterator it = d.getV().iterator();
            while (it.hasNext()) {
                node_data temp = (node_data)it.next();
                Point3D p = temp.getLocation();
                StdDraw.filledCircle(p.x(), p.y(), 0.8);
                StdDraw.text(p.x(), p.y() + 1, "" + temp.getKey());
            }
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.003);
            Iterator it1 = d.getV().iterator();
            while(it1.hasNext()){
                node_data temp1 = (node_data)it1.next();
                if(d.getE(temp1.getKey())!=null){
                    Iterator it2 = d.getE(temp1.getKey()).iterator();
                    while(it2.hasNext()){
                        edge_data temp2 = (edge_data)it2.next();
                        if(temp2!=null){
                            StdDraw.setPenRadius(0.003);
                            StdDraw.setPenColor(Color.RED);
                            double weight = temp2.getWeight();
                            node_data srcNode = d.getNode(temp2.getSrc());
                            node_data dstNode = d.getNode(temp2.getDest());
                            Point3D srcP = srcNode.getLocation();
                            Point3D dstP = dstNode.getLocation();
                            StdDraw.line(srcP.x(), srcP.y(), dstP.x(), dstP.y());

                            double x = 0.2*srcP.x()+0.8*dstP.x();
                            double y = 0.2*srcP.y() + 0.8*dstP.y();
                            StdDraw.setPenColor(Color.BLACK);
                            StdDraw.text(x,y, "" + weight);

                            StdDraw.setPenColor(Color.YELLOW);
                            StdDraw.setPenRadius(0.15);
                            double x1 = 0.1*srcP.x()+0.9*dstP.x();
                            double y1 = 0.1*srcP.y()+0.9*dstP.y();
                            StdDraw.filledCircle(x1,y1,0.8);

                        }

                    }
                }
            }

        }

    }
    public void showPath(ArrayList<node_data> ans){
        StdDraw.setPenRadius(0.15);
        for (int i = 0; i <ans.size() ; i++) {
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.filledCircle(graph.getNode(ans.get(i).getKey()).getLocation().x(),graph.getNode(ans.get(i).getKey()).getLocation().y(),0.8);
        }
        for (int i = 0; i <ans.size()-1 ; i++) {
            StdDraw.setPenRadius(0.003);
            StdDraw.setPenColor(Color.GREEN);
            Point3D src =graph.getNode(ans.get(i).getKey()).getLocation();
            Point3D dst = graph.getNode(ans.get(i+1).getKey()).getLocation();
            StdDraw.line(src.x(),src.y(),dst.x(),dst.y());
        }

    }

    public static void main(String[] args) {
        DGraph p = new DGraph();
        Node x1 = new Node(new Point3D(6,30));
        Node x2 = new Node(new Point3D(10,-20));
        Node x3 = new Node(new Point3D(-30,5));
        Node x4 = new Node(new Point3D(-60,60));
        Node x5 = new Node(new Point3D(8,80));
        Node x6 = new Node(new Point3D(60,-5));
        p.addNode(x1);
        p.addNode(x2);
        p.addNode(x3);
        p.addNode(x4);
        p.addNode(x5);
        p.addNode(x6);
        p.connect(1,5,7);
        p.connect(2,3,67);
        p.connect(2,6,10);
        p.connect(6,5,30);
        p.connect(5,6,5);
        p.connect(4,1,50);
        Graph_GUI g = new Graph_GUI(p);

    }
}
