package pageView;


import generateValues.ReturnData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by USER on 11.04.15.
 */
public class PageViewComponent extends JPanel {

    private int currentPage;
    private int numOfRecords;
    private ButtonsPanel buttonsPanel = new ButtonsPanel(this);
    private TableComponent tableComponent;
    private DefaultTableModel model = new DefaultTableModel();
    private JTable table = new JTable(model);

    public PageViewComponent(ReturnData data){
        //this.tableComponent.setData(data);
        currentPage = 1;
        numOfRecords = 10;
        tableComponent = new TableComponent( currentPage, numOfRecords, table);
        Box box = Box.createVerticalBox();
        box.add(new JScrollPane(table));
        box.add(buttonsPanel);
        add(box);
        updateModel();
    }

    public void updateModel(){
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.setPagesAndRecords(tableComponent.getPages(),tableComponent.getNumOfRecords(),currentPage);
        buttonsPanel.updateLabels();
    }

    public void nextPage(){
        if(currentPage < tableComponent.getPages())
            currentPage++;
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.setPagesAndRecords(tableComponent.getPages(),tableComponent.getNumOfRecords(),currentPage);
        buttonsPanel.updateLabels();
    }
    public void prevPage(){
        if(currentPage > 1)
            currentPage--;
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.setPagesAndRecords(tableComponent.getPages(),tableComponent.getNumOfRecords(),currentPage);
        buttonsPanel.updateLabels();
    }
    public void firstPage(){
        currentPage = 1;
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.setPagesAndRecords(tableComponent.getPages(),tableComponent.getNumOfRecords(),currentPage);
        buttonsPanel.updateLabels();
    }
    public void lastPage(){
        currentPage = tableComponent.getPages();
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.setPagesAndRecords(tableComponent.getPages(),tableComponent.getNumOfRecords(),currentPage);
        buttonsPanel.updateLabels();
    }

    public void setNumOfRecords(int numOfRecords){
        this.numOfRecords = numOfRecords;
        currentPage = 1;
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.setPagesAndRecords(tableComponent.getPages(),tableComponent.getNumOfRecords(),currentPage);
        buttonsPanel.updateLabels();
    }

    public void setData(ReturnData data){
        tableComponent.setData(data);
        currentPage = 1;
        tableComponent.updateModel(currentPage, numOfRecords);
        buttonsPanel.setPagesAndRecords(tableComponent.getPages(),tableComponent.getNumOfRecords(),currentPage);
        buttonsPanel.updateLabels();
    }
}
