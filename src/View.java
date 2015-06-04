import generateValues.ReturnData;
import graphicComponent.GraphComponent;
import pageView.PageViewComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by USER on 17.05.15.
 */
public class View extends JFrame {
    private JButton generateButton;
    private ReturnData data;
    private JTextField numOfArrays = new JTextField(20);
    private PageViewComponent tableView;
    private GraphComponent graph;

    public View(){
        JFrame frame = new JFrame();
        tableView = new PageViewComponent(new ReturnData()) ;
        Box box = Box.createHorizontalBox();
        Box arrayBox = Box.createVerticalBox();
        box.add(arrayBox);
        arrayBox.add(tableView);
        graph = new GraphComponent();
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
            data.setTableAndGraph(tableView , graph);
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
