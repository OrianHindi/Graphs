//package Ex2Testing;
//
//import dataStructure.DGraph;
//import dataStructure.Edge;
//import dataStructure.Node;
//import dataStructure.edge_data;
//import org.junit.Before;
//import org.junit.Test;
//import utils.Point3D;
//
//import java.awt.*;
//
//import static org.junit.Assert.*;
//
//public class DGraphTest {
//    static DGraph Graph = new DGraph();
//    static Point3D p[]= new Point3D[6];
//    static Node n[] = new Node[6];
//
//    @Before
//    public void BeforeEach(){
//        p[0]=new Point3D(3,5,0);
//        p[1]=new Point3D(2,1,0);
//        p[2]=new Point3D(1,80,0);
//        p[3]=new Point3D(0,8,0);
//        p[4]=new Point3D(4,5,0);
//        p[5]=new Point3D(1,12,0);
//        n[0]=new Node(1,20,0,p[0],"a");
//        n[1]=new Node(2,10,0,p[1],"b");
//        n[2]=new Node(3,15,0,p[2],"c");
//        n[3]=new Node(4,5,0,p[3],"d");
//        n[4]=new Node(5,0,0,p[4],"e");
//        n[5]=new Node(6,25,0,p[5],"f");
//        for (int i = 0; i <p.length ; i++) {
//            Graph.addNode(n[i]);
//        }
//        Graph.connect(1,4,20);
//        Graph.connect(2,1,10);
//        Graph.connect(6,1,5);
//        Graph.connect(1,6,6);
//        Graph.connect(4,3,440);
//        Graph.connect(5,6,1);
//        Graph.connect(6,3,22);
//        Graph.connect(3,4,20);
//        Graph.connect(2,5,10);
//    }
//    @Test
//    public void getNode() {
//        for (int i = 0; i <n.length ; i++) {
//            assertEquals(n[i],Graph.getNode(i+1));
//        }
//    }
//
//    @Test
//    public void getEdge() {
//        edge_data ans[]= new edge_data[9];
//        ans[0]= new Edge(1,4,20);
//        ans[1]= new Edge(2,1,10);
//        ans[2]= new Edge(6,1,5);
//        ans[3]=new Edge(1,6,6);
//        ans[4]= new Edge(4,3,440);
//        ans[5]= new Edge(5,6,1);
//        ans[6]= new Edge(6,3,22);
//        ans[7]=new Edge(3,4,20);
//        ans[8]= new Edge(2,5,10);
//
//        for (int i = 0; i <ans.length ; i++) {
//            assertEquals(Graph.getEdge(ans[i].getSrc(),ans[i].getDest()).toString(),ans[i].toString());
//            System.out.println(Graph.getEdge(ans[i].getSrc(),ans[i].getDest()).toString()+ "," +ans[i].toString());
//        }
//
//    }
//
//    @Test
//    public void addNode() {
//    }
//
//    @Test
//    public void connect() {
//    }
//
//    @Test
//    public void getV() {
//    }
//
//    @Test
//    public void getE() {
//    }
//
//    @Test
//    public void removeNode() {
//    }
//
//    @Test
//    public void removeEdge() {
//    }
//
//    @Test
//    public void nodeSize() {
//    }
//
//    @Test
//    public void edgeSize() {
//    }
//
//    @Test
//    public void getMC() {
//    }
//}