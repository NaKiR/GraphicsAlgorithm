package generateValues;

import graphicComponent.GraphComponent;
import pageView.PageViewComponent;
import sorting.HeapSortAlgorithm;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by USER on 17.05.15.
 */
public class ReturnData extends  Thread{
    private  Integer numOfArray;
    private List< Integer[]> resultData;
    private Integer[] row;
    private PageViewComponent table;
    private GraphComponent graphicComponent;

    public ReturnData(){
        numOfArray = 1;
        resultData = new ArrayList< Integer[]>();
    }

    public void setTableAndGraph(PageViewComponent table, GraphComponent graphicComponent){
        this.table = table;
        this.graphicComponent = graphicComponent;
    }

    private Integer[] generateRow(int numItems) throws Exception {
        GenerateArray generator = new GenerateArray();
        Integer[] resultRow = new  Integer[2];
        Integer[] array;
        Integer start;
        resultRow [0] = numItems;
        HeapSortAlgorithm sort = new HeapSortAlgorithm();
        Integer middle = 0;
        for(int currentArray = 0; currentArray < 1000; currentArray++){
            array = generator.generateArray(numItems);
            sort.setArray(array);
            start = (int) System.nanoTime();
            sort.run();
            Integer stop = (int) System.nanoTime();
            Integer result = stop - start;
            middle += result;
        }
        resultRow [1] = middle/1000;
        return resultRow;
    }

    public void run(){
        for(int currentRow = 2; currentRow <= numOfArray; currentRow++){
            try {
                row = generateRow(currentRow);
                EventQueue.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        resultData.add(row);
                        if(graphicComponent != null && table != null){
                            graphicComponent.repaint();
                            table.updateModel();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void getNumOfArray(int numOfArray){
        this.numOfArray = numOfArray;
    }

    public List< Integer[]> getRows(){
        return resultData;
    }
}
