import generateValues.ReturnData;

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
        JPanel panel = new JPanel();
        data = new ReturnData(5);
        model = new DefaultTableModel();
        table = new JTable();
        model.addColumn("Элементы");
        model.addColumn("Время");
        table.setModel(model);
        List< Integer[]> rows;
        rows = data.getData();
        for(int currentRow = 0; currentRow < rows.size(); currentRow++){
            model.addRow(rows.get(currentRow));
        }
        Box box = Box.createVerticalBox();
        panel.add(new JScrollPane(table));
        box.add(panel);
        add(box);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
