package graphicComponent;

/**
 * Created by USER on 18.05.15.
 */
import generateValues.ReturnData;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.*;
import java.text.DecimalFormat;
import java.util.List;

public class PaintGraph extends JPanel
{
    private int valOfDivision;
    private int indent;
    private int lengthY;
    private int lengthX;

    private ReturnData data;
    private String nameY = "Y";
    private String nameX = "X";
    private JScrollPane scrollPane;

    public PaintGraph()
    {
        valOfDivision = 10;
        indent = 40 ;
        lengthY = 500;
        lengthX = 500;
        setBackground(Color.WHITE);
        data = new ReturnData();
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);
        List<Integer []> dots = data.getRows();
        for(int currentDot = 0; currentDot < dots.size(); currentDot++){
            if(lengthX / valOfDivision < dots.get(currentDot)[0]){
                lengthX = dots.get(currentDot)[0] * valOfDivision;
            }
            if(lengthY /valOfDivision < dots.get(currentDot)[1]/1000){
                lengthY = (dots.get(currentDot)[1]/1000) *valOfDivision;
            }
        }
        for(int currentDot = 0; currentDot < dots.size(); currentDot++){
            Integer dot[] = dots.get(currentDot);
            g.setColor(Color.BLUE);
        g.fillOval(indent + dot[0]* valOfDivision - 2, indent + lengthY - (dot[1]/1000) * valOfDivision - 2, 5, 5);

            if(currentDot !=0){
                g.setColor(Color.RED);
                g.drawLine(indent + dots.get(currentDot - 1)[0]* valOfDivision + 1, indent + lengthY - ( dots.get(currentDot - 1)[1]/1000) * valOfDivision +1 ,
                        indent +  dots.get(currentDot)[0]* valOfDivision + 1 , indent + lengthY - ( dots.get(currentDot)[1]/1000) * valOfDivision +1);
            }
        }
        g.setColor(Color.BLACK);
        setPreferredSize(new Dimension(lengthX + 2 * indent + 2 * valOfDivision, lengthY + 2 * indent));
        g.drawLine(indent, indent, indent, lengthY + indent);
        g.drawLine( indent, lengthY/2 + indent, indent + lengthX + valOfDivision, lengthY/2 + indent);
        g.drawLine(indent, indent, indent - 7, indent + 10);
        g.drawLine(indent, indent, indent + 7, indent + 10);
        g.drawString(nameY, indent, indent - 5);
        g.drawString(nameX, indent + lengthX + valOfDivision, lengthY/2 + indent + 20);
        g.drawLine(indent + lengthX + valOfDivision, lengthY/2 + indent, indent + lengthX + valOfDivision - 10, lengthY/2 + indent + 7);
        g.drawLine(indent + lengthX + valOfDivision, lengthY/2 + indent, indent + lengthX + valOfDivision - 10, lengthY/2 + indent -7);

        Integer numOfSegmentsX = lengthX / valOfDivision;
        Integer numOfSegmentsY = (lengthY / valOfDivision) - 1;

        for(int segment = 1; segment <= numOfSegmentsY; segment++){
            g.drawLine(indent - 3, lengthY + indent - valOfDivision * segment,
                    indent + 3, lengthY + indent - valOfDivision * segment);
        }
        for(int segment = 1; segment <= numOfSegmentsX; segment++){
            g.drawLine(indent +  valOfDivision * segment, lengthY/2 + indent -3,
                    indent +  valOfDivision * segment, lengthY/2 + indent + 3);
        }

        int index;
        index = 10;
        int indentIn;
        indentIn = indent ;

        for(Integer segment = 5; segment <= numOfSegmentsX - 5; segment+= 5){

            if((segment+1)/index != 0){
                index = index * 10;
                indentIn = indentIn - 4;
            }
            g.drawString(segment.toString(),indentIn +  valOfDivision * segment, lengthY/2 + indent + 15 );
        }
        indentIn = indent - 30;
        DecimalFormat myFormatter = new DecimalFormat("##0.00");

        for(double segment = 0.4; segment <= numOfSegmentsY/2 * 0.08; segment += 0.4){
            Integer temp = (int)(segment/0.08);
            g.drawString(myFormatter.format(segment), indentIn, lengthY/2 + indent - valOfDivision * temp + 5);
        }
        indentIn = indentIn - 7;
        for(double segment = -0.4; segment >= - numOfSegmentsY/2 * 0.08; segment -= 0.4){
            Integer temp = (int)(segment/0.08);
            g.drawString(myFormatter.format(segment), indentIn, lengthY/2 + indent - valOfDivision * temp + 5);
        }
        g.drawString("0,00", indentIn, lengthY/2 + indent + 5);

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if(e.isControlDown() && e.getPreciseWheelRotation() > 0){
                    if(valOfDivision > 4) valOfDivision--;
                    repaint();
                    System.out.println(e.getScrollType());
                }
                if(e.isControlDown() && e.getPreciseWheelRotation() < 0){
                    if(valOfDivision < 16) valOfDivision++;
                    repaint();
                    System.out.println(e.getScrollType());
                }
            }
        });
    }
    public void setData(ReturnData data){
        this.data = data;
    }

    public int getValOfDivision() {
        return valOfDivision;
    }
    public void setValOfDivision(int valOfDivision) {
        if(valOfDivision > 2) this.valOfDivision = valOfDivision;
    }

    public void setNameY(String name){
        this.nameY = name;
    }
    public void setNameX(String name){
        this.nameX = name;
    }
}