package Ex2Testing;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;
import org.junit.Test;
import utils.Point3D;

import static org.junit.Assert.assertEquals;

public class Graph_AlgoTest {

    @Test
    public void init() {
        Graph_Algo g = new Graph_Algo();
        DGraph d = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,3));
        node_data a2 = new Node(new Point3D(1,2,3));
        d.addNode(a1);
        d.addNode(a2);
        d.connect(7,8,9);
        g.init(d);
        assertEquals(d, g);
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
    }

    @Test
    public void isConnected() {
        Graph_Algo g = new Graph_Algo();
        DGraph d = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,3));
        node_data a2 = new Node(new Point3D(1,2,3));
        d.addNode(a1);
        d.addNode(a2);
        d.connect(1,2,9);
        d.connect(2,1,2);
        g.init(d);
        assertEquals(g.isConnected(), true);
    }

    @Test
    public void setNodes() {

    }

    @Test
    public void shortestPathDist() {
    }

    @Test
    public void STPRec() {
    }

    @Test
    public void shortestPath() {
    }

    @Test
    public void TSP() {
    }

    @Test
    public void copy() {
    }
}