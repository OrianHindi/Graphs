package gui;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;
import utils.Range;
import utils.StdDraw;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Graph_GUI extends Thread {
    public static DGraph graph= new DGraph();
    public static Graph_Algo graph_algo= new Graph_Algo();
    private static Range xRange= new Range(0,0);
    private static Range yRange= new Range(0,0);
    private int ModeCount=0;


    public Graph_GUI (){
        graph= new DGraph();
        graph_algo= new Graph_Algo();
        StdDraw.g=this;
        this.openCanvas();
        ModeCount=graph.getMC();
        this.start();
    }
    public Graph_GUI(DGraph graph){
        this.graph = graph;
        graph_algo.init(graph);
        StdDraw.g=this;
        this.openCanvas();
        ModeCount=graph.getMC();
        this.start();
    }
    public  boolean isConnected(){
        graph_algo.init(graph);
        return graph_algo.isConnected();
    }

    public  void add_node (double x, double y) {//change key
        checKeys(graph.getV());
        Point3D p = new Point3D(x,y);
        Node n = new Node(p);
        graph.addNode(n);
        StdDraw.g=this;
        if(x>xRange.get_max() || x<xRange.get_min() || y>yRange.get_max() ||y<yRange.get_min())openCanvas();

    }
    public void checKeys(Collection<node_data> temp){
        int key =Integer.MIN_VALUE;
        for (node_data node: temp) {
            if(node.getKey()>key){
                key=node.getKey();
            }
        }
        if(key==Integer.MIN_VALUE) key=0;
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
    public void run(){
        while(true) {
            if (ModeCount != graph.getMC()) {
                printGraph();
                ModeCount = graph.getMC();
            }
        }
    }


    public Range findRangeX(){
        if(graph.nodeSize()!=0) {
            double min = Integer.MAX_VALUE;
            double max = Integer.MIN_VALUE;
            Collection<node_data> V = graph.getV();
            for (node_data node : V) {
                if (node.getLocation().x() > max) max = node.getLocation().x();
                if (node.getLocation().x() < min) min = node.getLocation().x();
            }
            Range ans = new Range(min, max);
            xRange = ans;
            return ans;
        }
        else{
            Range Default = new Range(-100,100);
            xRange=Default;
            return Default;
        }
    }
    public Range findRangeY(){
        if(graph.nodeSize()!=0) {
            double min = Integer.MAX_VALUE;
            double max = Integer.MIN_VALUE;
            Collection<node_data> V = graph.getV();
            for (node_data node : V) {
                if (node.getLocation().y() > max) max = node.getLocation().y();
                if (node.getLocation().y() < min) min = node.getLocation().y();
            }
            Range ans = new Range(min, max);
            yRange = ans;
            return ans;
        }
        else{
            Range Default = new Range(-100,100);
            yRange=Default;
            return Default;
        }
    }

    public void openCanvas(){
        StdDraw.setCanvasSize(800,800);
        Range x = findRangeX();
        Range y = findRangeY();
        StdDraw.setXscale(x.get_min()-100,x.get_max()+100);
        StdDraw.setYscale(y.get_min()-100,y.get_max()+100);
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

    public List<node_data> TSP(List<Integer> targets){
        graph_algo.init(graph);
        return graph_algo.TSP(targets);
    }
    public void showPath(ArrayList<node_data> ans){
        StdDraw.setPenRadius(0.15);
        for (int i = 0; i <ans.size() ; i++) {
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.filledCircle(graph.getNode(ans.get(i).getKey()). getLocation().x(),graph.getNode(ans.get(i).getKey()).getLocation().y(),0.8);
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
        Point3D x = new Point3D(-400,300,0);
        Point3D x2 = new Point3D(300,150,0);
        Point3D x3 = new Point3D(380,-300,0);
        Point3D x4 = new Point3D(150,-400,0);
        Point3D x5 = new Point3D(0,-450,0);
        Point3D x6 = new Point3D(200,-300,0);
        Point3D x7 = new Point3D(-400,-150,0);
        Point3D x8 = new Point3D(-400,120,0);
        Node a1 = new Node(x);
        Node a2 = new Node(x2);
        Node a3 = new Node(x3);
        Node a4 = new Node(x4);
        Node a5 = new Node(x5);
        Node a6 = new Node(x6);
        Node a7 = new Node(x7);
        Node a8 = new Node(x8);
        DGraph d = new DGraph();
        d.addNode(a1);
        d.addNode(a2);
        d.addNode(a3);
        d.addNode(a4);
        d.addNode(a5);
        d.addNode(a6);
        d.addNode(a7);
        d.addNode(a8);
        d.connect(1,2,5);
        d.connect(1,5,2);
        d.connect(1,3,6);
        d.connect(1,6,5);
        d.connect(3,4,7);
        d.connect(2,8,8);
        d.connect(2,7,3);
        d.connect(5,1,5);
        d.connect(5,6,2);
        d.connect(6,1,3);
        d.connect(6,5,3);
        d.connect(6,7,3);
        d.connect(7,6,3);
        Graph_GUI p = new Graph_GUI(d);
        d.connect(2,3,40);
        d.connect(2,4,5);
        d.connect(4,6,7);
        d.connect(4,3,6);



    }
}
