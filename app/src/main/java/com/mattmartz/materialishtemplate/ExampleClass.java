package com.mattmartz.materialishtemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by matthewmartz on 11/11/14.
 */
public class ExampleClass {
    private List<String> exampleNameList;
    private List<String> exampleOtherList;

    private List<Map<String, ?>> examples;

    public ExampleClass() {
        initExample();
    }

    public static Map newExample(String itemname, String itemother) {
        Map example = new HashMap();
        example.put("name", itemname);
        example.put("other", itemother);
        return example;
    }

    private void processExamples() {
        List<String> tempExNames = new ArrayList<String>();
        List<String> tempExOthers = new ArrayList<String>();

        for (int a = 0; a < examples.size(); a++) {
            tempExNames.add(examples.get(a).get("name").toString());
            tempExOthers.add(examples.get(a).get("other").toString());

        }
        exampleNameList = tempExNames;
        exampleOtherList = tempExOthers;
    }

    public List initExample() {
        examples = new ArrayList<Map<String, ?>>();
        examples.add(newExample("Name One", "Something"));
        examples.add(newExample("Name Two", "Recommendation"));
        examples.add(newExample("Name Three", "Something Else"));
        examples.add(newExample("Name Four", "Red"));
        examples.add(newExample("Name Five", "Recommendation"));
        examples.add(newExample("Name Six", "Other"));
        processExamples();
        return examples;
    }

    public int getNameLocation(String exerciseName) {
        int location = -1;

        for (int a = 0; a < examples.size(); a++) {
            if (exerciseName.equals(examples.get(a).get("name").toString())) {
                location = a;
            }
        }
        return location;
    }

    public String getExampleName(int position) {
        return exampleNameList.get(position);
    }

    public String getExampleOther(int position) {
        return exampleOtherList.get(position);
    }

    public int getSize() {
        return examples.size();
    }

    public List<String> getExampleNameList() {
        return exampleNameList;
    }
}
