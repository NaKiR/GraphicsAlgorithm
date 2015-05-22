package generateValues;

import sorting.HeapSortAlgorithm;
import sorting.SortingClass;

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

    public ReturnData(){
        numOfArray = 1;
        resultData = new ArrayList< Integer[]>();
    }

    private Integer[] generateRow(int numItems) throws Exception {
        GenerateArray generator = new GenerateArray();
        Integer[] resultRow = new  Integer[2];
        Integer[] array;
        Integer start;
        array = generator.generateArray(numItems);
        resultRow [0] = array.length;
        HeapSortAlgorithm sort = new HeapSortAlgorithm();
        sort.setArray(array);
        start = (int) System.nanoTime();
        sort.run();
        Integer stop = (int) System.nanoTime();
        Integer result = stop - start;
        resultRow [1] = result;
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
