//package Ex2Testing;
//
//import dataStructure.Edge;
//import dataStructure.edge_data;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class EdgeTest {
//    static edge_data edgeTest[]= new edge_data[6];
//
//    @Before
//    public void BeforeEach(){
//        edgeTest[0]=new Edge(1,6,20,"a",1);
//        edgeTest[1]=new Edge(2,7,30,"b",2);
//        edgeTest[2]=new Edge(3,8,18,"c",3);
//        edgeTest[3]=new Edge(4,9,15,"d",4);
//        edgeTest[4]=new Edge(5,10,13,"e",5);
//        edgeTest[5]=new Edge(6,11,10,"f",6);
//    }
//    @Test
//    public void getSrc() {
//        int ans[]={1,2,3,4,5,6,};
//        for (int i = 0; i <edgeTest.length ; i++) {
//            assertEquals(ans[i],edgeTest[i].getSrc());
//        }
//    }
//
//    @Test
//    public void getDest() {
//        int ans[]= {6,7,8,9,10,11};
//        for (int i = 0; i <edgeTest.length ; i++) {
//            assertEquals(ans[i],edgeTest[i].getDest());
//        }
//    }
//
//    @Test
//    public void getWeight() {
//        double ans[] =  {20,30,18,15,13,10};
//        for (int i = 0; i <edgeTest.length ; i++) {
//            assertEquals(ans[i],edgeTest[i].getWeight(),0.01);
//        }
//    }
//
//    @Test
//    public void getInfo() {
//        String ans[] = {"a","b","c","d","e","f"};
//        for (int i = 0; i <edgeTest.length ; i++) {
//            assertEquals(ans[i],edgeTest[i].getInfo());
//        }
//    }
//
//    @Test
//    public void setInfo() {
//        String ans[] = {"f","e","d","c","b","a"};
//        for (int i = 0; i <edgeTest.length ; i++) {
//            edgeTest[i].setInfo(ans[i]);
//        }
//        for (int i = 0; i <edgeTest.length ; i++) {
//            assertEquals(ans[i],edgeTest[i].getInfo());
//        }
//    }
//
//    @Test
//    public void getTag() {
//        int ans[]= {1,2,3,4,5,6};
//        for (int i = 0; i <edgeTest.length ; i++) {
//            for (int j = 0; j <edgeTest.length; j++) {
//                assertEquals(ans[i],edgeTest[i].getTag());
//            }
//        }
//    }
//
//    @Test
//    public void setTag() {
//        int ans[]= {5,6,7,8,9,10};
//        for (int i = 0; i <edgeTest.length ; i++) {
//            edgeTest[i].setTag(ans[i]);
//        }
//        for (int i = 0; i <edgeTest.length ; i++) {
//            assertEquals(ans[i],edgeTest[i].getTag());
//        }
//    }
//}