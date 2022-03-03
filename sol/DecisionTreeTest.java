package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.HashMap;
import src.Row;

import javax.xml.crypto.Data;

public class DecisionTreeTest {

    // Constructor for DecisionTreeTest tester class - don't need to add anything in here!
    public DecisionTreeTest() {
    }
    
    @Test
    public void testExample() {
        {
            HashMap<String, String> dish1 = new HashMap<String, String>();
            dish1.put("main", "rice");
            dish1.put("meat", "beef");
            dish1.put("vegetable", "bok choy");
            dish1.put("cuisine", "Chinese");

            HashMap<String, String> dish2 = new HashMap<String, String>();
            dish2.put("main", "rice");
            dish2.put("meat", "shrimp");
            dish2.put("vegetable", "onion");
            dish2.put("cuisine", "Spanish");

            HashMap<String, String> dish3 = new HashMap<String, String>();
            dish3.put("main", "rice");
            dish3.put("meat", "beef");
            dish3.put("vegetable", "kimchi");
            dish3.put("cuisine", "Korean");

            HashMap<String, String> dish4 = new HashMap<String, String>();
            dish4.put("main", "bread");
            dish4.put("meat", "beef");
            dish4.put("vegetable", "kimchi");
            dish4.put("cuisine", "Korean");

            HashMap<String, String> dish5 = new HashMap<String, String>();
            dish5.put("main", "bread");
            dish5.put("meat", "bacon");
            dish5.put("vegetable", "lettuce");
            dish5.put("cuisine", "American");

            HashMap<String, String> dish6 = new HashMap<String, String>();
            dish6.put("main", "bread");
            dish6.put("meat", "beef");
            dish6.put("vegetable", "onion");
            dish6.put("cuisine", "Spanish");

            HashMap<String, String> dish7 = new HashMap<String, String>();
            dish7.put("main", "pasta");
            dish7.put("meat", "shrimp");
            dish7.put("vegetable", "broccoli");
            dish7.put("cuisine", "Italian");

            HashMap<String, String> dish8 = new HashMap<String, String>();
            dish8.put("main", "pasta");
            dish8.put("meat", "bacon");
            dish8.put("vegetable", "onion");
            dish8.put("cuisine", "Italian");

            HashMap<String, String> dish9 = new HashMap<String, String>();
            dish9.put("main", "pasta");
            dish9.put("meat", "beef");
            dish9.put("vegetable", "broccoli");
            dish9.put("cuisine", "Italian");

            Row r1 = new Row(dish1);
            Row r2 = new Row(dish2);
            Row r3 = new Row(dish3);
            Row r4 = new Row(dish4);
            Row r5 = new Row(dish5);
            Row r6 = new Row(dish6);
            Row r7 = new Row(dish7);
            Row r8 = new Row(dish8);
            Row r9 = new Row(dish9);

            ArrayList<String> atts = new ArrayList<>();
            atts.add("main");
            atts.add("meat");
            atts.add("vegetable");
            atts.add("cuisine");

            ArrayList<Row> objects = new ArrayList<>();
            objects.add(r1);
            objects.add(r2);
            objects.add(r3);
            objects.add(r4);
            objects.add(r5);
            objects.add(r6);
            objects.add(r7);
            objects.add(r8);
            objects.add(r9);

            Dataset dishes = new Dataset(atts, objects);

            ArrayList<String> l1 = new ArrayList<>();
            l1.add("main");
            l1.add("meat");
            l1.add("vegetable");
            l1.add("cuisine");
            Assert.assertEquals(l1, dishes.getAttributeList());

            ArrayList<Row> l2 = new ArrayList<>();
            l2.add(r1);
            l2.add(r2);
            l2.add(r3);
            l2.add(r4);
            l2.add(r5);
            l2.add(r6);
            l2.add(r7);
            l2.add(r8);
            l2.add(r9);
            Assert.assertEquals(l2, dishes.getDataObjects());

/*            Assert.assertEquals(2, dishes.filterDataset("Korean", "cuisine").size());
            Assert.assertEquals(3, dishes.filterDataset("bread", "main").size());*/

            Assert.assertEquals(true, dishes.isDistinctAttValues("vegetable"));

            ArrayList<String> l3 = new ArrayList<>();
            l3.add("main");
            l3.add("vegetable");
            l3.add("cuisine");
            Assert.assertEquals(l3, dishes.getNextAttributes("meat"));

            ArrayList<String> l4 = new ArrayList<>();
            l4.add("rice");
            l4.add("bread");
            l4.add("pasta");
            Assert.assertEquals(l4, dishes.getAttributeValList("main"));

            int[] l5 = new int[] {3,9,2,7};
            Assert.assertEquals(1, dishes.maxIndex(l5));

            Assert.assertEquals("Italian", dishes.getDefaultVal("cuisine"));
            Assert.assertEquals("onion", dishes.getDefaultVal("vegetable"));


            Assert.assertEquals(9, dishes.size());

        }
    }
    
    // TODO: Add your tests here!
    
}
