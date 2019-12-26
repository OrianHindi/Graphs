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
        this.printGraph();
    }
    public  boolean isConnected(){
        graph_algo.init(graph);
        return graph_algo.isConnected();
    }

    public  void add_node (double x, double y) {   //change key
        Point3D p = new Point3D(x,y);
        Node n = new Node(p);
        graph.addNode(n);
        StdDraw.g=this;
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


    public void openCanvas(){
        StdDraw.setCanvasSize(1000,1000);
        StdDraw.setXscale(-100,100);
        StdDraw.setYscale(-100,100);
        printGraph();
    }
    public  void printGraph(){
        StdDraw.clear();
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.15);
        DGraph d = this.graph;
        if(d!=null) {
            Iterator it = d.getHash().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry map = (Map.Entry) it.next();
                int key = (int) map.getKey();
                Point3D p = d.getNode(key).getLocation();
                StdDraw.filledCircle(p.x(), p.y(), 0.4);
                StdDraw.text(p.x(), p.y() + 0.5, "" + d.getNode(key).getKey());
            }
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.01);
            Iterator it2 = d.getEdgeHash().entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry map2 = (Map.Entry) it2.next();
                int keySrc = (int) map2.getKey();
                HashMap temp = (HashMap) d.getEdgeHash().get(keySrc);
                Iterator it3 = temp.entrySet().iterator();
                if (temp != null) {
                    while (it3.hasNext()) {
                        Map.Entry map3 = (Map.Entry) it3.next();
                        int keyDest = (int) map3.getKey();
                        if (temp.get(keyDest) != null) {
                            edge_data edge = (edge_data) temp.get(keyDest);
                            double weight = edge.getWeight();
                            node_data srcNode = d.getNode(keySrc);
                            node_data dstNode = d.getNode(keyDest);
                            Point3D srcP = srcNode.getLocation();
                            Point3D dstP = dstNode.getLocation();
                            StdDraw.line(srcP.x(), srcP.y(), dstP.x(), dstP.y());
                            StdDraw.setPenColor(Color.BLACK);
                            StdDraw.text((srcP.x() + dstP.x()) / 2, (srcP.y() + dstP.y()) / 2, "" + weight);

                        }
                    }
                }
            }
        }
    }
    public void showPath(ArrayList<node_data> ans){
        for (int i = 0; i <ans.size() ; i++) {
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.filledCircle(graph.getNode(ans.get(i).getKey()).getLocation().x(),graph.getNode(ans.get(i).getKey()).getLocation().y(),0.4);
        }
        for (int i = 0; i <ans.size()-1 ; i++) {
            StdDraw.setPenColor(Color.GREEN);
            Point3D src =graph.getNode(ans.get(i).getKey()).getLocation();
            Point3D dst = graph.getNode(ans.get(i+1).getKey()).getLocation();
            StdDraw.line(src.x(),src.y(),dst.x(),dst.y());
        }
        for (int i = 0; i <ans.size() ; i++) {
            StdDraw.setPenColor(Color.white);
            StdDraw.text(graph.getNode(ans.get(i).getKey()).getLocation().x(),graph.getNode(ans.get(i).getKey()).getLocation().y(),"" +ans.get(i).getKey() );
        }

    }

    public static void main(String[] args) {
        DGraph DG = new DGraph();
        Point3D p[] = new Point3D[6];
        Node n[] = new Node[6];
        p[0]=new Point3D(0,0,0);
        p[1]=new Point3D(29,1,0);
        p[2]=new Point3D(76,80,0);
        p[3]=new Point3D(40,8,0);
        p[4]=new Point3D(-35,5,0);
        p[5]=new Point3D(-60,12,0);
        n[0]=new Node(p[0]);
        n[1]=new Node(p[1]);
        n[2]=new Node(p[2]);
        n[3]=new Node(p[3]);
        n[4]=new Node(p[4]);
        n[5]=new Node(p[5]);
        for (int i = 0; i <p.length ; i++) {
            DG.addNode(n[i]);
        }
         DG.connect(1,4,20);
        DG.connect(2,1,10);
        DG.connect(6,1,5);
        DG.connect(1,6,6);
        DG.connect(4,3,440);

        DG.connect(5,6,1);
        DG.connect(6,3,22);
        DG.connect(3,4,20);
        DG.connect(2,5,10);


        Graph_GUI g = new Graph_GUI();
        g.printGraph();




    }
}
// StdDraw.setPenColor(Color.BLUE);
//         StdDraw.setPenRadius(0.05);
//         Iterator it2 = d.getEdgeHash().entrySet().iterator();
//         while(it2.hasNext()){
//         Map.Entry map =(Map.Entry)it.next();
//         int key = (int)map.getKey();
//         HashMap s =(HashMap)d.getE(key);
//         if(s!=null){
//         Iterator it3 = s.entrySet().iterator();
//         while(it3.hasNext()){
//         Map.Entry map1 = (Map.Entry)it.next();
//         int key1 =(int) map1.getKey();
//         edge_data e = (edge_data)s.get(key1);
//         node_data p = d.getNode(e.getSrc());
//         node_data p1 = d.getNode(e.getDest());
//         Point3D p2 = p.getLocation();
//         Point3D p3 = p1.getLocation();
//         StdDraw.line(p2.x(),p2.y(),p3.x(),p3.y());
//         }
//
//
//         }
//
//         }
