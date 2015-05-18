package graphicComponent;

/**
 * Created by USER on 18.05.15.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButPan extends JPanel implements ActionListener
{
    private JButton jbt1;
    private JButton jbt2;
    private JButton jbt7;
    private JButton jbt8;
    private PaintGraph graphic;
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(jbt1))
        {
            if( graphic.getNy()== 5 ) jbt2.setEnabled( true );
            graphic.setNy(graphic.getNy() + 5);
            graphic.repaint();
        }
        if (e.getSource().equals(jbt2))
        {
            graphic.setNy(graphic.getNy() - 5);
            graphic.repaint();
            if(graphic.getNy() == 5) jbt2.setEnabled(false);
        }

        if (e.getSource().equals(jbt7))
        {
            if(graphic.getHx() >=0.01) jbt8.setEnabled(true);
            graphic.setHx( graphic.getHx()+(float)0.01);
            graphic.repaint();
            if(graphic.getHx() >= 1) jbt7.setEnabled(false);
        }
        if (e.getSource().equals(jbt8))
        {
            if(graphic.getHx() <=1) jbt7.setEnabled(true);
            graphic.setHx( graphic.getHx()-(float)0.01);
            graphic.repaint();
            if(graphic.getHx() <=0.01) jbt8.setEnabled(false);
        }
    }
    public ButPan(PaintGraph p)
    {
        setLayout(new GridLayout(8,1));// Установка табличного менеджера размещения
        graphic = p ;
        jbt1 = new JButton("Scale -");
        jbt2 = new JButton("Scale +");
        jbt7 = new JButton("hx+");
        jbt8 = new JButton("hx-");
        jbt1.addActionListener(this);
        jbt2.addActionListener(this);
        jbt7.addActionListener(this);
        jbt8.addActionListener(this);
        add(jbt1);
        add(jbt2);
        add(jbt7);
        add(jbt8);
    }
}
