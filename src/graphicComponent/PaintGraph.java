package graphicComponent;

/**
 * Created by USER on 18.05.15.
 */
import generateValues.ReturnData;

import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.util.List;

public class PaintGraph extends JPanel
{
    private int valOfDivision, nx , oyn , indent, lenthY, lenthX, sw , xln , l2;
    private float  kx , ky , hx , yg , xk;
    private ReturnData data;
    private String nameX = "X";
    private String nameY = "Y";

    public PaintGraph()
    {
        valOfDivision = 10;// цена деления  по шкалам
        ky = (float)1; // коэф шкалы по у
        kx = (float)0; // коэф шкалы по x
        oyn = 10; // начальный отступ по y
        indent = 40 ; //начальный отступ по х
        lenthY = 60; // длина оси у
        lenthX = 60; // длина оси х
        // по умолчанию в начале на экран выводится график y=x
        sw = 1; // свич для переключения графика функции
        setBackground(Color.WHITE);
        hx = (float)0.011;//шаг табуляции
        data = new ReturnData();
    }

    public void paint(Graphics g)
    {
       // setSize(100,100);
        super.paintComponent(g);
        List<Integer []> dots = data.getRows();
        //lenthY = valOfDivision * (dots.size() + 2);
        //lenthX = valOfDivision * (dots.size() + 2);
        for(int currentDot = 0; currentDot < dots.size(); currentDot++){
            if(lenthX / valOfDivision < dots.get(currentDot)[0]){
                lenthX = dots.get(currentDot)[0] * valOfDivision;
            }
            if(lenthY/valOfDivision < dots.get(currentDot)[1]/1000){
                lenthY = (dots.get(currentDot)[1]/1000) *valOfDivision;
            }
        }
        for(int currentDot = 0; currentDot < dots.size(); currentDot++){
            Integer dot[] = dots.get(currentDot);
            g.setColor(Color.BLUE);
        g.fillOval(indent + dot[0]* valOfDivision - 2, indent + lenthY - (dot[1]/1000) * valOfDivision, 5, 5);

            if(currentDot !=0){
                g.setColor(Color.RED);
                g.drawLine(indent + dots.get(currentDot - 1)[0]* valOfDivision + 1, indent + lenthY - ( dots.get(currentDot - 1)[1]/1000) * valOfDivision +1 ,
                        indent +  dots.get(currentDot)[0]* valOfDivision + 1 , indent + lenthY - ( dots.get(currentDot)[1]/1000) * valOfDivision +1);
            }
        }
        g.setColor(Color.BLACK);
        setPreferredSize(new Dimension(lenthX + 2 * indent + 2 * valOfDivision, lenthY + 2 * indent));
        g.drawLine(indent, indent, indent, lenthY + indent);
        g.drawLine( indent, lenthY + indent, indent + lenthX + valOfDivision, lenthY + indent);
        g.drawLine(indent, indent, indent - 7, indent + 10);
        g.drawLine(indent, indent, indent + 7, indent + 10);
        g.drawString(nameX, indent, indent - 5);
        g.drawString(nameY , indent + lenthX + valOfDivision , lenthY + indent + 20);
        g.drawLine(indent + lenthX + valOfDivision, lenthY + indent, indent + lenthX + valOfDivision - 10, lenthY + indent + 7);
        g.drawLine(indent + lenthX + valOfDivision, lenthY + indent, indent + lenthX + valOfDivision - 10, lenthY + indent -7);

        Integer numOfSegmentsX = lenthX / valOfDivision;
        Integer numOfSegmentsY = (lenthY / valOfDivision) - 1;

        for(int segment = 1; segment <= numOfSegmentsY; segment++){
            g.drawLine(indent - 3, lenthY + indent - valOfDivision * segment,
                    indent + 3, lenthY + indent - valOfDivision * segment);
        }
        for(int segment = 1; segment <= numOfSegmentsX; segment++){
            g.drawLine(indent +  valOfDivision * segment, lenthY + indent -3,
                    indent +  valOfDivision * segment, lenthY + indent + 3);
        }

        int index;
        index = 10;
        int indentIn;
        indentIn = indent ;

        for(Integer segment = 0; segment <= numOfSegmentsX; segment+= 5){

            if((segment+1)/index != 0){
                index = index * 10;
                indentIn = indentIn - 4;
            }
            g.drawString(segment.toString(),indentIn +  valOfDivision * segment, lenthY + indent + 15 );
        }

        index = 10;
        indentIn = indent - 15;

        for(Integer segment = 5; segment <= numOfSegmentsY; segment+= 5){

            if((segment+1)/index != 0){
                index = index * 10;
                indentIn = indentIn - 7;
            }
            g.drawString(segment.toString(),indentIn, lenthY + indent - valOfDivision * segment + 5);
        }
    }

    public void setData(ReturnData data){
        this.data = data;
    }

    public int getValOfDivision() {
        return valOfDivision;
    }
    public void setValOfDivision(int valOfDivision) {
        this.valOfDivision = valOfDivision;
    }

    public float getHx() {
        return hx;
    }
    public void setHx(float hx) {
        this.hx = hx;
    }
    public void setNameX(String name){
        this.nameX = name;
    }
    public void setNameY(String name){
        this.nameY = name;
    }
}