package gui;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Graph_GUI {
    public static void printGraph(DGraph d){
        StdDraw.setCanvasSize(1000,1000);
        StdDraw.setXscale(-100,100);
        StdDraw.setYscale(-100,100);
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.15);
        Iterator it = d.getHash().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry map =(Map.Entry)it.next();
            int key = (int)map.getKey();
            Point3D p = d.getNode(key).getLocation();
            StdDraw.filledCircle(p.x(),p.y(),0.4);
            StdDraw.text(p.x(),p.y()+0.5,""+d.getNode(key).getKey());
        }
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.01);
        Iterator it2 =d.getEdgeHash().entrySet().iterator();
        while(it2.hasNext()){
            Map.Entry map2 = (Map.Entry)it2.next();
            int keySrc =(int)map2.getKey();
            HashMap temp =(HashMap)d.getEdgeHash().get(keySrc);
            Iterator it3 = temp.entrySet().iterator();
            if(temp!=null){
                while(it3.hasNext()){
                    Map.Entry map3=(Map.Entry)it3.next();
                    int keyDest = (int)map3.getKey();
                    if(temp.get(keyDest)!=null){
                        edge_data edge = (edge_data)temp.get(keyDest);
                        double weight = edge.getWeight();
                        node_data srcNode = d.getNode(keySrc);
                        node_data dstNode =d.getNode(keyDest);
                        Point3D srcP= srcNode.getLocation();
                        Point3D dstP= dstNode.getLocation();
                        StdDraw.line(srcP.x(),srcP.y(),dstP.x(),dstP.y());
                        StdDraw.setPenColor(Color.BLACK);
                        StdDraw.text((srcP.x()+dstP.x())/2,(srcP.y()+dstP.y())/2,"" + weight);
                    }
                }


            }





        }





    }

    public static void main(String[] args) {
        DGraph Graph = new DGraph();
        Point3D p[] = new Point3D[6];
        Node n[] = new Node[6];
        p[0]=new Point3D(0,0,0);
        p[1]=new Point3D(29,1,0);
        p[2]=new Point3D(76,80,0);
        p[3]=new Point3D(40,8,0);
        p[4]=new Point3D(-35,5,0);
        p[5]=new Point3D(-60,12,0);
        n[0]=new Node(1,20,0,p[0],"a");
        n[1]=new Node(2,10,0,p[1],"b");
        n[2]=new Node(3,15,0,p[2],"c");
        n[3]=new Node(4,5,0,p[3],"d");
        n[4]=new Node(5,0,0,p[4],"e");
        n[5]=new Node(6,25,0,p[5],"f");
        for (int i = 0; i <p.length ; i++) {
            Graph.addNode(n[i]);
        }
        Graph.connect(1,4,20);
        Graph.connect(2,1,10);
        Graph.connect(6,1,5);
        Graph.connect(1,6,6);
        Graph.connect(4,3,440);
        Graph.connect(5,6,1);
        Graph.connect(6,3,22);
        Graph.connect(3,4,20);
        Graph.connect(2,5,10);
        printGraph(Graph);
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
