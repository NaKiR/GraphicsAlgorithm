package graphicComponent;

import generateValues.Function;

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

    private Function data;
    private String nameY = "Y";
    private String nameX = "X";
    private JScrollPane scroll;

    public PaintGraph()
    {
        valOfDivision = 10;
        indent = 40 ;
        lengthY = 500;
        lengthX = 500;
        setBackground(Color.WHITE);
        data = new Function();
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if(e.isControlDown() && e.getPreciseWheelRotation() > 0){
                    if(valOfDivision > 4) valOfDivision--;
                    repaint();
                    paintAxis(data.getData());
                }
                if(e.isControlDown() && e.getPreciseWheelRotation() < 0){
                    if(valOfDivision < 16) valOfDivision++;
                    repaint();
                    paintAxis(data.getData());
                }
            }
        });
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);
        List<Double []> dots = data.getData();
        paintAxis(dots);
        for(int currentDot = 0; currentDot < dots.size(); currentDot++){
            Double dot[] = dots.get(currentDot);
            g.setColor(Color.DARK_GRAY);
            double x = dot[0];
            double y = dot[1]/0.08;
            g.fillOval(indent + (int)x* valOfDivision - 2, indent + lengthY/2 - (int)y * valOfDivision - 2, 5, 5);

            if(currentDot != 0){
                g.setColor(Color.BLACK);
                double x1 = dots.get(currentDot - 1)[0];
                double y1 = dots.get(currentDot - 1)[1]/0.08;
                g.drawLine(indent + (int)x1 * valOfDivision + 1, indent + lengthY/2 - (int)y1 * valOfDivision + 1,
                        indent +  (int)x * valOfDivision + 1 , indent + lengthY/2 - (int)y * valOfDivision + 1);
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

        for(int segment = 1; segment <= numOfSegmentsY/2; segment++){
            g.drawLine(indent - 3, lengthY/2 + indent - valOfDivision * segment,
                    indent + 3, lengthY/2 + indent - valOfDivision * segment);
        }
        for(int segment = -1; segment >= -numOfSegmentsY/2; segment--){
            g.drawLine(indent - 3, lengthY/2 + indent - valOfDivision * segment,
                    indent + 3, lengthY/2 + indent - valOfDivision * segment);
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
    }
    public void setData(Function data){
        this.data = data;
    }

    public void paintAxis(List<Double[]> dots) {
        for (Double[] dot1 : dots) {
            double x = dot1[0];
            double y = dot1[1] / 0.08;
            if (lengthX / valOfDivision < (int) x) {
                lengthX = (int) x * valOfDivision;
            }
            if (lengthY / valOfDivision < (int) y) {
                lengthY = (int) y * valOfDivision;
            }
        }
    }
}