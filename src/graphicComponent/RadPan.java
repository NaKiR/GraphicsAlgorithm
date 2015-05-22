package graphicComponent;

/**
 * Created by USER on 18.05.15.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RadPan extends JPanel implements ActionListener
{
    private JRadioButton jrb1,jrb2,jrb3;
    private ButtonGroup bg;
    PaintGraph pg;
    public void actionPerformed(ActionEvent e)
    {
// Проверяется источник генерации события, и в зависимости от него задается вид
// графика функции
        if (e.getSource().equals(jrb1)){
            pg.repaint();// перерисовка графика функции
        }
        if (e.getSource().equals(jrb2)) {
            pg.repaint();
        }
        if (e.getSource().equals(jrb3)) {
            pg.repaint();
        }
    }
    public RadPan(PaintGraph p)
    {
        setLayout(new FlowLayout());
        pg=p ;
        jrb1 = new JRadioButton("Линия",true);
        jrb2 = new JRadioButton("Парабола",false);
        jrb3 = new JRadioButton("Гипербола",false);
        jrb1.addActionListener(this);
        jrb2.addActionListener(this);
        jrb3.addActionListener(this);
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        bg.add(jrb3);
        add(jrb1);
        add(jrb2);
        add(jrb3);
    }
}
