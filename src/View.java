import generateValues.ReturnData;
import graphicComponent.DirPan;
import graphicComponent.PaintGraph;
import pageView.PageViewComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by USER on 17.05.15.
 */
public class View extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private ReturnData data;
    private JButton generateButton;
    private JTextField numOfArrays = new JTextField(3);
    private PageViewComponent tableView;
    private DirPan graph;

    public View(){
        JFrame frame = new JFrame();
      //  data = new ReturnData();
        tableView = new PageViewComponent(new ReturnData()) ;
        Box box = Box.createHorizontalBox();
        Box arrayBox = Box.createVerticalBox();
        box.add(arrayBox);
        arrayBox.add(tableView);
        graph = new DirPan();
        box.add(graph);
        generateButton = new JButton("Сгенерировать");
        JPanel panelArray = new JPanel();
        arrayBox.add(panelArray);
        numOfArrays.setText("5");
        panelArray.add(generateButton);
        panelArray.add(numOfArrays);
        frame.add(box);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initListeners();
    }
    private void initListeners(){
        generateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            data = new ReturnData();
            graph.setData(data);
            tableView.setData(data);
            data.getNumOfArray(Integer.parseInt(numOfArrays.getText()));
            data.start();
            tableView.updateModel();
            graph.repaint();
        }
    });
    }
}
