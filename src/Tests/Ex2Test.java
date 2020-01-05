package Tests;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.graph;
import gui.Graph_GUI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Point3D;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Ex2Test {
    private static graph _graph;
    private static graph_algorithms _alg;
    public static final double EPS = 0.001;
    private static Point3D min = new Point3D(0,0,0);
    private static Point3D max = new Point3D(100,100,0);
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        _graph = tinyGraph();
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testConnectivity() {
        _alg = new Graph_Algo(_graph);
        boolean con = _alg.isConnected();
        if(!con) {
            fail("shoulbe be connected");
        }
    }
    @Test
    void testgraph() {
        boolean ans = drawGraph(_graph);
        assertTrue(ans);
    }

    private static graph tinyGraph() {
        graph ans = new DGraph();
        return ans;
    }
    boolean drawGraph(graph g) {
        Graph_GUI p = new Graph_GUI((DGraph)g);
        return true;

    }
}
