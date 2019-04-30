package com.company;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            JFrame mainFrame = new JFrame("Main Window");
            MainWindow2 mainWindow2 = new MainWindow2();
            mainFrame.setContentPane(mainWindow2.getContentPane());
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(1000,600);
            mainFrame.setVisible(true);


//

    }
    public static String[] split(String string){
            return string.split(",");
    }
}
