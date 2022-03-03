package sol;

import sol.Edge;
import sol.ITreeNode;
import src.Row;

import java.util.ArrayList;

public class Leaf implements ITreeNode {
    private String decisionName;

    public Leaf(String decision){
        this.decisionName = decision;
    }

    @Override
    public String getDecision(Row forDatum) {
        return this.decisionName;
    }
/*
    @Override
    public String getNodeAttName() {
        return this.decisionName;
    }*/

    public ArrayList<Edge> getLsOfEdge(){
        return null;
    }


    public String getNodeAttName() {
        return this.decisionName;
    }
}
