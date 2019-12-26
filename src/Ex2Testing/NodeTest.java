package Ex2Testing;

import dataStructure.Node;
import dataStructure.node_data;
import org.junit.Before;
import org.junit.Test;
import utils.Point3D;

import static org.junit.Assert.*;


public class NodeTest {

    static node_data[] nodeTest = new node_data[6];
    @Before
    public void BeforeEach(){
        nodeTest[0]= new Node(new Point3D(3,4,0));
        nodeTest[1]=new Node(new Point3D(4,5,0));
        nodeTest[2]=new Node(new Point3D(5,6,0));
        nodeTest[3]=new Node(new Point3D(6,7,0));
        nodeTest[4]=new Node(new Point3D(7,8,0));
        nodeTest[5]=new Node(new Point3D(8,9,0));
    }
    @Test
    public void getKey() {
        int ans[]= {1,2,3,4,5,6};
        for (int i = 0; i <nodeTest.length ; i++) {
            assertEquals(ans[i],nodeTest[i].getKey());
        }
    }

    @Test
    public void getLocation() {
        Point3D ans[]= {new Point3D(3,4,0),new Point3D(4,5,0),new Point3D(5,6,0),new Point3D(6,7,0),new Point3D(7,8,0),new Point3D(8,9,0)};
        for (int i = 0; i <nodeTest.length ; i++) {
            assertEquals(ans[i],nodeTest[i].getLocation());
        }
    }

    @Test
    public void setLocation() {
        Point3D ans[] = new Point3D[6];
        int x= 20;
        int y =20;
        for (int i = 0; i <nodeTest.length ; i++) {
            ans[i]=new Point3D(x,y,0);
            x--;
            y--;
        }
        for (int i = 0; i <nodeTest.length ; i++) {
            nodeTest[i].setLocation(ans[i]);
        }
        for (int i = 0; i <nodeTest.length ; i++) {
            assertEquals(nodeTest[i].getLocation(),ans[i]);
        }

    }

    @Test
    public void getWeight() {
        double ans=0;
        for(int i = 0; i <nodeTest.length ; i++) {
            assertEquals(ans,nodeTest[i].getWeight(),0.01);

        }
    }

    @Test
    public void setWeight() {
        double ans[]= {3.3,4.4,5.5,6.6,7.7,8.8};
        for (int i = 0; i <nodeTest.length ; i++) {
            nodeTest[i].setWeight(ans[i]);
        }
        for (int i = 0; i <nodeTest.length ; i++) {
            assertEquals(ans[i],nodeTest[i].getWeight(),0.01);
        }
    }

    @Test
    public void getInfo() {
        String ans="";
        for (int i = 0; i <nodeTest.length ; i++) {
            assertEquals(ans,nodeTest[i].getInfo());
        }

    }

    @Test
    public void setInfo() {
        String ans[] = {"f","e","d","c","b","a"};
        for (int i = 0; i <nodeTest.length ; i++) {
            nodeTest[i].setInfo(ans[i]);
        }
        for (int i = 0; i <nodeTest.length ; i++) {
            assertEquals(ans[i],nodeTest[i].getInfo());
        }
    }

    @Test
    public void getTag() {
        int ans=0;
        for (int i = 0; i <nodeTest.length ; i++) {
            assertEquals(ans,nodeTest[i].getTag());
        }
    }

    @Test
    public void setTag() {
        int ans[] = {2,3,4,5,6,7};
        for (int i = 0; i <nodeTest.length ; i++) {
            nodeTest[i].setTag(ans[i]);
        }
        for (int i = 0; i <nodeTest.length ; i++) {
            assertEquals(ans[i],nodeTest[i].getTag());
        }
    }
}