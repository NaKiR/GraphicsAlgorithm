package graphic;

import Function;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphComponent extends JPanel
{
    private PaintGraph graphic;
    private JScrollPane scrollPane;
    private int startX;
    private int startY;

    public GraphComponent()
    {
        setSize(600,600);
        setLayout(new BorderLayout());
        graphic = new PaintGraph();
        scrollPane = new JScrollPane(graphic);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
        graphic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    startX = e.getX();
                    startY = e.getY();
                    graphic.repaint();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        graphic.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                    scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue() + (startX - e.getX()));
                    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() + (startY - e.getY()));
                    graphic.repaint();
//                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }


    public void setData(Function data){
        graphic.setData(data);
    }
}
