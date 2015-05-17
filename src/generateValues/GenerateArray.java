package generateValues;


/**
 * Created by USER on 17.05.15.
 */
public class GenerateArray {

    public  Integer[] generateArray(){
        Integer n;
        Integer array[];
        //System.out.println("Введите размер массива");
        n = (int) ( 2 + (Math.random()*(99 - 2)));

        array = new  Integer [n];
        for(int i = 0; i < n; i++ ){
            array[i] = (int)((Math.random()*(100-10)));
        }
        return array;
    }
}
