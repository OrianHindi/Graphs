package dataStructure;

import utils.Point3D;

import java.io.Serializable;
import java.security.Key;

public class Node implements node_data, Serializable {
    private int key;
    private double weight=0;
    private int tag=0;
    private Point3D location;
    private String info;
    static private int KeyNum=1;


    public Node(){
        this.key=KeyNum++;
        this.weight=0;
        this.tag=0;
        this.location=null;
        this.info=null;
    }

    public Node(double weight,int tag,Point3D p,String info){
        this.key= KeyNum++;
        this.weight=weight;
        this.tag=tag;
        this.location=new Point3D(p);
        this.info=info;
    }
    public Node(Node s){
        this.key=s.key;
        this.tag= s.tag;
        this.weight=s.weight;
        this.info=s.info;
        this.location= new Point3D(s.location);
    }
    public Node(Point3D p){
        this.key=KeyNum++;
        this.location=p;
        this.info="";
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public Point3D getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(Point3D p) {
        this.location= new Point3D(p);
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight=w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info=s;

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }
    public String toString(){
        return "" + this.getKey();
    }
}
