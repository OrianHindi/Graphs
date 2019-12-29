package Ex2Testing;

import dataStructure.*;
import org.junit.Before;
import org.junit.Test;
import utils.Point3D;

import static org.junit.Assert.assertEquals;

public class DGraphTest {
    static DGraph Graph = new DGraph();
    static Point3D p[]= new Point3D[6];
    static Node n[] = new Node[6];

    @Before
    public void BeforeEach(){
        p[0]=new Point3D(3,5,0);
        p[1]=new Point3D(2,1,0);
        p[2]=new Point3D(1,80,0);
        p[3]=new Point3D(0,8,0);
        p[4]=new Point3D(4,5,0);
        p[5]=new Point3D(1,12,0);
        n[0]=new Node(p[0]);
        n[1]=new Node(p[1]);
        n[2]=new Node(p[2]);
        n[3]=new Node(p[3]);
        n[4]=new Node(p[4]);
        n[5]=new Node(p[5]);
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
    }
    @Test
    public void getNode() {
        DGraph p = new DGraph();
        node_data a1 = new Node(new Point3D(2,3,7));
        p.addNode(a1);
        assertEquals(p.getNode(a1.getKey()),a1);

    }

    @Test
    public void getEdge() {
        edge_data ans[]= new edge_data[9];
        ans[0]= new Edge(1,4,20);
        ans[1]= new Edge(2,1,10);
        ans[2]= new Edge(6,1,5);
        ans[3]=new Edge(1,6,6);
        ans[4]= new Edge(4,3,440);
        ans[5]= new Edge(5,6,1);
        ans[6]= new Edge(6,3,22);
        ans[7]=new Edge(3,4,20);
        ans[8]= new Edge(2,5,10);

        for (int i = 0; i <ans.length ; i++) {
            assertEquals(Graph.getEdge(ans[i].getSrc(),ans[i].getDest()).toString(),ans[i].toString());
            System.out.println(Graph.getEdge(ans[i].getSrc(),ans[i].getDest()).toString()+ "," +ans[i].toString());
        }
    }

    @Test
    public void addNode() {
        DGraph p = new DGraph();
        node_data a1 = new Node(new Point3D(2,3,7));
        node_data a2 = new Node(new Point3D(1,5,3));

        p.addNode(a1);
        p.addNode(a2);

        assertEquals(p.getNode(7), a1);
        assertEquals(p.getNode(8), a2);
    }

    @Test
    public void connect() {
        DGraph p = new DGraph();
        node_data a1 = new Node(new Point3D(7,8,3));
        node_data a2 = new Node(new Point3D(1,5,3));

        p.addNode(a1);
        p.addNode(a2);

        p.connect(a1.getKey(),a2.getKey(),1);
        p.getEdge(a1.getKey(),a2.getKey());
        boolean flag1 = p.getE(a1.getKey()).contains(p.getEdge(a1.getKey(),a2.getKey()));
        assertEquals(flag1,true);
    }


    @Test
    public void getV() {
    }

    @Test
    public void getE() {
    }

    @Test
    public void removeNode() {
        DGraph p = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,3));
        node_data a2 = new Node(new Point3D(2,4,7));
        node_data a3 = new Node(new Point3D(5,3,4));
        p.addNode(a1);
        p.addNode(a2);
        p.addNode(a3);
        p.removeNode(1);
        p.removeNode(2);
        p.removeNode(3);
        assertEquals(null,p.getNode(1));
        assertEquals(null,p.getNode(2));
        assertEquals(null,p.getNode(3));
    }

    @Test
    public void removeEdge() {
        DGraph p = new DGraph();
        node_data a1 = new Node(new Point3D(1,5,3));
        node_data a2 = new Node(new Point3D(1,2,8));
        p.addNode(a1);
        p.addNode(a2);
        p.connect(a1.getKey(),a2.getKey(), 1);
        p.removeEdge(a1.getKey(),a2.getKey());
        assertEquals(p.getEdge(a1.getKey(),a2.getKey()),null);
    }

    @Test
    public void nodeSize() {
        DGraph p = new DGraph();
        node_data a1 = new Node(new Point3D(3,2,3));
        node_data a2 = new Node(new Point3D(1,4,8));
        p.addNode(a1);
        p.addNode(a2);
        assertEquals(2,p.nodeSize());
        p.removeNode(a1.getKey());
        assertEquals(1,p.nodeSize());
    }

    @Test
    public void edgeSize() {
        DGraph p = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,0));
        node_data a2 = new Node(new Point3D(1,2,0));
        p.addNode(a1);
        p.addNode(a2);
        p.connect(a1.getKey(),a2.getKey(),10);
        assertEquals(p.edgeSize(),1);
        p.removeEdge(a1.getKey(),a2.getKey());
        assertEquals(p.edgeSize(),0);
    }


    @Test
    public void getMC() {
        DGraph p = new DGraph();
        node_data a1 = new Node(new Point3D(1,2,3));
        node_data a2 = new Node(new Point3D(1,2,3));
        p.addNode(a1);
        p.addNode(a2);
        p.connect(a1.getKey(),a2.getKey(),10);
        p.removeEdge(a1.getKey(),a2.getKey());
        assertEquals(4 , p.getMC());
    }
}