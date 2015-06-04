package nakir.ppvis.lab3.graphic;

import nakir.ppvis.lab3.MyTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Function extends  Thread {
    private int a = 0;
    private int b = 0;
    private int x1 = 0;
    private int x2 = 0;
    private List<Double[]> resultData = new ArrayList< Double[]>();
    private Double[] row;
    private MyTableModel table;
    private GraphComponent graphicComponent;

    public Function(){}

    public void setTableAndGraph(MyTableModel table, GraphComponent graphicComponent){
        this.table = table;
        this.graphicComponent = graphicComponent;
    }

    private Double[] solve(int x) throws Exception {
        Double[] resultRow = new Double[2];
        double answer = 0;
        double tempAnswer;
        int i = 1;
        double e = 0.0001;
        do {
            tempAnswer = Math.pow(-1, i - 1) * Math.sin(i * (a * x - b)) / i;
            answer += tempAnswer;
            i++;
        } while (tempAnswer > e);
        resultRow [0] = (double)x;
        resultRow [1] = answer;
        return resultRow;
    }

    public void run(){
        for(int x = x1; x <= x2; x++){
            try {
                row = solve(x);
                EventQueue.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        resultData.add(row);
                        if(graphicComponent != null && table != null){
                            graphicComponent.repaint();
                            table.updateModel(resultData);
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List< Double[]> getData(){
        return resultData;
    }

    public void setConst(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void setVar(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }
}
