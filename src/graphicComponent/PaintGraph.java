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
    private String nameX = "Время";
    private String nameY = "Деньги";

    public PaintGraph(ReturnData d)
    {
        this.data = d;
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
//        g.fillOval(indent , indent + lenthX, 5, 5);
//        //Разбиваем каждую ось на две части для удобства переноса центра координат
//        g.drawLine( ( int ) ( lenthX * kx + indent ) , oyn ,
//                ( int ) ( lenthX * kx+ indent ) , lenthY + oyn );
//        // Стрелки
//        g.drawLine( ( int ) ( lenthX * kx + indent) , oyn ,
//                ( int ) ( lenthX * kx + indent ) - 3 , oyn + 10 );
//        g.drawLine( ( int ) ( lenthX * kx + indent) , oyn ,
//                ( int ) ( lenthX * kx + indent) + 3 , oyn + 10 );
//        // Надпись
//        g.drawString( "Y" , ( int ) ( lenthX * kx + indent) - 10 , oyn + 10 );
//        g.drawString( "0" , ( int ) ( lenthX * kx + indent ) - 10 , ( int) ( lenthY * ky+ oyn) + 10 );
//        //Деления
//        int l1 = ( int ) (lenthY*ky);
//        l2 = lenthY - l1;
//        int k1 = ( int ) l1 / valOfDivision ;
//        int k2 = ( int ) l2 / valOfDivision ;
//        for ( int i = 1; i < k1 + 1 ; i++)
//        {
//            g.drawLine( ( int )(lenthX * kx - 2 + indent) , l1 - valOfDivision+ oyn ,
//                    ( int ) ( lenthX * kx + 2+ indent ) , l1 - valOfDivision+ oyn );
//            l1 = l1 - valOfDivision ;
//        }
//        l1 = lenthY - l2;
//        for ( int i = 1; i < k2 + 1 ; i++)
//        {
//            g.drawLine( ( int )(lenthX * kx - 2 + indent) , l1 + valOfDivision+ oyn ,
//                    ( int )(lenthX * kx + 2+ indent ) , l1 + valOfDivision+ oyn );
//            l1 = l1 + valOfDivision ;
//        }
//        // Ось Х
//        g.drawLine( indent , ( int ) ( lenthY * ky + oyn) , lenthX + indent,  ( int ) ( lenthY * ky + oyn)  );
//        g.drawLine( lenthX+ indent , ( int ) ( lenthY * ky + oyn ) , lenthX+ indent - 10 ,
//                ( int ) ( lenthY * ky + oyn) - 3 );
//        g.drawLine( lenthX + indent, ( int ) ( lenthY * ky + oyn) , lenthX+ indent - 10 ,
//                ( int ) ( lenthY * ky+ oyn ) + 3 );
//        // Надпись
//        g.drawString( "Х" , lenthX+ oyn -10 , ( int ) ( lenthY * ky+ oyn ) - 10 );
//        // Деления
//        l1 = ( int ) ( lenthX * kx );
//        l2 = lenthX - l1;
//        k1 = ( int ) l1 / valOfDivision ;
//        k2 = ( int ) l2 / valOfDivision ;
//        for ( int i = 1; i <  k1 + 1 ; i++)
//        {
//            g.drawLine( l1 - valOfDivision + indent ,( int ) ( lenthY * ky - 2+ oyn) ,
//                    l1 - valOfDivision + indent , ( int ) ( lenthY * ky + 2 + oyn )  );
//            l1 = l1 - valOfDivision ;
//        }
//        l1 = lenthX - l2;
//        for ( int i = 1; i < k2 + 1 ; i++)
//        {
//            g.drawLine( l1 + valOfDivision+ indent ,( int )(lenthY* ky - 2+ oyn) ,
//                    l1 + valOfDivision + indent , ( int )(lenthY * ky + 2+ oyn )  );
//            l1 = l1 + valOfDivision ;
//        }
        // Выбор метода для рисования функции
//        switch (sw)
//        {
//            case 1 :
//                funcLine(g);
//                break;
//
//                  }
    }

    // группа методов рисующих графики функций
    // Метод рисующий линию

    void funcLine(Graphics g)
    {
        xln = ( lenthX - l2 ) ;
        xk = 0 ;
        yg = 0;
        while(   ( xk + hx )  * valOfDivision < xln  &&  ( xk + hx )* valOfDivision < lenthY - lenthY * ky )
        {
            yg =  xk  ;
            g.drawLine( ( int ) ( xln - xk * valOfDivision + indent) ,
                    ( int ) ( lenthY * ky + yg * valOfDivision + oyn),
                    ( int ) ( xln -  ( xk + hx ) * valOfDivision + indent),
                    ( int ) ( lenthY * ky + ( xk + hx )  * valOfDivision)+ oyn) ;
            xk = xk + hx ;
        }
        xk = 0 ;
        yg = 0;
        while(   ( xk + hx )  * valOfDivision < l2 &&  ( xk + hx )  * valOfDivision < lenthY * ky )
        {
            yg =  xk  ;
            g.drawLine( ( int ) ( xln + xk * valOfDivision + indent) ,
                    ( int ) ( lenthY * ky - yg * valOfDivision + oyn ),
                    ( int ) ( xln +  ( xk + hx ) * valOfDivision + indent),
                    ( int ) ( lenthY * ky - ( xk + hx )  * valOfDivision)+ oyn) ;
            xk = xk + hx ;
        }
    }

    // Метод рисующий параболу

    // группа getXXX(), setXXX() - методов
    public int getNx() {
        return nx;
    }
    public void setNx(int nx) {
        this.nx = nx;
    }
    public int getValOfDivision() {
        return valOfDivision;
    }
    public void setValOfDivision(int valOfDivision) {
        this.valOfDivision = valOfDivision;
    }
    public float getKy() {
        return ky;
    }
    public void setKy(float ky) {
        this.ky = ky;
    }
    public float getKx() {
        return kx;
    }
    public void setKx(float kx) {
        this.kx = kx;
    }
    public float getHx() {
        return hx;
    }
    public void setHx(float hx) {
        this.hx = hx;
    }
    public int getLenthX() {
        return lenthX;
    }
    public void setLenthX(int lenthX) {
        this.lenthX = lenthX;
    }
    public int getLenthY() {
        return lenthY;
    }
    public void setLenthY(int lenthY) {
        this.lenthY = lenthY;
    }
    public int getSw() {
        return sw;
    }
    public void setSw(int sw) {
        this.sw = sw;
    }
    public int getOyn() {
        return oyn;
    }
    public void setOyn(int oyn) {
        this.oyn = oyn;
    }
    public int getIndent() {
        return indent;
    }
    public void setIndent(int indent) {
        this.indent = indent;
    }

    public String getNameX() {
        return nameX;
    }

    public void setNameX(String nameX) {
        this.nameX = nameX;
    }

    public String getNameY() {
        return nameY;
    }

    public void setNameY(String nameY) {
        this.nameY = nameY;
    }
}