package sorting;

/**
 * Created by USER on 17.05.15.
 */
public class KeyPrinter  implements TreeVisitor {
    private  Integer [] array;
    public KeyPrinter( Integer [] array){
        this.array = array;
    }
    public void visit(Tree node) {
        for(int i = 0; i < array.length; i++) {
            array[i] = node.key;
        }
    }
};
