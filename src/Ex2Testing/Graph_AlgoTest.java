package Ex2Testing;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;
import org.junit.Test;
import utils.Point3D;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class Graph_AlgoTest {

    @Test
    public void init() {
        boolean flag = true;
        Graph_Algo g = new Graph_Algo();
        DGraph d = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,3));
        node_data a2 = new Node(new Point3D(1,2,3));
        d.addNode(a1);
        d.addNode(a2);
        d.connect(a1.getKey(),a2.getKey(),9);
        g.init(d);
        Collection<node_data> temp = d.getV();


    }

    @Test
    public void testInit() {
        Graph_Algo g = new Graph_Algo();
        DGraph d = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,3));
        node_data a2 = new Node(new Point3D(1,2,3));
        d.addNode(a1);
        d.addNode(a2);
        d.connect(1,2,9);
        d.connect(2,1,9);
        g.init(d);
        g.save("test");
        Graph_Algo g2 = new Graph_Algo();
        g2.init("test");
        assertEquals(g.isConnected() == g2.isConnected() , true);
    }

    @Test
    public void save() {
        Graph_Algo g = new Graph_Algo();
        DGraph d = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,3));
        node_data a2 = new Node(new Point3D(1,2,3));
        d.addNode(a1);
        d.addNode(a2);
        d.connect(1,2,9);
        d.connect(2,1,9);
        g.init(d);
        g.save("test");
        Graph_Algo g2 = new Graph_Algo();
        g2.init("test");
        assertEquals(g.isConnected() == g2.isConnected() , true);
    }

    @Test
    public void isConnected() {
        Graph_Algo g = new Graph_Algo();
        DGraph d = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,3));
        node_data a2 = new Node(new Point3D(1,2,3));
        d.addNode(a1);
        d.addNode(a2);
        d.connect(a1.getKey(),a2.getKey(),9);
        d.connect(a2.getKey(),a1.getKey(),2);
        g.init(d);
        assertEquals(g.isConnected(), true);
    }


    @Test
    public void shortestPathDist() {
        Graph_Algo g = new Graph_Algo();
        DGraph d = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,3));
        node_data a2 = new Node(new Point3D(1,2,3));
        d.addNode(a1);
        d.addNode(a2);
        d.connect(a1.getKey(),a2.getKey(),9.0);
        g.init(d);
        double ans_excepted = 9.0;
        double ans = g.shortestPathDist(a1.getKey(),a2.getKey());
        assertEquals(ans_excepted, ans, 0.001);
    }


    @Test
    public void shortestPath() {
    node_data a1 = new Node(new Point3D(1,2,3));
    node_data a2 = new Node(new Point3D(1,2,3));
    node_data a3 = new Node(new Point3D(1,2,3));
    DGraph d = new DGraph();
    d.addNode(a1);
    d.addNode(a2);
    d.addNode(a3);
    d.connect(a1.getKey(),a2.getKey(),3);
    d.connect(a1.getKey(),a3.getKey(),4);
    Graph_Algo g = new Graph_Algo();
    g.init(d);
    assertEquals(g.shortestPath(a1.getKey(),a3.getKey()).toString(), "[1, 3]");
    }

    @Test
    public void TSP() {

    }

    @Test
    public void copy() {
        Graph_Algo g = new Graph_Algo();
        DGraph d = new DGraph();
        node_data a1 = new Node(new Point3D(1, 2, 3));
        node_data a2 = new Node(new Point3D(1, 2, 3));
        d.addNode(a1);
        d.addNode(a2);
        d.connect(a1.getKey(), a2.getKey(), 9.0);
        g.init(d);
        graph g2 = new DGraph();
        g2 = g.copy();
        assertEquals(g2.nodeSize(), 2);
    }
}