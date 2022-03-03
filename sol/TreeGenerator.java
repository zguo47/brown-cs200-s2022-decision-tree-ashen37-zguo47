package sol;

import src.*;
import sol.ITreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */
public class TreeGenerator implements ITreeGenerator<Dataset> {
    public ITreeNode tree;

    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {

/*
        String currentAttributeName = trainingData.randomAttribute(targetAttribute);


        ArrayList<String> usedAtt = new ArrayList<>();
        usedAtt.add(currentAttributeName);

        System.out.println(" attribute " + currentAttributeName);

        String defaultValue = trainingData.getDefaultVal(targetAttribute);

        ArrayList<Edge> nextEdges = new ArrayList<>();
        System.out.println(trainingData.getAttributeValList(currentAttributeName));
        for (String v : trainingData.getAttributeValList(currentAttributeName)) {
            */
/*                Dataset newData = this.filterDataset(v, currentAttributeName);*//*

            */
/*Dataset newData = newData1.filterDataset(v, targetAttribute);*//*

            */
/*System.out.println(newData.getNextAttributes(currentAttributeName, targetAttribute));*//*

*/
/*                if (newData.getNextAttributes(currentAttributeName, targetAttribute).isEmpty()) {

                    String decisionName = newData.getDataObjects().get(0).getAttributeValue(targetAttribute);
                    nextEdges.add(new Edge(v, new Leaf(decisionName)));

                }*//*

            System.out.println(currentAttributeName + " with value " + v);
            Dataset newData = new Dataset(trainingData.removeAttLs(usedAtt), trainingData.filterRows(v, currentAttributeName));
            boolean x = newData.getAttributeList().isEmpty();
            if (newData.isDistinctAttValues(targetAttribute) || x){
                nextEdges.add(new Edge(v, new Leaf(newData.getDefaultVal(targetAttribute))));
                System.out.println(" Leaf value: " + newData.getDefaultVal(targetAttribute));
            }else {
                System.out.println(" new node: ");

                nextEdges.add(new Edge(v, newData.generateRecursion(targetAttribute)));
            }
        }
*/

        this.tree = trainingData.generateRecursion(targetAttribute);

    }

/*    public void debugTree(ITreeNode tree, int level){
        System.out.println(level);
        System.out.println(tree.getNodeAttName());
        ArrayList<Edge> lsOfEdges = tree.getLsOfEdge();
        if (lsOfEdges != null){
            for (Edge e : lsOfEdges){
                System.out.println(e.getEdgeValue());
                debugTree(e.getNext(), level++);
            }
        }
    }*/


/*        if (!trainingData.getNextAttributes(trainingData.filterDataset(
                r.getAttributeValue(firstAttribute)), firstAttribute).isEmpty()) {
            Node currPrevNode = new Node(firstAttribute);
            lsOfAtt = trainingData.getNextAttributes(trainingData.filterDataset(
                    r.getAttributeValue(firstAttribute)), firstAttribute);
            Node currNextNode = new Node(lsOfAtt.get(0));
            Edge e = new Edge(currPrevNode, r.getAttributeValue(lsOfAtt.get(0)), currNextNode);
            decisionTree.add(e);
            return this.generateRecursion(lsOfAtt.get(0), targetAttribute, lsOfAtt, decisionTree, trainingData, r);
        } else {
            Node currPrevNode = new Node(firstAttribute);
            Leaf thisDecision = new Leaf(r.getAttributeValue(targetAttribute));
            Edge e = new Edge(currPrevNode, r.getAttributeValue(firstAttribute), thisDecision);
            decisionTree.add(e);
        }
        return decisionTree;*/


/*    public void generateBranch(String attributeName, Dataset trainingData, String targetAttribute){
        List<String> lsOfAtt = trainingData.getAttributeList();
        List<Row> lsOfOb = trainingData.getDataObjects();

*//*        int indexOfAtt = 0;
        String firstAttName = lsOfAtt.get(indexOfAtt);
        String nextAttName = lsOfAtt.get(indexOfAtt+1);
        Node firstAtt = new Node(firstAttName);
        Node nextAtt = new Node(nextAttName);
        Row row = lsOfOb.get(indexOfAtt);
        Edge e = new Edge(firstAtt, row.getAttributeValue(firstAttName), nextAtt);*//*

        ArrayList<Edge> lsOfEdges = new ArrayList<>();
        for (Row r : lsOfOb){
            String temporary = r.getAttributeValue(attributeName);
            if (lsOfEdges.contains(temporary) == false){
                Node att = new Node(attributeName);
                Edge edge = new Edge(att, temporary);
                lsOfEdges.add(edge);
            }
        }
    }*/

    public ITreeNode getTree(){
        return this.tree;
    }

    @Override
    public String getDecision(Row datum) {

/*        String currAtt = this.tree.getNodeAttName();
        String currAttVal = datum.getAttributeValue(currAtt);

        List<Edge> lsOfEdge = this.tree.getLsOfEdge();

        for (Edge e : lsOfEdge){
            if (e.getEdgeValue().equals(currAttVal)){
                this.tree = e.getNext();
                return this.getDecision(datum);
            }
        }*/

        return this.tree.getDecision(datum);


/*        for (String currAtt : rowAtt) {
            if (this.tree.getNodeAttName() == currAtt) {
                List<Edge> lsOfEdges = this.tree.getLsOfEdge();
                String valOfAtt = datum.getAttributeValue(currAtt);
                for (Edge edge : lsOfEdges) {
                    if (valOfAtt == edge.getEdgeValue()) {
                        // cut row
                        edge.getNext().getDecision(*//*smaller row*//*);
                    } else {
                        return this.tree.getDecision(datum);
                    }
                }
            }
        }*/

    }
    // TODO: Implement methods declared in ITreeGenerator interface!

/*    @Test
    public void testGenerateTree(){
        // creates a Dataset out of a CSV file
        List<Row> dataObjects = DecisionTreeCSVParser.parse("testing.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        // builds a TreeGenerator object and generates a tree for "foodType"
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(training, "foodType");

        System.out.println(generator.getTree());
    }*/
}
