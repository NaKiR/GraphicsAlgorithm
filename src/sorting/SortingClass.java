package sorting;

/**
 * Created by USER on 12.05.15.
 */
public class SortingClass {
    private  Integer[] inputArray;

    public SortingClass( Integer[] inputArray){
        this.inputArray = inputArray;
    }

        public void startSort() {
            Tree myTree;
            myTree = new Tree(inputArray[0]);
            for(int currentElement = 1; currentElement < inputArray.length; currentElement++){
                myTree.insert( new Tree(inputArray[currentElement]));
            }
            myTree.traverse(new KeyPrinter(inputArray));
        }
}
