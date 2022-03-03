package sol;

import src.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */
public class Dataset implements IDataset {
    private List<String> lsName;
    private List<Row> rows;


    public Dataset(List<String> attributeList, List<Row> dataObjects){
        this.rows = dataObjects;
        this.lsName = attributeList;
    }

    @Override
    public List<String> getAttributeList() {
        return this.lsName;
    }

    @Override
    public List<Row> getDataObjects() {
        return this.rows;
    }

    public List<Row> filterRows(String attributeValue, String attributeName){
        ArrayList<Row> newRows = new ArrayList<>();

        for (Row r : rows) {
            if (r.getAttributeValue(attributeName).equals(attributeValue)){
                newRows.add(r);
            }
/*            for (String s : lsName) {
                if (r.getAttributeValue(s).equals(attributeValue)){
                    newRows.add(r);
                }
            }*/
        }
        return newRows;
    }

    public List<String> removeAttLs(ArrayList<String> attLs){
        ArrayList<String> newLs = new ArrayList<>();
        for (String s : this.lsName){
            if (!attLs.contains(s)){
                newLs.add(s);
            }
        }
        return newLs;
    }

    public boolean isDistinctAttValues(String attName){
        ArrayList<String> lsOfAttVal = new ArrayList<>();
        for (Row r : rows){
            lsOfAttVal.add(r.getAttributeValue(attName));
        }
/*        for (Row r : rows){
            if (!lsOfAttVal.contains(r.getAttributeValue(attName))) {
                lsOfAttVal.add(r.getAttributeValue(attName));
            }else{
                return false;
            }
        }
        return true;*/
        String s = lsOfAttVal.get(0);
        for (int i = 1; i < lsOfAttVal.size(); i++){
            if (!lsOfAttVal.get(i).equals(s)){
                return true;
            }else{
                i++;
            }
        }
        return false;
    }


    public ArrayList<String> getNextAttributes(String prevAttributeName){
        ArrayList<String> newLsOfAtt = new ArrayList<>();
        for (String s : this.lsName){
            if (!s.equals(prevAttributeName) && this.isDistinctAttValues(s)){
                newLsOfAtt.add(s);
            }
        }
        return newLsOfAtt;
    }

    public ArrayList<String> getAttributeValList(String attName){
        ArrayList<String> lsOfVal = new ArrayList<>();
        List<Row> lsOfRows = this.getDataObjects();
        for (Row r : lsOfRows){
            if (!lsOfVal.contains(r.getAttributeValue(attName))){
                lsOfVal.add(r.getAttributeValue(attName));
            }
        }
        return lsOfVal;
    }

    public int maxIndex(int[] arr){
        int integer = arr[0];
        int index = 0;
        for (int i=0; i < arr.length; i++){
            if (arr[i] > integer){
                integer = arr[i];
                index = i;
            }
        }
        return index;
    }

    public String getDefaultVal(String targetAttribute){
        List<Row> rows = this.getDataObjects();
        List<String> lsOfVal= this.getAttributeValList(targetAttribute);

        int[] intArray = new int[lsOfVal.size()];

        for (int i = 0; i < lsOfVal.size(); i++){
            for (Row r : rows){
                if (r.getAttributeValue(targetAttribute).equals(lsOfVal.get(i))){
                    intArray[i] ++;
                }
            }
        }

        return lsOfVal.get(this.maxIndex(intArray));

    }


    public ITreeNode generateRecursion (String targetAttribute) {
/*        ArrayList<Edge> nextEdges = new ArrayList<>();

        if (!this.isDistinctAttValues(targetAttribute) || this.getNextAttributes(targetAttribute).isEmpty()){

            nextEdges.add(new Edge(, new Leaf(this.getDefaultVal(targetAttribute))));

        }else {*/

/*            List<String> lsOfAtt = this.getNextAttributes(null, targetAttribute);*/


            Random random = new Random();
            int upperBound = this.lsName.size();
            int randomNum = random.nextInt(upperBound);

            ArrayList<String> usedAtt = new ArrayList<>();
            usedAtt.add(targetAttribute);


            String currentAttributeName = this.lsName.get(randomNum);
            usedAtt.add(currentAttributeName);

            System.out.println(" attribute " + currentAttributeName);

            String defaultValue = this.getDefaultVal(targetAttribute);

            ArrayList<Edge> nextEdges = new ArrayList<>();
            System.out.println(this.getAttributeValList(currentAttributeName));
            for (String v : this.getAttributeValList(currentAttributeName)) {
/*                Dataset newData = this.filterDataset(v, currentAttributeName);*/
                /*Dataset newData = newData1.filterDataset(v, targetAttribute);*/
                /*System.out.println(newData.getNextAttributes(currentAttributeName, targetAttribute));*/
/*                if (newData.getNextAttributes(currentAttributeName, targetAttribute).isEmpty()) {

                    String decisionName = newData.getDataObjects().get(0).getAttributeValue(targetAttribute);
                    nextEdges.add(new Edge(v, new Leaf(decisionName)));

                }*/
                System.out.println(currentAttributeName + " with value " + v);
                Dataset newData = new Dataset(this.removeAttLs(usedAtt), this.filterRows(v, currentAttributeName));
                if (!newData.isDistinctAttValues(targetAttribute) || newData.lsName.isEmpty()){
                    nextEdges.add(new Edge(v, new Leaf(newData.getDefaultVal(targetAttribute))));
                    System.out.println(" Leaf value: " + newData.getDefaultVal(targetAttribute));
                }else {
                    System.out.println(" new node: ");

                    nextEdges.add(new Edge(v, newData.generateRecursion(targetAttribute)));
                }
            }

        return new Node(currentAttributeName, defaultValue, nextEdges);

       /* if (this.isDistinctAttValues(firstAttribute) &&
                !this.getNextAttributes(firstAttribute, targetAttribute).isEmpty()) {
            ArrayList<String> tempAttVal = this.getAttributeValList(firstAttribute);
            for (String v : tempAttVal) {
                List<String> newLsOfAtt = this.getNextAttributes(firstAttribute, targetAttribute);

                Random random = new Random();
                int upperBound = newLsOfAtt.size();
                int randomNum = random.nextInt(upperBound);

                this.filterDataset(v);

                String nextAttribute = newLsOfAtt.get(randomNum);

                Node nextNode = new Node(nextAttribute,
                        this.generateRecursion(nextAttribute, targetAttribute, nextEdges).getLsOfEdge());
                Edge e = new Edge(v, nextNode);
                nextEdges.add(e);
                return this.generateRecursion(nextAttribute, targetAttribute, nextEdges);
            }

        } else if (this.isDistinctAttValues(firstAttribute) &&
                this.getNextAttributes(firstAttribute, targetAttribute).isEmpty()){

            ArrayList<String> tempAttVal = this.getAttributeValList(firstAttribute);
            for (String v : tempAttVal) {
                this.filterDataset(v);

                Leaf decision = new Leaf(this.getDataObjects().get(0).getAttributeValue(targetAttribute));
                Edge e = new Edge(v, decision);
                nextEdges.add(e);
            }
            Node n = new Node(firstAttribute, nextEdges);
            return n;

        }else if (!this.isDistinctAttValues(firstAttribute)){
            String v = this.getDataObjects().get(0).getAttributeValue(firstAttribute);
            Leaf decision = new Leaf(this.getDataObjects().get(0).getAttributeValue(targetAttribute));
            Edge e = new Edge(v, decision);
            nextEdges.add(e);

            Node n = new Node(firstAttribute, nextEdges);
            return n;
        }*/
    }




    @Override
    public int size() {
        return rows.size();
    }
    // TODO: Implement methods declared in IDataset interface!
}
