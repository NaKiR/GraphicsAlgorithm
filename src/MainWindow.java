import graphic.GraphComponent;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private GraphComponent graph;
    private Function data;
    private MyTableModel tableModel = new MyTableModel();

    public MainWindow() {
        setTitle("График");
        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTable table = new JTable(tableModel);
        for (int i = 0; i < 2; i++) {
            TableColumn tc = table.getColumnModel().getColumn(i);
            tc.setPreferredWidth(50);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(120, 0));
        graph = new GraphComponent();
        add(scrollPane, BorderLayout.WEST);
        addBottomPanel();
        add(graph, BorderLayout.CENTER);
        setVisible(true);
    }

    public void addBottomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JButton zoomIn = new JButton("+");
        panel.add(zoomIn);
        JButton zoomOut = new JButton("-");
        panel.add(zoomOut);
        final JLabel labelA = new JLabel("A: ");
        panel.add(labelA);
        final JTextField enterA = new JTextField();
        panel.add(enterA);
        final JLabel labelB = new JLabel("B: ");
        panel.add(labelB);
        final JTextField enterB = new JTextField();
        panel.add(enterB);
        final JLabel labelX1 = new JLabel("X1: ");
        panel.add(labelX1);
        final JTextField enterX1 = new JTextField();
        panel.add(enterX1);
        final JLabel labelX2 = new JLabel("X2: ");
        panel.add(labelX2);
        final JTextField enterX2 = new JTextField();
        panel.add(enterX2);
        JButton buildDiagram = new JButton("Построить");
        buildDiagram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x1, x2, a, b;
                x1 = new Integer(enterX1.getText());
                x2 = new Integer(enterX2.getText());
                a = new Integer(enterA.getText());
                b = new Integer(enterB.getText());
                data = new Function();
                data.setVar(x1, x2);
                data.setConst(a, b);
                data.setTableAndGraph(tableModel, graph);
                graph.setData(data);
                data.start();
                tableModel.updateModel(data.getData());
                graph.repaint();
            }
        });
        panel.add(buildDiagram);
        add(panel, BorderLayout.SOUTH);
    }
}
