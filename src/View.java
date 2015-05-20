import generateValues.ReturnData;
import graphicComponent.DirPan;
import pageView.PageViewComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Created by USER on 17.05.15.
 */
public class View extends JFrame implements Runnable {
    private DefaultTableModel model;
    private JTable table;
    private ReturnData data;

    public View(){

    }
    public void run(){
        JFrame frame = new JFrame();
        data = new ReturnData();
        PageViewComponent tableView = new PageViewComponent(data) ;
        Box box = Box.createHorizontalBox();
        box.add(tableView);
        DirPan graph = new DirPan(data);
        box.add(graph);
        frame.add(box);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
