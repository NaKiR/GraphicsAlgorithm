package generateValues;

import sorting.HeapSortAlgorithm;
import sorting.SortingClass;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by USER on 17.05.15.
 */
public class ReturnData {
    private  Integer numOfArray;
    private List< Integer[]> resultData;

    public ReturnData(int numOfArray){
        this.numOfArray = numOfArray;
        resultData = new ArrayList< Integer[]>();
    }

    public  Integer[] generateRow() throws Exception {
        GenerateArray generator = new GenerateArray();
        Integer[] resultRow = new  Integer[2];
        Integer[] array;
        Integer start = (int) System.nanoTime();
        array = generator.generateArray();
        resultRow [0] = array.length;
      //  SortingClass sort = new SortingClass(array);
        HeapSortAlgorithm sort = new HeapSortAlgorithm();
        sort.setArray(array);
        sort.run();
        Integer stop = (int) System.nanoTime();
        Integer result = stop - start;
        resultRow [1] = result;
//        for (int i = 0; i< array.length; i++)
//            System.out.println(array[i]);
        return resultRow;
    }

    public List< Integer[]> getData(){
        for(int currentRow = 0; currentRow < numOfArray; currentRow++){
            try {
                resultData.add(generateRow());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultData;
    }
}
