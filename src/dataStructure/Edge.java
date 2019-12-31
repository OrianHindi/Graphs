package dataStructure;

import java.io.Serializable;

public class Edge implements edge_data, Serializable {
    private int src;
    private int dest;
    private double weight;
    private String info;
    private int tag;


    public Edge(int src,int dest,double weight){
        this.src=src;
        this.dest=dest;
        this.weight=weight;
        this.info="";
        this.tag=0;
    }
    public Edge(int src,int dest,double weight,String info,int tag){
        this.src=src;
        this.dest=dest;
        this.weight=weight;
        this.info=info;
        this.tag=tag;
    }
    public Edge(Edge p){
        this(p.getSrc(),p.getDest(),p.getWeight(),p.getInfo(),p.getTag());
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() { return this.weight; }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    @Override
    public int getTag() { return this.tag; }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }
    public String toString(){
        return "<" + this.getSrc() + "," + this.getDest() + "," + this.getWeight() + ">";
    }
}
