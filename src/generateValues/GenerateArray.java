package generateValues;


/**
 * Created by USER on 17.05.15.
 */
public class GenerateArray{

    public  Integer[] generateArray(int numOfItems){
        Integer n;
        Integer array[];
        array = new  Integer [numOfItems];
        for(int i = 0; i < numOfItems; i++ ){
            array[i] = (int)((Math.random()*(1000000-10)));
        }
        return array;
    }
}
