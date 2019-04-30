/*
 * Created by JFormDesigner on Tue Apr 16 10:55:44 EEST 2019
 */

package com.company;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;

import static com.company.Main.main;
import static com.company.Main.split;

/**
 * @author Vilmis
 */
public class MainWindow2 extends JFrame {
    public int[] selectedElementsIndeces;

    public MainWindow2() {
        initComponents();
        progressBar1.setMinimum(0);
        progressBar1.setMaximum(10);
    }

    private void submitDirectoryButtonMouseClicked(MouseEvent e) {
        ArffLoader loader = new ArffLoader();
        DefaultListModel listModel = new DefaultListModel();

        try {
            loader.setSource(new File(textField1.getText()));
            Instances data = loader.getDataSet();

            System.out.println("\nHeader of dataset:\n");

            String result = new Instances(data, 0).toString();
            String results[] = result.split("\n");
            for (String string : results) {
                listModel.addElement(string);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        listOfElements.setModel(listModel);

    }

    private void pushElementsButtonMouseClicked(MouseEvent e) throws IOException {
        ArrayList<String> selectedElements = (ArrayList)listOfElements.getSelectedValuesList();
        Scan scan = new Scan(selectedElements,selectedElementsIndeces,listOfElements,listOfPushedElements,textField1, progressBar1, this);
        scan.start();

    }






    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Vilmis
        textField1 = new JTextField();
        submitDirectoryButton = new JButton();
        scrollPane1 = new JScrollPane();
        listOfElements = new JList();
        scrollPane2 = new JScrollPane();
        listOfPushedElements = new JList();
        pushElementsButton = new JButton();
        progressBar1 = new JProgressBar();

        //======== this ========
        Container contentPane = getContentPane();

        //---- submitDirectoryButton ----
        submitDirectoryButton.setText("text");
        submitDirectoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                submitDirectoryButtonMouseClicked(e);
            }
        });

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(listOfElements);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(listOfPushedElements);
        }

        //---- pushElementsButton ----
        pushElementsButton.setText(">>");
        pushElementsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    pushElementsButtonMouseClicked(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(textField1)
                            .addGap(18, 18, 18)
                            .addComponent(submitDirectoryButton))
                        .addComponent(progressBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(pushElementsButton)
                            .addGap(28, 28, 28)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(57, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(submitDirectoryButton)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(241, 241, 241)
                            .addComponent(pushElementsButton)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                    .addComponent(progressBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(40, 40, 40))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Vilmis
    private JTextField textField1;
    private JButton submitDirectoryButton;
    private JScrollPane scrollPane1;
    private JList listOfElements;
    private JScrollPane scrollPane2;
    private JList listOfPushedElements;
    private JButton pushElementsButton;
    private JProgressBar progressBar1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
