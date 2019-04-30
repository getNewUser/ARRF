package com.company;

import weka.core.FastVector;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

import static com.company.Main.split;

public class Scan extends Thread{
    private ArrayList<String> selectedElements;
    private ArrayList<String> results;
    private int[] selectedElementsIndeces;
    private JList listOfElements;
    private JList listOfPushedElements;
    private JTextField textField1;
    private JProgressBar progerssBar;
    private JFrame jFrame;

    public Scan(ArrayList<String> selectedElements, int[] selectedElementsIndeces, JList listOfElements, JList listOfPushedElements, JTextField textField1, JProgressBar progerssBar, JFrame jFrame) {
        this.selectedElements = selectedElements;
        this.selectedElementsIndeces = selectedElementsIndeces;
        this.listOfElements = listOfElements;
        this.listOfPushedElements = listOfPushedElements;
        this.textField1 = textField1;
        this.progerssBar = progerssBar;
        this.results = new ArrayList<>();
        this.jFrame = jFrame;
    }

    public Scan() {
    }

    public void scan() throws IOException {
        selectedElementsIndeces = listOfElements.getSelectedIndices();
        for(int i = 0; i<selectedElementsIndeces.length; i++){
            selectedElementsIndeces[i] -= 2;
        }
        DefaultListModel defaultListModel = new DefaultListModel();
        double startTime = 0;
        for(String string : selectedElements){
            defaultListModel.addElement(string);
        }
        listOfPushedElements.setModel(defaultListModel);
        for(int integer : selectedElementsIndeces){
            System.out.println(integer);
        }
        BufferedReader input = new BufferedReader(new FileReader(textField1.getText()));
        String last, line;
        last = "";

        while ((line = input.readLine()) != null) {
            last = line;
        }
        System.out.println(last + ".");

        String[] firstLine = split(last);

        int counter = 0;
        int progerssBarValue = 1;
        File file = new File("C:\\Users\\80085\\IdeaProjects\\ARFF\\arff");
        for(File temp : file.listFiles()) {
            counter++;
            if(counter >= 399){
                progerssBarValue++;
                System.out.println(progerssBarValue);
                counter = 0;
                progerssBar.setValue(progerssBarValue);

            }
            input = new BufferedReader(new FileReader(temp));
            last = "";

            while ((line = input.readLine()) != null) {
                last = line;
            }

            String[] secondLine = split(last);

            int[] copyOfSelectedElementsIndeces = selectedElementsIndeces;

            startTime = System.nanoTime();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < copyOfSelectedElementsIndeces.length; i++) {
                        if (firstLine[copyOfSelectedElementsIndeces[i]].equals(secondLine[copyOfSelectedElementsIndeces[i]])) {
//                            System.out.println(firstLine[copyOfSelectedElementsIndeces[i]] + " == " +secondLine[copyOfSelectedElementsIndeces[i]]);
                            results.add(firstLine[copyOfSelectedElementsIndeces[i]]);
                        }
                    }
                }

            });
        }
        double endTime = System.nanoTime();
        double timeElapsed = (endTime - startTime) / 1000000000;
        System.out.println("Operation took: " + timeElapsed);
        writeToFile();

        System.out.println("done");
    }

    public void writeToFile() throws IOException {
        PrintWriter writer = new PrintWriter("results.arff", "UTF-8");
        int counter = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < listOfPushedElements.getModel().getSize(); i++){
//            System.out.println(string + " " + results.get(counter) + "\n");
////            stringBuilder.append(string + " " + results.get(counter) + "\n");
//            writer.println(string + " " + results.get(counter));
            writer.write(listOfPushedElements.getModel().getElementAt(i).toString() +"\n");
        }
        writer.write("\n@data\n\n");
        writer.write("(");
        for(String string : results){
            writer.write(string +",");
        }
        writer.write(")");
        writer.close();
    }



    @Override
    public void run() {
        try {
            scan();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
