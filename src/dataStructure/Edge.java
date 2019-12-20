package dataStructure;

public class Edge implements edge_data {
    private int src;
    private int dest;
    private double weight;
    private String info;
    private int tag;

    public Edge(){
        this.src=0;
        this.dest=0;
        this.weight=0;
        this.info=null;
        this.tag=0;
    }
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
        this.src=p.src;
        this.dest=p.dest;
        this.weight=p.weight;
        this.info=p.info;
        this.tag=p.tag;
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
    public double getWeight() {

        return this.weight;
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
        return "<" + this.getSrc() + "," + this.getDest() + "," + this.getWeight() + ">";
    }
}
