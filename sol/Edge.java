package sol;

import sol.ITreeNode;

public class Edge {
    private String edgeValue;
    private ITreeNode nextAttribute;

    public Edge(String edgeName, ITreeNode next){
        this.edgeValue = edgeName;
        this.nextAttribute = next;
    }


    public String getEdgeValue() {
        return edgeValue;
    }

    public ITreeNode getNext() {
        return this.nextAttribute;
    }

}
