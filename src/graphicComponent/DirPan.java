package graphicComponent;

/**
 * Created by USER on 18.05.15.
 */
import generateValues.ReturnData;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class DirPan extends JPanel
{
    private PaintGraph pg; // класс вывода графика функции
    private ButPan bp; // класс управляющих масштабом кнопок
    public DirPan(ReturnData d)
    {
        setLayout(new BorderLayout()); // установка менеджера размещения
        pg = new PaintGraph(d); // инициализация класса построения графика функции
         // задание размеров
        JScrollPane scroll = new JScrollPane(pg);
        add(scroll,BorderLayout.CENTER); // задание размещения
        bp = new ButPan(pg); // инициализация класса кнопок масштаба
        add(bp, BorderLayout.WEST);
        //setSize(700, 430); // задание размеров
        // главного окна при закрытии
        setVisible(true);
        //scroll.addMouseMotionListener(new Mo);
    }
}
