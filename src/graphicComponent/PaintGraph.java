package graphicComponent;

/**
 * Created by USER on 18.05.15.
 */
import generateValues.ReturnData;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.util.*;
import java.util.List;

public class PaintGraph extends JPanel
{
    private int ny , nx , oyn , oxn , ly , lx , sw , xln , l2;
    private float  kx , ky , hx , yg , xk;
    private ReturnData data;

    public PaintGraph(ReturnData d)
    {
        this.data = d;
        ny = 8;// цена деления  по шкалам
        ky = (float)1; // коэф шкалы по у
        kx = (float)0; // коэф шкалы по x
        oyn = 10; // начальный отступ по y
        oxn = 10 ; //начальный отступ по х
        ly = 600; // длина оси у
        lx = 600; // длина оси х
        // по умолчанию в начале на экран выводится график y=x
        sw = 1; // свич для переключения графика функции
        hx = (float)0.011;//шаг табуляции
    }

    public void paint(Graphics g)
    {
       // setSize(100,100);
        super.paint(g);
        List<Integer []> dots = data.getRows();
        for(int currentDot = 0; currentDot < dots.size(); currentDot++){
            Integer dot[] = dots.get(currentDot);
        g.fillOval( ny + dot[0]*ny, oxn + lx - ny - (dot[1]/1000) *ny + 10, 5, 5);
            if(currentDot !=0){
                g.drawLine(ny + dots.get(currentDot - 1)[0]*ny + 3, oxn + lx - ny - ( dots.get(currentDot - 1)[1]/1000) *ny + 10 + 3,
                        ny +  dots.get(currentDot)[0]*ny + 3, oxn + lx - ny - ( dots.get(currentDot)[1]/1000) *ny + 10 + 3);
            }
        }
        g.fillOval(oxn , oxn + lx, 5, 5);
        //Разбиваем каждую ось на две части для удобства переноса центра координат
        g.drawLine( ( int ) ( lx * kx + oxn ) , oyn ,
                ( int ) ( lx * kx+ oxn ) , ly + oyn );
        // Стрелки
        g.drawLine( ( int ) ( lx * kx + oxn) , oyn ,
                ( int ) ( lx * kx + oxn ) - 3 , oyn + 10 );
        g.drawLine( ( int ) ( lx * kx + oxn) , oyn ,
                ( int ) ( lx * kx + oxn) + 3 , oyn + 10 );
        // Надпись
        g.drawString( "Y" , ( int ) ( lx * kx + oxn) - 10 , oyn + 10 );
        g.drawString( "0" , ( int ) ( lx * kx + oxn ) - 10 , ( int) ( ly * ky+ oyn) + 10 );
        //Деления
        int l1 = ( int ) (ly*ky);
        l2 = ly - l1;
        int k1 = ( int ) l1 / ny ;
        int k2 = ( int ) l2 / ny ;
        for ( int i = 1; i < k1 + 1 ; i++)
        {
            g.drawLine( ( int )(lx * kx - 2 + oxn) , l1 - ny+ oyn ,
                    ( int ) ( lx * kx + 2+ oxn ) , l1 - ny+ oyn );
            l1 = l1 - ny ;
        }
        l1 = ly - l2;
        for ( int i = 1; i < k2 + 1 ; i++)
        {
            g.drawLine( ( int )(lx * kx - 2 + oxn) , l1 + ny+ oyn ,
                    ( int )(lx * kx + 2+ oxn ) , l1 + ny+ oyn );
            l1 = l1 + ny ;
        }
        // Ось Х
        g.drawLine( oxn , ( int ) ( ly * ky + oyn) , lx + oxn,  ( int ) ( ly * ky + oyn)  );
        g.drawLine( lx+ oxn , ( int ) ( ly * ky + oyn ) , lx+ oxn - 10 ,
                ( int ) ( ly * ky + oyn) - 3 );
        g.drawLine( lx + oxn, ( int ) ( ly * ky + oyn) , lx+ oxn - 10 ,
                ( int ) ( ly * ky+ oyn ) + 3 );
        // Надпись
        g.drawString( "Х" , lx+ oyn -10 , ( int ) ( ly * ky+ oyn ) - 10 );
        // Деления
        l1 = ( int ) ( lx * kx );
        l2 = lx - l1;
        k1 = ( int ) l1 / ny ;
        k2 = ( int ) l2 / ny ;
        for ( int i = 1; i <  k1 + 1 ; i++)
        {
            g.drawLine( l1 - ny + oxn ,( int ) ( ly * ky - 2+ oyn) ,
                    l1 - ny + oxn , ( int ) ( ly * ky + 2 + oyn )  );
            l1 = l1 - ny ;
        }
        l1 = lx - l2;
        for ( int i = 1; i < k2 + 1 ; i++)
        {
            g.drawLine( l1 + ny+ oxn ,( int )(ly* ky - 2+ oyn) ,
                    l1 + ny + oxn , ( int )(ly * ky + 2+ oyn )  );
            l1 = l1 + ny ;
        }
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
        xln = ( lx - l2 ) ;
        xk = 0 ;
        yg = 0;
        while(   ( xk + hx )  * ny < xln  &&  ( xk + hx )* ny < ly - ly * ky )
        {
            yg =  xk  ;
            g.drawLine( ( int ) ( xln - xk * ny+ oxn ) ,
                    ( int ) ( ly * ky + yg * ny + oyn),
                    ( int ) ( xln -  ( xk + hx ) * ny + oxn ),
                    ( int ) ( ly * ky + ( xk + hx )  * ny )+ oyn) ;
            xk = xk + hx ;
        }
        xk = 0 ;
        yg = 0;
        while(   ( xk + hx )  * ny < l2 &&  ( xk + hx )  * ny < ly * ky )
        {
            yg =  xk  ;
            g.drawLine( ( int ) ( xln + xk * ny+ oxn ) ,
                    ( int ) ( ly * ky - yg * ny+ oyn ),
                    ( int ) ( xln +  ( xk + hx ) * ny+ oxn ),
                    ( int ) ( ly * ky - ( xk + hx )  * ny  )+ oyn) ;
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
    public int getNy() {
        return ny;
    }
    public void setNy(int ny) {
        this.ny = ny;
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
    public int getLx() {
        return lx;
    }
    public void setLx(int lx) {
        this.lx = lx;
    }
    public int getLy() {
        return ly;
    }
    public void setLy(int ly) {
        this.ly = ly;
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
    public int getOxn() {
        return oxn;
    }
    public void setOxn(int oxn) {
        this.oxn = oxn;
    }
}