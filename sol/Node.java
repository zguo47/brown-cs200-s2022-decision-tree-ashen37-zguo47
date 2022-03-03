package sol;

import sol.Edge;
import sol.ITreeNode;
import src.Row;

import java.util.ArrayList;
import java.util.List;

public class Node implements ITreeNode {
    private String attributeName;
    private String defaultValue;
    private ArrayList<Edge> lsOfEdge;

    public Node(String name, String defaultValue, ArrayList<Edge> nextAttributes){
        this.attributeName = name;
        this.defaultValue = defaultValue;
        this.lsOfEdge = nextAttributes;
    }

    public String getNodeAttName() {
        return this.attributeName;
    }


    @Override
    public String getDecision(Row forDatum) {

        String currAttVal = forDatum.getAttributeValue(attributeName);

        List<Edge> lsOfEdge = this.getLsOfEdge();

        for (Edge e : lsOfEdge){
            if (e.getEdgeValue().equals(currAttVal)){
                /*this.tree = e.getNext();*/
                return e.getNext().getDecision(forDatum);
            }
        }
        return this.defaultValue;
    }

    public ArrayList<Edge> getLsOfEdge(){
        return this.lsOfEdge;
    }


}
